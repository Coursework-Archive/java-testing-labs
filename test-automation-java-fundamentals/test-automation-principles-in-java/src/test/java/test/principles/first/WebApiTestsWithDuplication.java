package test.principles.first;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.lang.Integer.*;
import static java.net.http.HttpResponse.BodyHandlers.*;

public class WebApiTestsWithDuplication {

    private static final String BASE_URL = "https://api.github.com/";

    @Test
    void getReturns200() throws IOException, InterruptedException {

        // Arrange
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        // Act
        HttpResponse<Void> response = httpClient.send(get, discarding());
        int actualCode = response.statusCode();

        // Assert
        Assert.assertEquals(200, actualCode);

    }

    @Test
    void contentTypeIsJson() throws IOException, InterruptedException {
        // Arrange
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        // Act
        HttpResponse<Void> response = httpClient.send(get, discarding());
        String contentType = response.headers().firstValue("content-type").get();

        Assert.assertEquals("application/json; charset=utf-8", contentType);

    }

    @Test
    void xRateLimitIsPresent() throws IOException, InterruptedException {
        // Arrange
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpRequest get = HttpRequest.newBuilder(URI.create(BASE_URL))
                .setHeader("User-Agent", "Java 11 Http bot")
                .build();

        // Act
        HttpResponse<Void> response = httpClient.send(get, discarding());
        String xRateLimit = response.headers().firstValue("X-Ratelimit-Limit").get();
        Assert.assertEquals(60, parseInt(xRateLimit));

    }

}
