package com.firat.questionsix;

import com.firat.api.data.SpotifyBaseResponse;
import com.firat.questionsix.data.Customer;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.bind.*;
import java.io.File;

@Component
public class ConverterWS implements IConverter {
    @Override
    public ResponseEntity xmlToJson() {
        try
        {
            String location = getClass().getClassLoader().getResource("customer.xml").getFile();
            Customer customer = readAndUnmarshall(location,Customer.class);
            return new ResponseEntity(customer,HttpStatus.OK);
        }
        catch (IllegalAccessException e)
        {
            return new ResponseEntity(HttpStatus.FORBIDDEN);

        }
        catch (InstantiationException e)
        {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch (JAXBException e)
        {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private <T> T readAndUnmarshall(String loc, Class<T> tClass) throws JAXBException, IllegalAccessException, InstantiationException {
        File file = new File(loc);
        JAXBContext context = JAXBContext.newInstance(tClass);
        Unmarshaller unmarshaller= context.createUnmarshaller();

        return (T)unmarshaller.unmarshal(file);
    }

    private String xmlToJson(String rawXml)
    {
        final int PRETTY_PRINT_INDENT_FACTOR=4;
        JSONObject jsonObject = XML.toJSONObject(rawXml);
        return jsonObject.toString(PRETTY_PRINT_INDENT_FACTOR);
    }
}
