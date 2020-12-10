package io.github.srhojo.utils.webclientbuilder.httpbin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpbinResponse implements Serializable {

    private static final long serialVersionUID = 7994025152918187758L;

    private Object json;
    private String origin;
    private String url;
    private HashMap<String, String> headers;

    public Object getJson() {
        return json;
    }

    public void setJson(final Object json) {
        this.json = json;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(final String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final HashMap<String, String> headers) {
        this.headers = headers;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
