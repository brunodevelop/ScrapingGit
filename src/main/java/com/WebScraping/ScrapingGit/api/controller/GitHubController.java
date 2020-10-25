package com.WebScraping.ScrapingGit.api.controller;

import com.WebScraping.ScrapingGit.api.domain.File;
import com.WebScraping.ScrapingGit.api.domain.Folder;
import com.WebScraping.ScrapingGit.api.domain.Repository;
import com.WebScraping.ScrapingGit.api.dto.ScraplngRequestDto;
import com.WebScraping.ScrapingGit.api.service.IFileScrapingService;
import com.WebScraping.ScrapingGit.api.service.IFolderScrapingService;
import com.WebScraping.ScrapingGit.api.service.IRepositoryScrapingService;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/github")
public class GitHubController {

    @Autowired
    private IRepositoryScrapingService repositoryScrapingService;

    @Autowired
    private IFolderScrapingService folderScrapingService;

    @Autowired
    private IFileScrapingService fileScrapingService;

    @RequestMapping(value = "/repository", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Repository getRepository(ScraplngRequestDto pRequisicao) {
        return repositoryScrapingService.getRepositoryInfo(pRequisicao);
    }

    @RequestMapping(value = "/repository/branches", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Repository getRepositoryBranches(@QueryParam("url") String url) {
        return repositoryScrapingService.getRepositoryBranches(url);
    }

    @RequestMapping(value = "/folder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Folder getFolder(@QueryParam("url") String url) {
        return this.folderScrapingService.getFolderContent(url);
    }

    @RequestMapping(value = "/fileInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public File getFileInfo(@QueryParam("url") String url) {
        return this.fileScrapingService.getFileInfo(url);
    }

}
