package Dao;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;
import BusinessLogic.HashCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public class AAccountDAOTest {

    AccountDAOInterface dao;
    Account a;
    Account b;
    Account c;

    //Also testes save method
    @Before
    public void setUp() throws Exception {

        dao = new AccountDAO();
        a = new Customer("Anders" , HashCode.createHash("1234"), 2);
        b = new Employee("Jikol1906", HashCode.createHash("2345"),1, "signe", "andersen", "signe@gmail.com");
        c = new Admin("username1234", HashCode.createHash("3456"), 0, "Preben", "Jensen", "Preben@gmail.com");

        dao.save(a);
        dao.save(b);
        dao.save(c);

    }

    //Also testes delete and getId method
    @After
    public void tearDown() throws Exception {

       dao.delete(String.valueOf(dao.getId("Anders")));
        dao.delete(String.valueOf(dao.getId("Anders")));
        dao.delete(String.valueOf(dao.getId("Jikol1906")));
        dao.delete(String.valueOf(dao.getId("username1234")));

    }

    @Test
    public void get() throws Exception {

        assertEquals(true,dao.get("Anders").equals(a));
        assertEquals(true,dao.get("Jikol1906").equals(b));
        assertEquals(true,dao.get("username1234").equals(c));
        assertEquals(null,dao.get("notaValidUsername123141342"));

    }

    //Tested in UserValidationTest
    @Test
    public void isCustomer() throws Exception {

    }

    //Testing getId for users not in DB
    @Test
    public void getId() throws Exception {

        assertEquals(-1,dao.getId("notavalidusername23423"));
        assertEquals(-1,dao.getId("falseusernamesefefse"));

    }

    @Test
    public void exists() throws Exception {

        assertEquals(true,dao.exists("Anders"));
        assertEquals(true,dao.exists("Jikol1906"));
        assertEquals(true,dao.exists("username1234"));
        assertEquals(false,dao.exists("notaValidusername1242342"));
        assertEquals(false,dao.exists("w4e4te4%465465"));

    }
}