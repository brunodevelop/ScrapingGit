package com.WebScraping.ScrapingGit.api.dto;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScraplngRequestDto implements Serializable{
    private String url;
    private boolean currentBranchOnly;
}
