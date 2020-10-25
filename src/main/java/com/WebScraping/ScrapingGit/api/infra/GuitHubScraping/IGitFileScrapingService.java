package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping;

import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.FileInfoDto;

public interface IGitFileScrapingService {

    FileInfoDto getFileInformation(String pFileName, String pUrlFile);
    
}
