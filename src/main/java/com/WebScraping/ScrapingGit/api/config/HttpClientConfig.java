package com.WebScraping.ScrapingGit.api.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.cache.HttpCacheStorage;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.cache.BasicHttpCacheStorage;
import org.apache.http.impl.client.cache.CacheConfig;
import org.apache.http.impl.client.cache.CachingHttpClientBuilder;
import org.apache.http.impl.client.cache.CachingHttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfig {

    @Bean()
    public CloseableHttpClient closableHttpClient() {
        return CachingHttpClients.custom()
                .setCacheConfig(httpClietnCacheConfig()).
                setDefaultRequestConfig(requestConfig())
                .build();
    }

    @Bean
    public HttpClient httpClient() {

        return CachingHttpClientBuilder
                .create()
                .setCacheConfig(httpClietnCacheConfig())
                .setDefaultRequestConfig(RequestConfig.DEFAULT)
                .build();
    }

    @Bean
    public CacheConfig httpClietnCacheConfig() {
        return CacheConfig.
                custom().
                setMaxObjectSize(2000000000).
                setMaxCacheEntries(30000).
                setNeverCacheHTTP10ResponsesWithQueryString(false).
                build();

    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .build();
    }

    @Bean
    public HttpCacheStorage httpCacheStorage() {
        CacheConfig cacheConfig = CacheConfig.custom()
                .setMaxCacheEntries(1000)
                .setMaxObjectSize(8192)
                .build();
        HttpCacheStorage cacheStorage = new BasicHttpCacheStorage(httpClietnCacheConfig());
        return cacheStorage;
    }
}
