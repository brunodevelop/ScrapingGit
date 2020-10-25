package com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class ItemInfoDto extends htmlInfoDto implements Serializable {
    
    private TypeItemEnum type;
    
    public ItemInfoDto(String name, String url, TypeItemEnum type) {
        super(name, url);
        this.type = type;
    }
    
}
