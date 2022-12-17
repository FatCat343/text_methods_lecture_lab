package org.nsu.fit.tests.api;

import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.nsu.fit.services.fixtures.ContactFixtureBuilder;
import org.nsu.fit.services.rest.RestClient;
import org.nsu.fit.services.rest.data.AccountTokenPojo;
import org.nsu.fit.services.rest.data.ContactPojo;
import org.nsu.fit.services.rest.data.CustomerPojo;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class CreateCustomerRequestFirstNameTest {
    private RestClient restClient;
    private AccountTokenPojo adminToken;

    @BeforeClass
    private void auth() {
        restClient = new RestClient();
        adminToken = restClient.authenticate("admin", "setup");
    }

    @Test
    @Feature("Administrator can create customer")
    @Severity(SeverityLevel.BLOCKER)
    public void succeedsWith2SymbolFirstName() {
        ContactPojo contactPojo = new ContactFixtureBuilder()
                .setFirstName("Al")
                .build();

        CustomerPojo customerPojo = restClient.createCustomer(adminToken, contactPojo);

        Assert.assertEquals(customerPojo.firstName, contactPojo.firstName);
        Assert.assertEquals(customerPojo.lastName, contactPojo.lastName);
        Assert.assertEquals(customerPojo.pass, contactPojo.pass);
        Assert.assertEquals(customerPojo.login, contactPojo.login);
        Assert.assertEquals(customerPojo.balance, contactPojo.balance);
        Assert.assertNotNull(customerPojo);
    }

    @Test
    @Feature("Administrator can create customer")
    @Severity(SeverityLevel.MINOR)
    public void failsWith1SymbolFirstName() {
        ContactPojo contactPojo = new ContactFixtureBuilder()
                .setFirstName("A")
                .build();

        CustomerPojo customerPojo = restClient.createCustomer(adminToken, contactPojo);
        Assert.assertNull(customerPojo);
    }
}