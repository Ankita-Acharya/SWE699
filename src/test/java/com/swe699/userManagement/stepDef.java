package com.swe699.userManagement;



import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class stepDef extends SpringIntegrationTest{

    String myPost = "";
    Users myPost2;

    @When("^client calls /getAll$")
    public void client_calls_getAll(){
        String url = "http://localhost:8081/getAll";
        String myPost = restTemplate.getForObject(url, String.class);

    }

    @Then("^response is given$")
    public void response_is_given(){
        Assert.assertNotNull(myPost);
    }

    @When("^api call to /getInfo$")
    public void api_call_to_getInfo(){
        String url = "http://localhost:8081/getInfo/{UserName}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("UserName", "Ankitaa");
        myPost2 = restTemplate.getForObject(url, Users.class, params);
    }

    @Then("^return user detail$")
    public void return_user_detail(){
        System.out.println("The returned User : "+myPost2);
        Assert.assertNotNull(myPost2);
    }
}
