package io.github.srhojo.utils.webclientbuilder.executer;

import io.github.srhojo.utils.webclientbuilder.entities.RestRequest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
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
    public <T> Mono<T> execute(RestRequest request) {

        //TODO: Add request headers
        //TODO: Add request query params
        //TODO: Add handler exceptions

        WebClient.RequestHeadersSpec<?> webClientRequest;
        if (request.getBody() != null) {
            webClientRequest = webClient.method(request.getHttpMethod()).uri(request.getUrl())
                    .contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(request.getBody()));
        } else {
            webClientRequest = webClient.method(request.getHttpMethod()).uri(request.getUrl());
        }

        return (Mono<T>) webClientRequest.retrieve().bodyToMono(request.getResponseType());
    }
}
