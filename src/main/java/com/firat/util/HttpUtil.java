package com.firat.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.URI;

/**
 * Created by yfpayalan on 24.02.2018.gs-spring-boot
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HttpUtil
{

    /**
     *
     * @param uri method location.
     * @param method method type (POST,GET,PUT,DELETE)
     * @param requestData entity data which available for POST and PUT methods.
     * @param headers default header information.
     * @return
     */
    public HttpResponse prepareNSendSimpleGenericRequest(String url,String uri,String method,String requestData,BasicHeader[] headers)
    {
        HttpResponse response = null;
        StringEntity data = null;
        if(requestData!=null)
            data = new StringEntity(requestData, "UTF-8");

        HttpUriRequest httpMethod = getHttpMethod(method,url+uri);
        if(httpMethod instanceof HttpEntityEnclosingRequestBase) //HttpPost AND HttpDelete are implemented HttpEntityEnclosingRequestBase typed request.
            ((HttpEntityEnclosingRequestBase) httpMethod).setEntity(data);

        for(BasicHeader header : headers)
        {
            httpMethod.addHeader(header);
        }
        response = makeRequestSimple(httpMethod);
        return response;
    }

    private HttpResponse makeRequestSimple (HttpUriRequest request)
    {
        HttpResponse response=null;
        HttpClient client = HttpClientBuilder.create().build();
        try
        {
            response = client.execute(request);
        }
        catch (Exception e)
        {
            e.printStackTrace();
//TODO             logger.error("Remote service call failed with exception : ", e);
        }

        return response;
    }
    private  HttpUriRequest getHttpMethod(String httpMethod,String uri)
    {
        HttpUriRequest httpUriRequest = null;
        if(httpMethod == null)
            return null;
        if(httpMethod.equalsIgnoreCase("POST"))
            httpUriRequest = new HttpPost(URI.create(uri));
        if(httpMethod.equalsIgnoreCase("GET"))
            httpUriRequest = new HttpGet(URI.create(uri));
        if(httpMethod.equalsIgnoreCase("DELETE"))
            httpUriRequest = new HttpDelete(URI.create(uri));
        if(httpMethod.equalsIgnoreCase("PUT"))
            httpUriRequest = new HttpPut(URI.create(uri));
        return httpUriRequest;
    }


}
