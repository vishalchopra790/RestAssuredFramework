package com.employee.testcases;

import com.employee.base.TestBase;
import com.employee.utils.RestUtils;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC003_Create_User extends TestBase {
    String ename=RestUtils.ename();
    String esal=RestUtils.esal();
    String eage=RestUtils.eage();

    @BeforeClass
    void createEmployees() throws InterruptedException
    {

        log.info("*********Started TC003_Create User **********");
        System.out.println(esal);

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        JSONObject requestParam=new JSONObject();
        requestParam.put("name",ename);
        requestParam.put("salary",esal);
        requestParam.put("age",eage);
        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParam.toJSONString());
        response = httpRequest.request(Method.POST,"/create");

        Thread.sleep(5000);
    }
    @Test
    void checkResposeBody()
    {
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        Assert.assertEquals(responseBody.contains(ename), true);
        Assert.assertEquals(responseBody.contains(esal), true);
        Assert.assertEquals(responseBody.contains(eage), true);
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
        Assert.assertEquals(contentType,"application/json");
    }
    @Test
    void checkServer(){
        log.info("***********  Checking Server **********");
        String server=response.header("Server");
        log.info("server=>"+server);
        Assert.assertEquals(server,"nginx/1.16.0");
    }

    @AfterClass
    void tearndown(){
        log.info("*********  Finished TC001_Get_All_Employees **********");
    }


}
