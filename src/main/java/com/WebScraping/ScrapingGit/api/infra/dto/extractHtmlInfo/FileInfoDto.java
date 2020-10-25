package com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo;

import lombok.Getter;

@Getter
public class FileInfoDto extends htmlInfoDto {

    private int line;
    private int sloc;
    private double size;
    private String unid;

    public FileInfoDto(int line, int sloc, double size, String unid, String name, String url) {
        super(name, url);
        this.line = line;
        this.sloc = sloc;
        this.size = size;
        this.unid = unid;
    }

    public FileInfoDto(double size, String unid, String name, String url) {
        super(name, url);
        this.size = size;
        this.unid = unid;
    }

}
