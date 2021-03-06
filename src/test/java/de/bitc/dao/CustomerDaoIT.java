package de.bitc.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.bitc.model.Customer;

@RunWith(Arquillian.class)
public class CustomerDaoIT {

    @Inject
    private CustomerDao customerDao;

    @Deployment(testable = true)
    public static JavaArchive createDeployment() {
        JavaArchive javaArchive = ShrinkWrap
                .create(JavaArchive.class,"dao-test.jar")
                .addClasses(CustomerDao.class,Customer.class)
                .addAsManifestResource("META-INF/persistence.xml",
                        "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(javaArchive.toString(true));
        return javaArchive;
    }

    @Test
    public void should_be_deployed() {
        Assert.assertNotNull(customerDao);
    }

    @Test
    public void daoTest() {
        Customer customer = new Customer();
        customer.setFirstName("Max");
        customer.setLastName("Mustermann");
        customer.setEmail("max.mustermann@example.com");
        customer.setLogin("max");
        customerDao.create(customer);
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertTrue(customer.getId() > 0);

        //        Customer selectedCustomer = customerDao.findById(customer.getId());
        //        assertNotNull(selectedCustomer);
        //
        //
        //        List<Customer> customers = customerDao.listAll(null, null);
        //        assertNotNull(customers);
        //        assertFalse(customers.isEmpty());
    }
}
