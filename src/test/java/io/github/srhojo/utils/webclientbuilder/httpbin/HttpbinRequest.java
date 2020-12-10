package io.github.srhojo.utils.webclientbuilder.httpbin;

import java.io.Serializable;

public class HttpbinRequest implements Serializable {

    private static final long serialVersionUID = 6328284405673972069L;

    private String text;

    public HttpbinRequest() {
        super();
    }

    public HttpbinRequest(final String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

}
