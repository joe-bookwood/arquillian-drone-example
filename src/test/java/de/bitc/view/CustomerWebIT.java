package de.bitc.view;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import de.bitc.model.Customer;

@RunWith(Arquillian.class)
public class CustomerWebIT {

    private static final String WEBAPP_SRC = "src/main/webapp";
    private static final String WEBAPP_SRC_CUSTOMER = "src/main/webapp/customer";
    private static final String WEBAPP_SRC_TEMPLATE = "src/main/webapp/resources/scaffold";
    private static final String WEBAPP_TEST_SRC = "src/test/webapp";


    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "web-test.war")
                .addClasses(CustomerBean.class, Customer.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebResource(new File(WEBAPP_SRC_CUSTOMER, "search.xhtml"))
                .addAsWebResource(new File(WEBAPP_SRC_TEMPLATE, "pageTemplate.xhtml"))
                .addAsWebInfResource(new StringAsset("<faces-config version=\"2.3\"/>"), "faces-config.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void addCustomerTest() {
        String url = deploymentUrl.toExternalForm() + "customer/search.xhtml";
        browser.get(url);
        String pageTitle = browser.getTitle();
        assertEquals("Search Customer entities", pageTitle);
    }
}
