package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping;

import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.ItemInfoDto;
import java.util.List;


public interface IGitFolderScrapingService {
    List<ItemInfoDto> getItens(String url);
}
