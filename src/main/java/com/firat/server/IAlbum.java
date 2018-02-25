package com.firat.server;

import com.firat.server.data.SearchRequest;
import com.firat.api.data.SpotifyBaseResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/album")
public interface IAlbum {
    /**
     * retrieves results from spotify search API
     * @param searchRequest
     * @return
     */
    @RequestMapping(value = "search"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<SpotifyBaseResponse> search(@RequestBody SearchRequest searchRequest);
}
