package com.WebScraping.ScrapingGit.api.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Folder extends CommonInfo {
    private List<File> files;
    private List<Folder> foldersChild;

    public Folder(String name, String url) {
        super(name, url);
        this.InitColections();
    }

    private void InitColections() {
        this.foldersChild = new ArrayList();
        this.files = new ArrayList();
    }

    public void addFile(File pNewFile) {
        this.files.add(pNewFile);
    }

    public void addFolder(Folder pNewFolder) {
        this.foldersChild.add(pNewFolder);
    }
}
