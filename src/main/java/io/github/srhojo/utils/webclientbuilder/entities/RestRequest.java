package io.github.srhojo.utils.webclientbuilder.entities;

import org.springframework.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author srhojo
 * @see <a href="https://github.com/srhojo">GitHub</a>
 */
public class RestRequest {

    // Required parameters
    private String url;
    private HttpMethod httpMethod;

    // Optional parameters
    private Class<?> responseType;
    private Object body;
    private final List<NameValuePair> queryParams;
    private final List<NameValuePair> headers;

    public RestRequest() {
        this.headers = new ArrayList<>();
        this.queryParams = new ArrayList<>();
    }

    public RestRequest(final String url, final HttpMethod httpMethod) {
        this();
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(final HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Class<?> getResponseType() {
        return responseType;
    }

    public void setResponseType(final Class<?> responseType) {
        this.responseType = responseType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(final Object body) {
        this.body = body;
    }

    public List<NameValuePair> getQueryParams() {
        return queryParams;
    }

    public List<NameValuePair> getHeaders() {
        return headers;
    }
}
