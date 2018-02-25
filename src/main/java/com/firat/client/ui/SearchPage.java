package com.firat.client.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firat.api.ISpotify;
import com.firat.api.data.artist.Item;
import com.firat.api.data.SearchResponse;
import com.firat.api.data.SpotifyBaseResponse;
import com.firat.api.data.error.SpotifyError;
import com.firat.client.model.SearchPageModel;
import com.firat.server.data.SearchRequest;
import com.firat.util.AppConstants;
import com.firat.util.HttpUtil;
import com.firat.util.PropertyReader;
import com.google.gson.Gson;
import com.vaadin.server.ExternalResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ImageRenderer;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yfpayalan on 24.02.2018.gs-spring-boot
 */
@SpringComponent
@UIScope
public class SearchPage extends VerticalLayout
{

    final Grid<SearchPageModel> grid;
    @Autowired
    HttpUtil httpUtil;
    @Autowired
    PropertyReader reader;

    @Autowired
    public SearchPage()
    {
        this.grid = new Grid<>();
        grid.setSizeFull();
        addComponent(grid);

        this.grid.addColumn(SearchPageModel::getImage, new ImageRenderer()).setResizable(true).setCaption("Image");
        this.grid.addColumn(SearchPageModel::getAlbumType).setCaption("Type");
        this.grid.addColumn(SearchPageModel::getArtistName).setCaption("Artist Name");
        this.grid.addColumn(SearchPageModel::getAlbumName).setCaption("Name");
        this.grid.addColumn(SearchPageModel::getReleaseDate).setCaption("Release Date");
        this.grid.addColumn(SearchPageModel::getUri).setCaption("URI");
        }


    public void retrieveResult(String query,String type)
    {
        ExternalResource albumImage = null;
        int limit = 10;
        int offset = 0;
        SpotifyBaseResponse response=null;
        try
        {
            response = searchAlbum(query, type, limit, offset);
        }
        catch(IOException e)
        {
            //TODO
            e.printStackTrace();
        }
        if(response!=null && response instanceof SearchResponse)
        {
            List<SearchPageModel> model = new ArrayList<>();
            SearchResponse searchResponse = (SearchResponse)response;
            List<com.firat.api.data.album.Item> items = searchResponse.getAlbums().getItems();
            Iterator<com.firat.api.data.album.Item> itemIterator = items.iterator();
            while(itemIterator.hasNext())
            {
                com.firat.api.data.album.Item currentItem = itemIterator.next();
                SearchPageModel current = new SearchPageModel();
                if(!currentItem.getImages().isEmpty())
                {
                    albumImage = new ExternalResource(currentItem.getImages().get(currentItem.getImages().size()-1).getUrl());
                }
                current.setImage(albumImage);
                current.setAlbumType(currentItem.getAlbumType());
                current.setArtistName(currentItem.getArtists().get(currentItem.getArtists().size()-1).getName());
                current.setAlbumName(currentItem.getName());
                current.setReleaseDate(currentItem.getReleaseDate());
                current.setUri(currentItem.getUri());
                model.add(current);
            }
            this.grid.setItems(model);
        }

    }
    private SpotifyBaseResponse searchAlbum(String query,String type, int limit,int offset) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        BasicHeader[] headers = {
                new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        };
        String url = reader.getProperties().get(AppConstants.URL_ALBUMWS).toString();
        String uri = reader.getProperties().get(AppConstants.URI_ALBUMWS_SEARCH).toString();
        limit = 10; //intentionally forced to set value regardless of input val
        offset = 0; //intentionally forced to set value regardless of input val
        SearchRequest request = new SearchRequest(query,type,limit,offset);
        /**
         * make request to AlbumWS server.
         */
        HttpResponse response = httpUtil.prepareNSendSimpleGenericRequest(url,uri, HttpMethod.POST.name(),request.toString(),headers);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            SearchResponse searchResponse = mapper.readValue(response.getEntity().getContent(),SearchResponse.class);
            return searchResponse;
        }
        SpotifyError error = mapper.readValue(response.getEntity().getContent(), SpotifyError.class);
        return error;
    }

}
