package io.github.srhojo.utils.webclientbuilder.executer;

import io.github.srhojo.utils.webclientbuilder.entities.RestRequest;
import reactor.core.publisher.Mono;

public interface WebClientExecuter {

    /**
     * @param request request data
     * @param <T>     Response Type
     * @return response
     */
    <T> Mono<T> execute(RestRequest request);
}
