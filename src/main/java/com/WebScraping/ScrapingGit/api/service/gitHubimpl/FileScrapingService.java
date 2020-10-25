package com.WebScraping.ScrapingGit.api.service.gitHubimpl;

import com.WebScraping.ScrapingGit.api.domain.File;
import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IGitFileScrapingService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.FileInfoDto;
import com.WebScraping.ScrapingGit.api.infra.util.GitUrlResolve;
import com.WebScraping.ScrapingGit.api.service.IFileScrapingService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileScrapingService implements IFileScrapingService {

    @Autowired
    private IGitFileScrapingService gitHubFileScrapingService;

    @Override
    public File getFileInfo(String name, String url) {
        File file = null;

        FileInfoDto fileInfo = this.gitHubFileScrapingService.getFileInformation(name, url);
        if (!Objects.equals(fileInfo, null)) {
            file = new File(fileInfo);
        } 
        return file;
    }

    @Override
    public File getFileInfo(String url) {
        String fileName = GitUrlResolve.getUrlEnd(url);
        return this.getFileInfo(fileName, url);
    }
}
