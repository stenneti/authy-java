package com.authy.api;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by sanjeev on 5/5/17.
 */
public class TestUser {
    String apiKey = "bf12974d70818a08199d17d5e2bae630";
    String apiEndPoint = "http://sandbox-api.authy.com";
    Users subject = new Users(apiEndPoint, apiKey, true);

    @Test
    public void itTestsCreateUser() {
        User result = subject.createUser("test@example.com", "555-555-5555", "1");

        Assert.assertEquals(true, result.isOk());
        Assert.assertEquals("User created successfully.", result.getMessage());
        Assert.assertEquals(null, result.getError());
        Assert.assertEquals(200, result.getStatus());
    }

    @Test
    public void itTestsCreateBadUser() {
        User result = subject.createUser("test@example.com", "1234", "1");

        Assert.assertEquals(false, result.isOk());
        Assert.assertEquals(true, result.getError().getMessage().contains("User was not valid"));
        Assert.assertEquals("60027", result.getError().getErrorCode());
        Assert.assertEquals(400, result.getStatus());
    }
}
