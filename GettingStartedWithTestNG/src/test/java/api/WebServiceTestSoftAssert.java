package api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class WebServiceTestSoftAssert {

    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeClass
    public void setup() throws IOException {
        client = HttpClientBuilder.create().build();
        response = client.execute(new HttpGet("http://api.github.com/"));
    }

    @AfterClass
    public void cleanup() throws IOException {

        client.close();
        response.close();
    }


    @Test
    public void charSetIsUtf8() throws IOException {

        //Assert
        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");

    }

    @Test
    public void typeIsJson() throws IOException {

        //Assert
        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

    }

    @Test
    public void statusIs200() throws IOException {

        //Assert
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 404);

    }


    @Test
    public void softAssertContinuesToTheEnd() throws IOException {
        //Arrange
        CloseableHttpClient client = HttpClientBuilder.create().build();
        SoftAssert sa = new SoftAssert();

        //Act
        CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com/"));

        //Assert
        System.out.println("First assert");
        sa.assertEquals(response.getStatusLine().getStatusCode(), 404);

        System.out.println("Second assert");
        sa.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

        System.out.println("Third assert");
        sa.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");

        client.close();
        response.close();

        sa.assertAll();

    }

}
