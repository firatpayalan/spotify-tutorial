package com.firat.api;

import com.firat.api.data.SpotifyBaseResponse;

import java.io.IOException;

/**
 * Created by yfpayalan on 24.02.2018.gs-spring-boot
 */

public interface ISpotify
{
    SpotifyBaseResponse search(String q,String type,int limit,int offset) throws IOException;
}
