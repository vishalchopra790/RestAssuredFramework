package com.employee.testcases;

import com.employee.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC001 extends TestBase {
    @BeforeClass
    void getAllEmployees() throws InterruptedException
    {

        log.info("*********Started TC001_Get_All_Employees **********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET,"/employees");

        Thread.sleep(5);
    }
    @Test
    void checkBody(){
        log.info("-----------Checking Body-------");
         String resp=response.getBody().asString();
         log.info("Response Body =>"+resp);
        Assert.assertTrue(resp!=null);
    }

    @Test
    void checkStatusCode(){
        log.info("Status Check Code Validation");
        int statusCode=response.getStatusCode();
        log.info("Status Code =>"+statusCode);
        Assert.assertEquals(statusCode,200);
    }

    @Test
    void checkStatusLine(){
        log.info("Validating Status Check line" );
        String status=response.statusLine();
        log.info("Status Line=>"+status);
        Assert.assertEquals(status,"HTTP/1.1 200 OK");

    }

    @Test
    void responseTime(){
    log.info("Validating Response Time");
    long responseTime=response.getTime();
    if(responseTime>2000)
        log.warn("Response time is greater than 2000 ms");
     Assert.assertTrue(responseTime<10000);
    }

    @Test
    void checkContentType(){
        log.info("***********  Checking Content Type **********");
        String contentType=response.header("Content-Type");
        log.info("Content-Type=>"+contentType);
        Assert.assertEquals(contentType,"application/json;charset=utf-8");
    }
    @Test
    void checkServer(){
        log.info("***********  Checking Server **********");
        String server=response.header("Server");
        log.info("server=>"+server);
        Assert.assertEquals(server,"nginx/1.16.0");
    }
    @Test
    void checkContentLenth(){
        log.info("***********  Content Length **********");
        String length=response.header("Content-Length");
        log.info("Content Length=>"+length);
        Assert.assertEquals(Integer.parseInt(length),595);
    }
    @Test
    void checkContentEncoding(){
        log.info("***********  Content-Encoding **********");
        String encoding=response.header("Content-Encoding");
        log.info("Content-Encoding=>"+encoding);
        Assert.assertEquals(encoding,"gzip");
    }
    @AfterClass
    void tearndown(){
        log.info("*********  Finished TC001_Get_All_Employees **********");
    }


}
