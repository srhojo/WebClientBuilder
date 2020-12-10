package io.github.srhojo.utils.webclientbuilder;


import io.github.srhojo.utils.webclientbuilder.entities.NameValuePair;
import io.github.srhojo.utils.webclientbuilder.entities.RestRequest;
import io.github.srhojo.utils.webclientbuilder.executer.WebClientExecuterImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author srhojo
 * @see <a href="https://github.com/srhojo">GitHub</a>
 */
public class WebClientBuilderExecutor {

    private final WebClient webClient;
    private final RestRequest restRequest;

    private WebClientBuilderExecutor(final String baseUrl) {
        this.restRequest = new RestRequest();
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public static WebClientBuilderExecutor of(@NotNull final String baseUrl) {
        return new WebClientBuilderExecutor(baseUrl);
    }

    public WebClientBuilderExecutor uri(@NotNull final String uri) {
        restRequest.setUrl(uri);
        return this;
    }

    public WebClientBuilderExecutor method(@NotNull final HttpMethod method) {
        restRequest.setHttpMethod(method);
        return this;
    }

    public WebClientBuilderExecutor withBody(@NotNull final Object body) {
        restRequest.setBody(body);
        return this;
    }

    public WebClientBuilderExecutor withResponseType(@NotNull final Class<?> responseType) {
        restRequest.setResponseType(responseType);
        return this;
    }

    public WebClientBuilderExecutor withHeaders(final List<NameValuePair> headers) {
        restRequest.getHeaders().addAll(headers);
        return this;
    }

    public WebClientBuilderExecutor withQueryParams(final List<NameValuePair> queryParams) {
        restRequest.getQueryParams().addAll(queryParams);
        return this;
    }

    /**
     * Method to do the final action to execute the HTTP request.
     *
     * @param <T> Type of return parameter.
     * @return return value.
     */
    public <T> Mono<T> execute() {
        return WebClientExecuterImpl.of(webClient).execute(restRequest);
    }


}
