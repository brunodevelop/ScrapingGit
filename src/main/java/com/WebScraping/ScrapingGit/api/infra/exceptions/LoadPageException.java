package com.WebScraping.ScrapingGit.api.infra.exceptions;

public class LoadPageException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LoadPageException(String msg) {
        super(msg);
    }

    public LoadPageException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
