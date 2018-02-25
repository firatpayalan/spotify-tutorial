package com.firat.questionsix;

import com.firat.api.data.SpotifyBaseResponse;
import com.firat.questionsix.data.Customer;
import com.firat.server.data.SearchRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/converter")
public interface IConverter {

    /**
     * read a xml file from source and converts to json format.
     * @param
     * @return
     */
    @RequestMapping(value = "xml-to-json"
            ,method = RequestMethod.GET
            ,produces = MediaType.APPLICATION_JSON_VALUE
            ,consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    ResponseEntity xmlToJson();

}
