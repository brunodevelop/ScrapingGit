package com.WebScraping.ScrapingGit.api.infra.util;

public class GitUrlResolve {

    final static String HOST_GUITHUB = "https://github.com";
    final static String BRANCHE_BASE_PATH = "tree/";
    final static String FILE_BASE_PATH = "/blob/";
    final static String BRENCHES_PATH = "/branches";

    public static boolean isGitHubUrl(String url) {
        return url.startsWith(HOST_GUITHUB);
    }

    public static String getBranchBasePath() {
        return BRANCHE_BASE_PATH;
    }

    public static String getUrlBase(String url) {
        String repositoryname = getRepositoryName(url);
        String[] paths = url.split(repositoryname);
        return String.format("%s%s", paths[0], repositoryname);
    }

    public static String makeUrlBase(String href) {
        String[] paths = href.split(BRANCHE_BASE_PATH);
        return String.format("%s%s", HOST_GUITHUB, paths[0]);
    }

    public static String getRepositoryName(String url) {
        String[] utlParts = url.split("/");
        return utlParts[4];
    }

    public static String getUrlEnd(String url) {
        String[] paths = url.split("/");
        return paths[paths.length - 1];
    }

    public static String getFileName(String url) {
        return getUrlEnd(url);
    }

    public static boolean isUrlFile(String pUrl) {
        return pUrl.contains(FILE_BASE_PATH);
    }

    public static String getImediateFolder(String pUrl) {
        String[] paths = pUrl.split(getUrlEnd(pUrl));
        return paths[0];
    }

    public static String getBranchesPageUrl(String url) {
        return String.format("%s%s", getUrlBase(url), BRENCHES_PATH);
    }

    public static String getFullUrl(String pPath) {
        return HOST_GUITHUB + pPath;
    }
}
