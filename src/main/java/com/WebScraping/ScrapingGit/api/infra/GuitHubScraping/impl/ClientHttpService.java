package com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.impl;

import com.WebScraping.ScrapingGit.api.infra.GuitHubScraping.IClientHttpService;
import com.WebScraping.ScrapingGit.api.infra.exceptions.LoadPageException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import javax.xml.ws.http.HTTPException;
import org.apache.http.HttpEntity;
import org.apache.http.client.cache.CacheResponseStatus;
import org.apache.http.client.cache.HttpCacheContext;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientHttpService implements IClientHttpService {

    @Autowired(required = true)
    private CloseableHttpClient configHttp;

    @Override

    public String loadPage(String url) {
        StringBuilder pageTextContent = new StringBuilder();
        try {
            HttpGet request = new HttpGet(url);

            HttpCacheContext context = HttpCacheContext.create();

            CloseableHttpResponse response = configHttp.execute(request, context); // Get HttpResponse Status

            System.out.println("-------------------------");
            System.out.println(url);
            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 200
            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK
            CacheResponseStatus responseStatus = context.getCacheResponseStatus(); //Cache
            System.out.println(responseStatus);
            System.out.println("-------------------------");

            /*-Note
            This is a contour.
            A good way would be to use a proxy service to use in httpclient.
            However the free proxies I found were slower than waiting and making a new request.
             */
            if (response.getStatusLine().getStatusCode() == 429) {
                response.close();
                Thread.sleep(30000);
                pageTextContent.append(this.loadPage(url));

            } else if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    try (BufferedReader br = new BufferedReader(
                            new InputStreamReader(entity.getContent(), "UTF-8"))) {

                        String line;
                        while ((line = br.readLine()) != null) {
                            pageTextContent.append(line);
                        }
                        entity.getContent().close();
                    } finally {
                        response.close();
                    }

                }
            } else {
                throw new HTTPException(response.getStatusLine().getStatusCode());
            }

            return pageTextContent.toString();

        } catch (MalformedURLException ex) {
            throw new LoadPageException(ex.getMessage(), ex);
        } catch (IOException ex) {
            throw new LoadPageException(ex.getMessage(), ex);
        } catch (InterruptedException ex) {
            throw new LoadPageException(ex.getMessage(), ex);
        }
    }

}
