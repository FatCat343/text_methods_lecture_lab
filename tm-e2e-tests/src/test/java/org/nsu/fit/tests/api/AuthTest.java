package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.annotations.Test;

public class AuthTest {
    private AccountTokenPojo adminToken;

    @Test(description = "Authenticate as admin.")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Authentication feature.")
    public void authAsAdminTest() {
        adminToken = new RestClient().authenticate("admin", "setup");
    }

    @Test(description = "Authenticate as admin.", dependsOnMethods = "authAsAdminTest")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Authentication feature.")
    public void authAsCustomerTest() {
        CustomerPojo customerPojo = new RestClient().createAutoGeneratedCustomer(adminToken);

        new RestClient().authenticate(customerPojo.login, customerPojo.pass);
    }
}
