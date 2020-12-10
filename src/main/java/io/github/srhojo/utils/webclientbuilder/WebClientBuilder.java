package io.github.srhojo.utils.webclientbuilder;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.srhojo.utils.webclientbuilder.entities.NameValuePair;
import io.github.srhojo.utils.webclientbuilder.entities.RestRequest;
import io.github.srhojo.utils.webclientbuilder.entities.SslProperties;
import io.github.srhojo.utils.webclientbuilder.executer.WebClientExecuterImpl;
import reactor.core.publisher.Mono;

/**
 * @author srhojo
 * @see <a href="https://github.com/srhojo">GitHub</a>
 */
public class WebClientBuilder {

    private final WebClient webClient;
    private final RestRequest restRequest;

    private WebClientBuilder(final WebClient webClient) {
        this.restRequest = new RestRequest();
        this.webClient = webClient;
    }

    /** Init methods **/

    public static WebClientBuilder of(@NotNull final String baseUrl) {
        final WebClient wClient = WebClientConfig.createWebClient(baseUrl);
        return new WebClientBuilder(wClient);
    }

    /**
     *
     * @param baseUrl       Main API Url
     * @param sslProperties Entity with ssl properties
     * @return WebClientBuilder
     */
    public static WebClientBuilder of(@NotNull final String baseUrl,
            @NotNull final SslProperties sslProperties) {
        final WebClient wClient = WebClientConfig.createWebClient(baseUrl, sslProperties);
        return new WebClientBuilder(wClient);
    }

    /** Properties Methods **/

    public WebClientBuilder uri(@NotNull final String uri) {
        restRequest.setUrl(uri);
        return this;
    }

    public WebClientBuilder method(@NotNull final HttpMethod method) {
        restRequest.setHttpMethod(method);
        return this;
    }

    public WebClientBuilder withBody(@NotNull final Object body) {
        restRequest.setBody(body);
        return this;
    }

    public WebClientBuilder withResponseType(@NotNull final Class<?> responseType) {
        restRequest.setResponseType(responseType);
        return this;
    }

    public WebClientBuilder withHeaders(final List<NameValuePair> headers) {
        restRequest.getHeaders().addAll(headers);
        return this;
    }

    public WebClientBuilder withQueryParams(final List<NameValuePair> queryParams) {
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
