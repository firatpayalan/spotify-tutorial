package com.firat.server;

import com.firat.api.data.SearchResponse;
import com.firat.server.data.SearchRequest;
import com.firat.api.data.SpotifyBaseResponse;
import io.swagger.annotations.*;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/album")
@Api(description = "This API consumes Spotify Search API")
public interface IAlbum {
    /**
     * retrieves results from spotify search API
     * @param searchRequest
     * @return
     */
    @ApiOperation(value = "/search",response = SearchResponse.class,notes = "This service has restricted to album typed queries.")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "OK"),
            @ApiResponse(code = 403,message = "Connection between Spotify and the server may drop !!!"),
            @ApiResponse(code = 500,message = "OMG!!! Something occurred internally. Call The Godfather !!!"),

    })
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header",value = "Album API only accepts application/json",name = HttpHeaders.CONTENT_TYPE,required = true,dataType = "string")
    })
    @RequestMapping(value = "search"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity<SpotifyBaseResponse> search(@ApiParam(value = "Simple Album API request object",required = true)@RequestBody SearchRequest searchRequest);
}
