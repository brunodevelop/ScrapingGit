package com.WebScraping.ScrapingGit.api.domain;

import java.util.ArrayList;
import java.util.List;

public class Repository extends CommonInfo {

    public List<Branch> branches;

    private void initializeColectiona() {
        this.branches = new ArrayList();
    }

    public Repository() {
        this.initializeColectiona();
    }

    public Repository(String name, String url,List<Branch> pBranches ) {
        super(name, url);
        this.branches = pBranches;
    }

}
