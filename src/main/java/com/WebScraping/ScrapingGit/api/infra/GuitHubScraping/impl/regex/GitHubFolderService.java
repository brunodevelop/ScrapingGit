package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.impl.regex;

import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IGitFolderScrapingService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.HtmlDataGroupDto;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.ItemInfoDto;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.TypeItemEnum;
import com.WebScraping.ScrapingGit.api.infra.exceptions.ScrapingException;
import com.WebScraping.ScrapingGit.api.infra.util.GitUrlResolve;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GitHubFolderService extends GitHubBaseService implements IGitFolderScrapingService {

    final static String FIND_ITENS_FOLDER_REGEX = "<a.class=\"js-navigation-open link-gray-dark\".+?href=\"(.*?)\">(.*?)<\\/a>";

    @Override
    @Cacheable("folder")
    public List<ItemInfoDto> getItens(String url)  {
        try {
            String pageContent = this.clientHttpService.loadPage(url);
            List<ItemInfoDto> itens = new ArrayList();
            List<HtmlDataGroupDto> listData = this.extractListData(pageContent, FIND_ITENS_FOLDER_REGEX);
            listData.forEach((HtmlDataGroupDto dataDtO) -> {
                itens.add(this.buildItemDto(dataDtO));
            });
            return itens;
        } catch (Exception e) {
            throw new ScrapingException("Fail to get folder intens", e);
        }

    }

    private ItemInfoDto buildItemDto(HtmlDataGroupDto data) {
        ItemInfoDto item = null;
        String urlItem = GitUrlResolve.getFullUrl(data.getFirstGroup());
        String itemName = data.getSecundGroup();
        if (GitUrlResolve.isUrlFile(urlItem)) {
            item = new ItemInfoDto(itemName, urlItem, TypeItemEnum.File);
        } else {
            item = new ItemInfoDto(itemName, urlItem, TypeItemEnum.Folder);
        }

        return item;
    }
}
