package com.WebScraping.ScrapingGit.api.service.gitHubimpl;

import com.WebScraping.ScrapingGit.api.domain.File;
import com.WebScraping.ScrapingGit.api.domain.Folder;
import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IGitFolderScrapingService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.ItemInfoDto;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.TypeItemEnum;
import com.WebScraping.ScrapingGit.api.infra.util.GitUrlResolve;
import com.WebScraping.ScrapingGit.api.service.IFileScrapingService;
import com.WebScraping.ScrapingGit.api.service.IFolderScrapingService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FolderScrapingService implements IFolderScrapingService {

    @Autowired
    IFileScrapingService fileScrapingService;

    @Autowired
    private IGitFolderScrapingService gitHubFolderScrapingService;

    @Override
    public Folder getFolderContent(String pFolderName, String pUrlFolder) {
        Folder folder;
        folder = new Folder(pFolderName, pUrlFolder);

        List<ItemInfoDto> itens = gitHubFolderScrapingService.getItens(pUrlFolder);

        for (ItemInfoDto iten : itens) {
            if (iten.getType().equals(TypeItemEnum.File)) {
                File newFile = this.fileScrapingService.getFileInfo(iten.getName(), iten.getUrl());
                folder.addFile(newFile);
            } else {
                Folder newFolder = this.getFolderContent(iten.getName(), iten.getUrl());
                folder.addFolder(newFolder);
            }
        }
        return folder;

    }

    @Override
    public Folder getFolderContent(String pUrlFolder) {
        String urlFolder =GitUrlResolve.getImediateFolder(pUrlFolder);
        String folderName = GitUrlResolve.getUrlEnd(urlFolder);
        return this.getFolderContent(folderName, pUrlFolder);
    }

}
