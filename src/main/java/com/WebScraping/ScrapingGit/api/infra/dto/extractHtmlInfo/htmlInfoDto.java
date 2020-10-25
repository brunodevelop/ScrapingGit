package com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class htmlInfoDto implements Serializable {
    private String name;
    private String url;

    public htmlInfoDto(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public htmlInfoDto() {
    }
    
    
    
}
