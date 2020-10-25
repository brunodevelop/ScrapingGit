
package com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo;

import lombok.Getter;

@Getter
public class HtmlDataGroupDto {
    private String firstGroup;
    private String SecundGroup;

    public HtmlDataGroupDto(String firstGroup, String SecundGrou) {
        this.firstGroup = firstGroup;
        this.SecundGroup = SecundGrou;
    }
    
}
