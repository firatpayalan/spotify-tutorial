package com.firat.client.model;

import com.vaadin.server.ExternalResource;

/**
 * Created by yfpayalan on 24.02.2018.gs-spring-boot
 */
public class SearchPageModel
{
    private ExternalResource image;
    private String albumType;
    private String artistName;
    private String albumName;
    private String releaseDate;
    private String type;
    private String uri;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type=type;
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri=uri;
    }

    public ExternalResource getImage()
    {
        return image;
    }

    public void setImage(ExternalResource image)
    {
        this.image=image;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
