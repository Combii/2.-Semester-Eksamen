package BusinessLogic;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;
import Dao.AAccountDAO;
import Dao.AccountInterface;
import Dao.DAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

/**
 * Created by ${Boris} Grunwald} on 27/11/2016.
 */
public class UserValidationTest {

    AccountInterface d;


    @Before
    public void setUp() throws Exception {

        d = new AAccountDAO();

        d.save(new Customer("bob",HashCode.createHash("1234"),2));
        d.save(new Employee("bob1",HashCode.createHash("1235"),1,"bobby","john","bob@gmail.com"));
        d.save(new Admin("bbob1",HashCode.createHash("3235"),0,"bobby1","john1","bob1@gmail.com"));

    }

    @After
    public void tearDown() throws Exception {



    }

    @Test
    public void isUser() throws Exception {


    }

    @Test
    public void isCustomer() throws Exception {

        System.out.println(HashCode.createHash("1234"));
        assertEquals(true,d.isCustomer(HashCode.createHash("1234")));

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