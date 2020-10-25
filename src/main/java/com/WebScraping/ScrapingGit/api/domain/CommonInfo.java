package com.WebScraping.ScrapingGit.api.domain;

import lombok.Getter;

@Getter
public abstract class CommonInfo
{   
    protected String name;
    protected String url;

    public CommonInfo(){
    }
    
    public CommonInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }
    
    
}
