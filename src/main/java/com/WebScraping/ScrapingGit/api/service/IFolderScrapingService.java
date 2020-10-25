package com.WebScraping.ScrapingGit.api.service;

import com.WebScraping.ScrapingGit.api.domain.Folder;

public interface IFolderScrapingService {
    
    Folder getFolderContent(String pUrlFolder);
    Folder getFolderContent(String pFolderName, String pUrlFolder);
    
    
}
