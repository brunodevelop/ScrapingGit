package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.impl.regex;

import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IClientHttpService;
import com.WebScraping.ScrapingGit.api.infra.dto.extractHtmlInfo.HtmlDataGroupDto;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GitHubBaseService {

    @Autowired
    protected IClientHttpService clientHttpService;

    protected Matcher runRegex(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher;
    }

    public List<HtmlDataGroupDto> extractListData(String content, String regex) {
        List<HtmlDataGroupDto> listData = new ArrayList();

        Matcher matcher = runRegex(content, regex);
        while (matcher.find()) {
            HtmlDataGroupDto data = this.buildDataGroupDto(matcher);
            listData.add(data);
        }
        return listData;
    }

    public HtmlDataGroupDto extractData(String content, String regex) {
        HtmlDataGroupDto data = null;

        Matcher matcher = runRegex(content, regex);
        if (matcher.find()) {
            data = this.buildDataGroupDto(matcher);
        }
        return data;
    }

    public String extractValue(String content, String regex) {
        String value = "";

        Matcher matcher = runRegex(content, regex);
        if (matcher.find()) {
            value = matcher.group(1);
        }
        return value.trim();
    }

    private HtmlDataGroupDto buildDataGroupDto(Matcher result) {
        HtmlDataGroupDto data = null;
        if (result.groupCount() == 2) {
            String grupo1 = result.group(1);
            String grupo2 = result.group(2);
            data = new HtmlDataGroupDto(grupo1, grupo2);
        }

        return data;
    }
}
