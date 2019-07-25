package de.bitc.view;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
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
        WebArchive warArchive = ShrinkWrap.create(WebArchive.class, "web-test.war")
                .addClasses(CustomerBean.class, Customer.class, ViewUtils.class)
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/forge.taglib.xml", "META-INF/forge.taglib.xml")
                .addAsWebResource(new File("src/main/webapp/resources/true.png"),"/resources/true.png")
                .addAsWebResource(new File("src/main/webapp/resources/remove.png"),"/resources/remove.png")
                .addAsWebResource(new File("src/main/webapp/resources/forge-style.css"),"/resources/forge-style.css")
                .addAsWebResource(new File("src/main/webapp/resources/forge-logo.png"),"/resources/forge-logo.png")
                .addAsWebResource(new File("src/main/webapp/resources/scaffold/pageTemplate.xhtml"),"/resources/scaffold/pageTemplate.xhtml")
                .addAsWebResource(new File("src/main/webapp/resources/scaffold/paginator.xhtml"),"/resources/scaffold/paginator.xhtml")
                .addAsWebResource(new File("src/main/webapp/resources/add.png"),"/resources/add.png")
                .addAsWebResource(new File("src/main/webapp/resources/bootstrap.css"),"/resources/bootstrap.css")
                .addAsWebResource(new File("src/main/webapp/resources/search.png"),"/resources/search.png")
                .addAsWebResource(new File("src/main/webapp/resources/false.png"),"/resources/false.png")
                .addAsWebResource(new File("src/main/webapp/resources/favicon.ico"),"/resources/favicon.ico")
                .addAsWebResource(new File("src/main/webapp/resources/jboss-community.png"),"/resources/jboss-community.png")
                .addAsWebResource(new File("src/main/webapp/error.xhtml"))
                .addAsWebResource(new File("src/main/webapp/customer/view.xhtml"),"/customer/view.xhtml")
                .addAsWebResource(new File("src/main/webapp/customer/create.xhtml"),"/customer/create.xhtml")
                .addAsWebResource(new File("src/main/webapp/customer/search.xhtml"),"/customer/search.xhtml")
                .addAsWebResource(new File("src/main/webapp/index.xhtml"))
                .addAsWebResource(new File("src/main/webapp/index.html"))
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/faces-config.xml"), "faces-config.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"), "web.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"), "beans.xml");
        System.out.println(warArchive.toString(true));
        return warArchive;
    }

    @Test
    public void addCustomerTest() {
        String url = deploymentUrl.toExternalForm() + "customer/search.xhtml";
        browser.get(url);
        String pageTitle = browser.getTitle();
        assertEquals("Search Customer entities", pageTitle);
    }
}
