package io.github.srhojo.utils.webclientbuilder;


import io.github.srhojo.utils.webclientbuilder.httpbin.HttpbinRequest;
import io.github.srhojo.utils.webclientbuilder.httpbin.HttpbinResponse;
import io.github.srhojo.utils.webclientbuilder.swapi.People;
import io.github.srhojo.utils.webclientbuilder.swapi.Starship;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;


public class WebClientBuilderIT {

    @Test
    public void testSwapiPeople() {
        // Given
        final String host = "https://swapi.dev/api";
        final String uri = "/people/1/";
        final HttpMethod method = HttpMethod.GET;

        // When
        final Mono<People> response = WebClientBuilder
                .of(host)
                .uri(uri)
                .method(method)
                .withResponseType(People.class)
                .execute();

        final People p = response.block();

        // Then
        assertThat(p).isNotNull();
        assertThat(p.getName()).isEqualTo("Luke Skywalker");
    }

    @Test
    public void testSwapiStarship() {
        // Given
        final String host = "https://swapi.dev/api";
        final String uri = "/starships/10/";
        final HttpMethod method = HttpMethod.GET;

        // When
        final Mono<Starship> response = WebClientBuilder
                .of(host)
                .uri(uri)
                .method(method)
                .withResponseType(Starship.class)
                .execute();

        final Starship p = response.block();

        // Then
        assertThat(p).isNotNull();
        assertThat(p.getName()).isEqualTo("Millennium Falcon");
        assertThat(p.getModel()).isEqualTo("YT-1300 light freighter");
        assertThat(p.getPilots().size()).isEqualTo(4);
    }

    // HttpBin.org

    @Test
    public void testHttpBinGet() {
        // Given
        final String host = "https://httpbin.org";
        final String uri = "/get";
        final HttpMethod method = HttpMethod.GET;

        // When
        final Mono<HttpbinResponse> response = WebClientBuilder.of(host).uri(uri).method(method)
                .withResponseType(HttpbinResponse.class).execute();
        final HttpbinResponse httpBinResponse = response.block();

        // Then
        assertThat(httpBinResponse).isNotNull();
        assertThat(httpBinResponse.getOrigin()).isNotNull();
        assertThat(httpBinResponse.getUrl()).isEqualTo(host + uri);

    }

    @Test
    public void testHttpBinPost() {
        // Given
        final String host = "https://httpbin.org";
        final String uri = "/post";
        final HttpMethod method = HttpMethod.POST;

        // When
        final Mono<HttpbinResponse> response = WebClientBuilder.of(host).uri(uri).method(method)
                .withResponseType(HttpbinResponse.class).execute();
        final HttpbinResponse httpBinResponse = response.block();

        // Then
        assertThat(httpBinResponse).isNotNull();
        assertThat(httpBinResponse.getOrigin()).isNotNull();
        assertThat(httpBinResponse.getUrl()).isEqualTo(host + uri);

    }

    @Test
    public void testHttpBinPut() {
        // Given
        final String host = "https://httpbin.org";
        final String uri = "/put";
        final HttpMethod method = HttpMethod.PUT;

        // When
        final Mono<HttpbinResponse> response = WebClientBuilder.of(host).uri(uri).method(method)
                .withResponseType(HttpbinResponse.class).execute();
        final HttpbinResponse httpBinResponse = response.block();

        // Then
        assertThat(httpBinResponse).isNotNull();
        assertThat(httpBinResponse.getOrigin()).isNotNull();
        assertThat(httpBinResponse.getUrl()).isEqualTo(host + uri);
    }

    @Test
    public void testHttpBinDelete() {
        // Given
        final String host = "https://httpbin.org";
        final String uri = "/delete";
        final HttpMethod method = HttpMethod.DELETE;

        // When
        final Mono<HttpbinResponse> response = WebClientBuilder.of(host).uri(uri).method(method)
                .withResponseType(HttpbinResponse.class).execute();
        final HttpbinResponse httpBinResponse = response.block();

        // Then
        assertThat(httpBinResponse).isNotNull();
        assertThat(httpBinResponse.getOrigin()).isNotNull();
        assertThat(httpBinResponse.getUrl()).isEqualTo(host + uri);
    }

    @Test
    public void testHttpBinPostAnything() {
        // Given
        final String host = "https://httpbin.org";
        final String uri = "/anything";
        final HttpMethod method = HttpMethod.POST;
        final HttpbinRequest requestBody = new HttpbinRequest("Hello World!");

        // When
        final Mono<HttpbinResponse> response = WebClientBuilder.of(host).uri(uri).method(method)
                .withBody(requestBody).withResponseType(HttpbinResponse.class).execute();

        final HttpbinResponse httpBinResponse = response.block();

        // Then
        assertThat(httpBinResponse).isNotNull();
        assertThat(httpBinResponse.getOrigin()).isNotNull();
        assertThat(httpBinResponse.getUrl()).isEqualTo(host + uri);
        assertThat(httpBinResponse.getJson()).isNotNull();
    }


}
