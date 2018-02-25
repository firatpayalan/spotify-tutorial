package com.firat.cache;

import com.firat.api.data.SearchResponse;
import com.firat.api.data.SpotifyBaseResponse;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

/**
 *
 *
 * Created by yfpayalan on 24.02.2018.gs-spring-boot
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SearchCache
{
    /**
     * md5 value of uri is calculated.
     */
    private HashMap<String,SearchResponse> levelOneResponse;

    @PostConstruct
    private void init()
    {
        this.levelOneResponse = new HashMap<>();
    }

    public HashMap<String, SearchResponse> getLevelOneResponse()
    {
        return levelOneResponse;
    }
}
