package com.WebScraping.ScrapingGit.api.service;

import com.WebScraping.ScrapingGit.api.domain.File;


public interface IFileScrapingService {
    File getFileInfo(String url);
    File getFileInfo(String name, String url);
}
