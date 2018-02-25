package com.firat.server;

import com.firat.api.ISpotify;
import com.firat.server.data.SearchRequest;
import com.firat.api.data.SpotifyBaseResponse;
import com.firat.api.data.error.Error;
import com.firat.api.data.error.SpotifyError;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Component
public class AlbumWS implements IAlbum {
    @Autowired
    ISpotify spotify;


    @Override
    public ResponseEntity<SpotifyBaseResponse> search(@RequestBody SearchRequest searchRequest) {
        try
        {
            String query = searchRequest.getQuery();
            String type  = searchRequest.getType();
            int limit = searchRequest.getLimit();
            int offset = searchRequest.getOffset();
            SpotifyBaseResponse baseResponse = spotify.search(query,type,limit,offset);
            return new ResponseEntity<>(baseResponse,org.springframework.http.HttpStatus.OK);
        }
        catch (IOException io)
        {
            String message = "Connection between Spotify and the server may drop !!!";
            SpotifyError error = makeErrorObject(message,HttpStatus.SC_SERVICE_UNAVAILABLE);
            return new ResponseEntity<>(error,org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch (Exception e)
        {
            String message = "OMG!!! Something occurred internally. Call The Godfather !!!";
            SpotifyError error = makeErrorObject(message,HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(error,org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * constructs error response object
     * @param message the value to be displayed
     * @param status http flag code
     * @return
     */
    private SpotifyError makeErrorObject(String message, int status)
    {
        Error error = new Error();
        error.setMessage(message);
        error.setStatus(status);
        SpotifyError spotifyError = new SpotifyError();
        spotifyError.setError(error);
        return spotifyError;
    }
}
