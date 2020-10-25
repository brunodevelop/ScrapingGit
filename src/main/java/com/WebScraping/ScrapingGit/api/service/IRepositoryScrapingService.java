package com.WebScraping.ScrapingGit.api.service;

import com.WebScraping.ScrapingGit.api.domain.Repository;
import com.WebScraping.ScrapingGit.api.dto.ScraplngRequestDto;


public interface IRepositoryScrapingService {
    Repository getRepositoryInfo(ScraplngRequestDto request) ;
    
    Repository getRepositoryBranches(String url) ;
}
