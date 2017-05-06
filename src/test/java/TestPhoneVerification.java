package com.authy.api;

import junit.framework.Assert;
import org.junit.Test;

public class TestPhoneVerification {
  String apiKey = "bf12974d70818a08199d17d5e2bae630";
  String apiEndPoint = "http://sandbox-api.authy.com";
  PhoneVerification subject = new PhoneVerification(apiEndPoint, apiKey, true);

  @Test
  public void itChecksContentTypeToBeJson() {
    PhoneVerification phoneVerification = new PhoneVerification("api.url", "api.key");
    Assert.assertEquals("application/json", phoneVerification.getContentType());
  }

  @Test
  public void itTestsVerificationStartEs() {
    Params params = new Params();
    params.setAttribute("locale", "es");

    Verification result = subject.start("555-555-5555", "1", "call", params);
    Assert.assertEquals("Llamada a +1 555-555-5555 fue iniciada.", result.getMessage());
    Assert.assertEquals("true", result.getSuccess());
  }

  @Test
  public void itTestsVerificationStartEn() {
    Params params = new Params();
    params.setAttribute("locale", "en");
    Verification result = subject.start("555-555-5555", "1", "sms", params);

    Assert.assertEquals("Text message sent to +1 555-555-5555.", result.getMessage());
    Assert.assertEquals("true", result.getSuccess());
  }

  @Test
  public void itTestsVerificationStartEnInvalid() {
    Params params = new Params();
    params.setAttribute("locale", "en");

    Verification result = subject.start("282-23", "1", "sms", params);
    Assert.assertEquals("Phone number is invalid", result.getMessage());
    Assert.assertEquals("60033", result.getErrorCode());
    Assert.assertEquals("false", result.getSuccess());
  }

  @Test
  public void itTestsVerificationCheckNotFound() {
    Verification result = subject.check("555-555-5555", "1", "2061");
    Assert.assertEquals(404, result.getStatus());
    Assert.assertEquals("No pending verifications for +1 555-555-5555 found.", result.getMessage());
    Assert.assertEquals("60023", result.getErrorCode());
    Assert.assertEquals("false", result.getSuccess());
  }

}
