package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.impl.regex;

import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IGitFileScrapingService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.FileInfoDto;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.HtmlDataGroupDto;
import com.WebScraping.ScrapingGit.api.infra.exceptions.ScrapingException;
import com.google.common.base.Objects;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class GitHubFileService extends GitHubBaseService implements IGitFileScrapingService {

    final static String FIND_FILE_REGEX_DEFAULT = "<div.class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">\\s.(.*?)\\s.<span class=\"file-info-divider\"><\\/span>\\s(.*?)<\\/div>";
    final static String FIND_FILE_SIZE_ONLY_REGEX = "<div.class=\"text-mono f6 flex-auto pr-3 flex-order-2 flex-md-order-1 mt-2 mt-md-0\">(\\s.)(.*?)<\\/div>";
    final static String FIND_FILE_EXECUTOR_REGEX = "(.*)<span.*><\\/span>\\s(.*)";//"(.*)^<span.*><\\/span>$\\s(.*)";

    final static String FIND_FILE_LINE_REGEX = "([0-9]*)\\slines";
    final static String FIND_FILE_SLOC_REGEX = "([0-9]*)\\ssloc";
    final static String FIND_FILE_SIZE_REGEX = "\\s.([0-9]*.[0-9]*)\\s[a-zA-Z]*";
    final static String FIND_FILE_UNID_REGEX = ".[0-9]*.[0-9]*.\\s([a-zA-Z]*)";

    final static String FILE_DIVIDER_TAG = "<span class=\"file-info-divider\"></span>";

    @Override
    @Cacheable("file")
    public FileInfoDto getFileInformation(String name, String url) {
        FileInfoDto file = null;
        try {
            String pageContent = this.clientHttpService.loadPage(url);

            HtmlDataGroupDto data = this.extractData(pageContent, FIND_FILE_REGEX_DEFAULT);

            if (!Objects.equal(data, null)) {
                if (this.isFileExecutor(data)) {
                    data = this.extractData(data.getSecundGroup(), FIND_FILE_EXECUTOR_REGEX);
                }
                file = new FileInfoDto(this.getLine(data), this.getSlo(data), this.getsize(data), this.getUnid(data), name, url);
            } else {
                data = this.extractData(pageContent, FIND_FILE_SIZE_ONLY_REGEX);
                if (!Objects.equal(data, null)) {
                    file = new FileInfoDto(this.getsize(data), this.getUnid(data), name, url);
                }
            }
            if (file == null) {
                String x = "";
            }
            return file;
        } catch (Exception e) {
            throw new ScrapingException("Fail to get file info", e);
        }

    }

    boolean isFileExecutor(HtmlDataGroupDto data) {

        return data.getSecundGroup().contains(FILE_DIVIDER_TAG);
    }

    private int getLine(HtmlDataGroupDto data) {
        String value = this.extractValue(data.getFirstGroup(), FIND_FILE_LINE_REGEX);

        int line = 0;
        if (!value.isEmpty()) {
            line = Integer.parseInt(value);
        }

        return line;
    }

    private int getSlo(HtmlDataGroupDto data) {
        String value = this.extractValue(data.getFirstGroup(), FIND_FILE_SLOC_REGEX);
        int sloc = 0;
        if (!value.isEmpty()) {
            sloc = Integer.parseInt(value);
        }

        return sloc;
    }

    private double getsize(HtmlDataGroupDto data) {
        String value = this.extractValue(data.getSecundGroup(), FIND_FILE_SIZE_REGEX);
        double size = 0;
        if (!value.isEmpty()) {
            size = Double.parseDouble(value);
        }

        return size;

    }

    private String getUnid(HtmlDataGroupDto data) {
        String value = this.extractValue(data.getSecundGroup(), FIND_FILE_UNID_REGEX);

        return value;
    }
}
