package com.WebScraping.ScrapingGit.api.infra.exceptions;

public class ScrapingException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ScrapingException(String msg) {
        super(msg);
    }

    public ScrapingException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
