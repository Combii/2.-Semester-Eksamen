package BusinessLogic;

import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;
import Dao.AccountDAO;
import Dao.AccountDAOInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by ${Boris} Grunwald} on 27/11/2016.
 */
public class UserValidationTest {

    AccountDAOInterface d;


    @Before
    public void setUp() throws Exception {

        d = new AccountDAO();

        d.save(new Customer("bob",HashCode.createHash("1234"),2));
        d.save(new Customer("Preben",HashCode.createHash("password1324"),2));
        d.save(new Employee("bob1",HashCode.createHash("1235"),1,"bobby","john","bob@gmail.com"));
        d.save(new Admin("bbob1",HashCode.createHash("1234"),0,"bobby1","john1","bob1@gmail.com"));

    }

    @After
    public void tearDown() throws Exception {
        /*
        d.delete(d.getId("bob"));
        d.delete(d.getId("bob1"));
        d.delete(d.getId("bbob1"));
        d.delete(d.getId("Preben"));
        */
    }

    @Test
    public void isUser() throws Exception {

        assertEquals(1, UserValidation.isUser("bob1","1235"));
        assertEquals(0,UserValidation.isUser("bbob1","1234"));
        assertEquals(-2,UserValidation.isUser("bob","1234"));
        assertEquals(-1,UserValidation.isUser("notavalidusername132342","notvalidpasswordsefse"));

    }

    @Test
    public void isCustomer() throws Exception {

        assertEquals(true,UserValidation.isCustomer("1234"));
        assertEquals(false,UserValidation.isCustomer("notavalidPassword"));
        assertEquals(true,UserValidation.isCustomer("password1324"));

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