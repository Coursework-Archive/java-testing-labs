package test.principles.first;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static java.lang.Integer.*;

public class WebApiTestsRefactored {

    private static final String BASE_URL = "https://api.github.com/";
    static HttpResponse<Void> response;

    @BeforeClass // vs. @BeforeMethod
    public void getResponse() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        response = httpClient.send(get, BodyHandlers.discarding());
    }

    @Test
    void getReturns200() {

        int actualCode = response.statusCode();
        Assert.assertEquals(200, actualCode);
    }

    @Test
    void contentTypeIsJson() {

        String contentType = response.headers().firstValue("content-type").get();
        Assert.assertEquals("application/json; charset=utf-8", contentType);
    }

    @Test
    void xRateLimitIsPresent() {
        String xRateLimit = response.headers().firstValue("X-Ratelimit-Limit").get();
        Assert.assertEquals(60, parseInt(xRateLimit));
    }

}
