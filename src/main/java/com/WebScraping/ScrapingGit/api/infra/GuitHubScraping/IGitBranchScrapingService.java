package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping;

import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.BranchInfoDto;
import java.util.List;


public interface IGitBranchScrapingService {
    List<BranchInfoDto> getBranches(String pUrl);
    BranchInfoDto getBranche(String pUrl) ;
}
