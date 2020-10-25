package com.WebScraping.ScrapingGit.api.domain;

import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.FileInfoDto;
import lombok.Getter;

@Getter
public class File extends CommonInfo {

    private int line;
    private int sloc;
    private double size;
    private String unid;

    public File() {
    }

    public File(FileInfoDto fileInfo) {
        super(fileInfo.getName(), fileInfo.getUrl());
        this.line = fileInfo.getLine();
        this.sloc = fileInfo.getSloc();
        this.size = fileInfo.getSize();
        this.unid = fileInfo.getUnid();
    }
}
