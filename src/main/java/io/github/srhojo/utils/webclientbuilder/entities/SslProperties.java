package io.github.srhojo.utils.webclientbuilder.entities;

import java.io.Serializable;

/**
 * @author srhojo
 *
 */
public class SslProperties implements Serializable {

    private static final long serialVersionUID = 4228311842056958838L;

    private String keystorePath;
    private String keyStorePwd;
    private String trustStorePath;
    private String tristStorePwd;

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(final String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getKeyStorePwd() {
        return keyStorePwd;
    }

    public void setKeyStorePwd(final String keyStorePwd) {
        this.keyStorePwd = keyStorePwd;
    }

    public String getTrustStorePath() {
        return trustStorePath;
    }

    public void setTrustStorePath(final String trustStorePath) {
        this.trustStorePath = trustStorePath;
    }

    public String getTristStorePwd() {
        return tristStorePwd;
    }

    public void setTristStorePwd(final String tristStorePwd) {
        this.tristStorePwd = tristStorePwd;
    }

}
