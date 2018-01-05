package de.bitc.view;

import java.io.File;
import java.net.URL;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import de.bitc.model.Customer;

@RunWith(Arquillian.class)
public class CustomerBeanTest {

    private static final String WEBAPP_SRC = "src/main/webapp";
    private static final String WEBAPP_TEST_SRC = "src/test/webapp";

    @Inject
    private CustomerBean customerBean;

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "web-test.war")
                .addClasses(CustomerBean.class, Customer.class)
                .addAsManifestResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsWebResource(new File(WEBAPP_SRC, "index.html"))
                .addAsWebInfResource(new StringAsset("<faces-config version=\"2.2\"/>"), "faces-config.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void should_be_deployed() {
        Assert.assertNotNull(customerBean);
    }
}
