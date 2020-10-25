package com.WebScraping.ScrapingGit.api.service.gitHubimpl;

import com.WebScraping.ScrapingGit.api.domain.Branch;
import com.WebScraping.ScrapingGit.api.domain.Folder;
import com.WebScraping.ScrapingGit.api.domain.Repository;
import com.WebScraping.ScrapingGit.api.dto.ScraplngRequestDto;
import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IGitBranchScrapingService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.BranchInfoDto;
import com.WebScraping.ScrapingGit.api.infra.util.GitUrlResolve;
import com.WebScraping.ScrapingGit.api.service.IFolderScrapingService;
import com.WebScraping.ScrapingGit.api.service.IRepositoryScrapingService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepositoryScrapingService implements IRepositoryScrapingService {

    @Autowired
    IFolderScrapingService folderScrapingService;

    @Autowired
    private IGitBranchScrapingService gitBranchScrapingService;

    @Override
    public Repository getRepositoryInfo(ScraplngRequestDto request) {

        String url = request.getUrl();

        String repositoryName = GitUrlResolve.getRepositoryName(url);

        List<Branch> branches = this.getBranches(request);

        for (Branch branche : branches) {
            Folder rootFolder = this.folderScrapingService.getFolderContent("root", branche.getUrl());
            branche.setRootFolder(rootFolder);
        }

        Repository repository = new Repository(repositoryName, GitUrlResolve.getUrlBase(url), branches);
        return repository;

    }

    public List<Branch> getBranches(ScraplngRequestDto request)  {
        List<Branch> branches = new ArrayList();

        List<BranchInfoDto> listInfoBranches = new ArrayList();
        if (request.isCurrentBranchOnly()) {
            BranchInfoDto branche = this.gitBranchScrapingService.getBranche(request.getUrl());
            listInfoBranches.add(branche);
        } else {
            listInfoBranches = this.gitBranchScrapingService.getBranches(request.getUrl());
        }
        branches = this.buildListBranches(listInfoBranches);

        return branches;

    }

    private List<Branch> buildListBranches(List<BranchInfoDto> listInfoBranches) {

        List<Branch> branches = new ArrayList();

        listInfoBranches.forEach((BranchInfoDto infoBranche) -> {
            branches.add(new Branch(infoBranche));
        });

        return branches;
    }

    @Override
    public Repository getRepositoryBranches(String url) {
        Repository repository = null;
        List<Branch> branches = new ArrayList();
        List<BranchInfoDto> listInfoBranches = this.gitBranchScrapingService.getBranches(url);

        branches = this.buildListBranches(listInfoBranches);

        String repositoryName = GitUrlResolve.getRepositoryName(url);

        repository = new Repository(repositoryName, GitUrlResolve.getUrlBase(url), branches);

        return repository;

    }
}
