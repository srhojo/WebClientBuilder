package io.github.srhojo.utils.webclientbuilder.executer;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.srhojo.utils.webclientbuilder.entities.NameValuePair;
import io.github.srhojo.utils.webclientbuilder.entities.RestRequest;
import reactor.core.publisher.Mono;

public class WebClientExecuterImpl implements WebClientExecuter {

    private final WebClient webClient;

    private WebClientExecuterImpl(final WebClient webClient) {
        this.webClient = webClient;
    }

    public static WebClientExecuterImpl of(final WebClient webClient) {
        return new WebClientExecuterImpl(webClient);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Mono<T> execute(final RestRequest request) {

        // TODO: Add request query params
        // TODO: Add handler exceptions

        WebClient.RequestHeadersSpec<?> webClientRequest;

        final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(request.getUrl());
        request.getQueryParams().forEach(nameValue -> uriBuilder.queryParam(nameValue.getName(), nameValue.getValue()));

        if (request.getBody() != null) {
            webClientRequest = webClient.method(request.getHttpMethod()).uri(uriBuilder.toUriString())
                    .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(request.getBody()));
            // use fromObject for compatibility with spring 5.1.X

        } else {
            webClientRequest = webClient.method(request.getHttpMethod()).uri(uriBuilder.toUriString());
        }

        webClientRequest.headers(headers -> headers.addAll(createHeaders(request.getHeaders())));

        return (Mono<T>) webClientRequest.retrieve().bodyToMono(request.getResponseType());
    }

    private HttpHeaders createHeaders(final List<NameValuePair> customHeaders) {
        final HttpHeaders headers = new HttpHeaders();
        customHeaders.forEach(nameValue -> headers.set(nameValue.getName(), nameValue.getValue()));
        return headers;
    }

}
