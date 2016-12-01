package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;
import Dao.AAccountDAO;
import Dao.DAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ${Boris} Grunwald} on 27/11/2016.
 */
public class UserValidationTest {

    DAO<Account> d;

    @Before
    public void setUp() throws Exception {

        d = new AAccountDAO();
        d.save(new Customer("bob","1234",2));
        d.save(new Employee("bob1","2345",1,"bobby","john","bob@gmail.com"));
        d.save(new Admin("bbob1","23456",0,"bobby1","john1","bob1@gmail.com"));

    }

    @After
    public void tearDown() throws Exception {

        d.delete(d.getId("bob"));
        d.delete(d.getId("bob1"));
        d.delete(d.getId("bbob1"));

    }

    @Test
    public void isUser() throws Exception {

        System.out.println(d.get("bob"));
        System.out.println(d.get("bob1"));
        System.out.println(d.get("bbob1"));


    }

    @Test
    public void isCustomer() throws Exception {

    }

    @Test
    public void isValidUsername() throws Exception {

    }

    @Test
    public void isValidPassword() throws Exception {

    }

    @Test
    public void startConnectionToDB() throws Exception {

    }

}