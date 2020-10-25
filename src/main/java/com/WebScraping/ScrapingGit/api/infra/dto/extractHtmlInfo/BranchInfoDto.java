package com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo;

import lombok.Getter;

@Getter
public class BranchInfoDto extends  ItemInfoDto{
    private boolean standard;
    
    public BranchInfoDto(String name, String url, boolean standard) {
        super(name, url, TypeItemEnum.Branch);
        this.standard = standard;
    }
    
}
