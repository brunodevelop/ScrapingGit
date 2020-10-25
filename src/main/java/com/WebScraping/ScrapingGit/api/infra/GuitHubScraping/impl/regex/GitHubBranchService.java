package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.impl.regex;

import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IGitBranchScrapingService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.BranchInfoDto;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.HtmlDataGroupDto;
import com.WebScraping.ScrapingGit.api.infra.exceptions.ScrapingException;
import com.WebScraping.ScrapingGit.api.infra.util.GitUrlResolve;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GitHubBranchService extends GitHubBaseService implements IGitBranchScrapingService {

    final static String FIND_BRANCHES_REGEX = "<a.class=\"branch-name css-truncate-target v-align-baseline width-fit mr-2 Details-content--shown\".+?href=\"(.*?)\">(.*?)<\\/a>";


    @Override
    @Cacheable("branche")
    public List<BranchInfoDto> getBranches(String url) {
        List<BranchInfoDto> branches = new ArrayList();
        
        try {
            String pagecontent = this.clientHttpService.loadPage(GitUrlResolve.getBranchesPageUrl(url));

            List<HtmlDataGroupDto> listData = this.extractListData(pagecontent, FIND_BRANCHES_REGEX);
            listData.forEach((HtmlDataGroupDto dataDtO) -> {
                String name = dataDtO.getSecundGroup();
                String urlBranche = this.getUrlBranch(dataDtO.getFirstGroup());

                branches.add(this.buildBranch(name, urlBranche));
            });

            return branches;
        } catch (Exception e) {
            throw new ScrapingException("Fail to get branches", e);
        }

    }

    @Override
    public BranchInfoDto getBranche(String url) {
        BranchInfoDto branche;
        try {
            if (!url.contains(GitUrlResolve.getBranchBasePath())) {
                branche = branche = this.findBranchInfo(url, true);
            } else {
                branche = this.findBranchInfo(url, false);
            }

            if (Objects.equals(branche, null)) {
                throw new ScrapingException("Branch not found");
            }
            return branche;
        } catch (Exception e) {
            throw new ScrapingException("Fail to get branchesl", e);
        }

    }

    private BranchInfoDto buildBranch(String name, String url) {

        boolean standardBranch = this.isBranchStandard(url);

        BranchInfoDto branche = new BranchInfoDto(name, url, standardBranch);
        return branche;
    }

    private boolean isBranchStandard(String path) {
        return GitUrlResolve.makeUrlBase(path).contains(path);
    }

    private String getUrlBranch(String path) {
        return this.isBranchStandard(path) ? GitUrlResolve.makeUrlBase(path) : GitUrlResolve.getFullUrl(path);
    }


    private BranchInfoDto findBranchInfo(String url, boolean standard) {
        List<BranchInfoDto> branches = this.getBranches(url);

        BranchInfoDto branche = branches.stream().filter((BranchInfoDto dto) -> {
            return url.contains(dto.getUrl()) && Objects.equals(dto.isStandard(), standard);
        }).findFirst().orElse(null);

        return branche;
    }
}
