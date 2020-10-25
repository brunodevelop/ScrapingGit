package com.WebScraping.ScrapingGit.api.domain;

import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.BranchInfoDto;
import lombok.Getter;

@Getter
public class Branch extends CommonInfo {

    public Folder rootFolder;

    private boolean standard;

    public Branch(String pName, String pUrl, boolean pStandart) {
        super(pName, pUrl);
        this.standard = pStandart;
    }
    
    public Branch(BranchInfoDto branchInfo){
        this(branchInfo.getName(), branchInfo.getUrl(), branchInfo.isStandard());
    }

    public void setRootFolder(Folder pRootFolder) {
        this.rootFolder = pRootFolder;
    }

}
