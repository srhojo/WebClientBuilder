package io.github.srhojo.utils.webclientbuilder;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.ResourceUtils;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.srhojo.utils.webclientbuilder.entities.SslProperties;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import reactor.netty.http.client.HttpClient;

/**
 *
 * @author srhojo
 * @see <a href="https://github.com/srhojo">GitHub</a>
 *
 */
public class WebClientConfig {

    public static WebClient createWebClient(final String baseUrl) {
        return WebClientConfig.createWebClient(baseUrl, null);
    }

    /**
     * Static method to create and configuration a WebClient
     *
     * @param baseUrl       main base API URL
     * @param sslProperties SSL properties
     * @return {@link WebClient}
     */
    public static WebClient createWebClient(final String baseUrl, final SslProperties sslProperties) {

        final HttpClient httpClient = HttpClient.create();
        if (sslProperties != null) {
            httpClient.secure(
                    sslContextSpec -> sslContextSpec.sslContext(WebClientConfig.configSSLContext(sslProperties)));

        }
        final ClientHttpConnector clientHttpConnector = new ReactorClientHttpConnector(httpClient);

        return WebClient.builder().baseUrl(baseUrl).clientConnector(clientHttpConnector)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

    /**
     * Method to configure Two Way SSL context.
     *
     * @param sslProperties SSL properties
     * @return {@link SslContext}
     */
    private static SslContext configSSLContext(final SslProperties sslProperties) {

        try {

            final KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(ResourceUtils.getFile(sslProperties.getKeystorePath())),
                    sslProperties.getKeyStorePwd().toCharArray());

            final KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, sslProperties.getKeyStorePwd().toCharArray());

            final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(ResourceUtils.getFile(sslProperties.getTrustStorePath())),
                    sslProperties.getTristStorePwd().toCharArray());

            final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(trustStore);

            return SslContextBuilder.forClient().keyManager(keyManagerFactory).trustManager(trustManagerFactory)
                    .build();

        } catch (final Exception e) {
            // TODO: Add log and remove printStrackTrace
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        }

    }

}
