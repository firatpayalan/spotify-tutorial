package com.firat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firat.api.data.SearchResponse;
import com.firat.api.data.SpotifyBaseResponse;
import com.firat.api.data.error.SpotifyError;
import com.firat.cache.SearchCache;
import com.firat.util.AppConstants;
import com.firat.util.HttpUtil;
import com.firat.util.PropertyReader;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by yfpayalan on 24.02.2018.gs-spring-boot
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SpotifyClient implements ISpotify
{

    @Autowired
    PropertyReader reader;
    @Autowired
    SearchCache cache;
    @Autowired
    HttpUtil httpUtil;

    private String token;

    @PostConstruct
    private void init()
    {
        this.token = reader.getProperties().get(AppConstants.TOKEN_SPOTIFY).toString();
    }

    @Override
    public SpotifyBaseResponse search(String q, String type, int limit, int offset) throws IOException
    {

        String url = reader.getProperties().get(AppConstants.URL_SPOTIFY).toString();
        ObjectMapper mapper = new ObjectMapper();
        BasicHeader[] headers = {
                new BasicHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString()),
                new BasicHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON.toString()),
                new BasicHeader(HttpHeaders.AUTHORIZATION,"Bearer "+this.token)
        };
        String uri = "/search?q="+URLEncoder.encode(q,"UTF-8")+"&type="+type+"&offset="+offset+"&limit="+limit;
        //cache e bak yoksa query çek.
        String cacheKeyVal = calculateMd5(uri);
        SearchResponse cacheValue = queryToCache(cacheKeyVal);
        if(cacheValue != null)
        {
            return cacheValue;
        }


        HttpResponse response = httpUtil.prepareNSendSimpleGenericRequest(url,uri, HttpMethod.GET.name(),null,headers);
        if(response != null)
        {
            if(response.getStatusLine().getStatusCode() ==HttpStatus.SC_OK)
            {
                SearchResponse searchResponse = mapper.readValue(response.getEntity().getContent(),SearchResponse.class);
                registerToCache(cacheKeyVal,searchResponse);
                //cache it
                return searchResponse;
            }
            SpotifyError error = mapper.readValue(response.getEntity().getContent(), SpotifyError.class);
            return error;
        }
        else
        {
            com.firat.api.data.error.Error error = new com.firat.api.data.error.Error();
            error.setMessage("Service Unavailable - check your connection !!!");
            error.setStatus(HttpStatus.SC_SERVICE_UNAVAILABLE);
            return new SpotifyError();
        }
    }

    private void registerToCache(String key, SearchResponse value)
    {
        cache.getLevelOneResponse().put(key,value);
    }
    private SearchResponse queryToCache(String key)
    {
        SearchResponse hitted = cache.getLevelOneResponse().get(key);
        if(hitted != null)
            return hitted;
        return null;
    }


    private String calculateMd5(String query)
    {
        try
        {
            byte[] byteValue = query.getBytes("UTF-8");
            return DigestUtils.md5Hex(byteValue);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            //FIXME
            return "";
        }
    }
}
