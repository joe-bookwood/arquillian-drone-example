package de.bitc.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.junit.jupiter.MockitoExtension;

import de.bitc.model.Customer;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CustomerDaoTest {

    @Mock
    private EntityManager entityManager;

    //    @Mock
    //    private List<Customer> customerList = new ArrayList<>();

    private CustomerDao customerDao;

    private Customer customer;

    @BeforeEach
    void setUp() throws Exception {
        customerDao = new CustomerDao();
        customer = mock(Customer.class);

        // em is a private field and there is no setter
        FieldSetter.setField(customerDao, customerDao.getClass().getDeclaredField("em"), entityManager);

        when(entityManager.find(Customer.class, 1L)).thenReturn(customer);
    }

    @Test
    final void testDeleteById() throws NoSuchFieldException, SecurityException {

        customerDao.deleteById(1L);

        verify(entityManager).remove(customer);
    }

    @Test
    final void testFindById() {

        customerDao.findById(1L);

        verify(entityManager).find(Customer.class,1L);
    }

    //    @Test
    //    final void testListAll() {
    //        TypedQuery query = mock(TypedQuery.class);
    //        when(query.getResultList()).thenReturn(customerList);
    //        when(entityManager.createQuery(
    //                "SELECT DISTINCT c FROM Customer c ORDER BY c.id",
    //                Customer.class)).thenReturn(query);
    //
    //        List<Customer> result = customerDao.listAll(null, null);
    //
    //        verify(query).getResultList();
    //
    //    }

}
