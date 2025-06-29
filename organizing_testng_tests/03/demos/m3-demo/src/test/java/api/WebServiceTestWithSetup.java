package api;
import baseclasses.WebServiceBaseClass;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class WebServiceTestWithSetup extends WebServiceBaseClass {

    @BeforeClass
    public void setup() throws IOException {
        System.out.println("Runs once per class");
        client = HttpClientBuilder.create().build();
        response = client.execute(new HttpGet("https://api.github.com/"));

        int actualStatusCode = response.getStatusLine().getStatusCode();
        if (actualStatusCode != 200){
            throw new SkipException("Basic criteria failed, " +
                    "was expecting code 200, but got: " + actualStatusCode);
        }

    }

    @BeforeMethod
    public void setupMethod(){
        System.out.println("Runs before each @Test");
    }

    @AfterClass
    public void cleanup() throws IOException {
        client.close();
        response.close();
    }


    @Test
    public void statusIs200() {
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void typeIsJson() {
        // Assert
        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/json");
    }

    @Test
    public void charSetIsUtf8() {
        // Assert
        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");
    }






}
