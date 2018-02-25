
package com.firat.api.data.error;

import java.lang.*;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firat.api.data.SpotifyBaseResponse;
import com.google.gson.Gson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})
public class SpotifyError implements SpotifyBaseResponse
{

    @JsonProperty("error")
    private Error error;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("error")
    public Error getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Error error) {
        this.error = error;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String print()
    {
        return new Gson().toJson(this);
    }
}
