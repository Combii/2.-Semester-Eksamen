package Dao;

import BusinessLogic.Account.Account;
import BusinessLogic.Account.Admin;
import BusinessLogic.Account.Customer;
import BusinessLogic.Account.Employee;
import BusinessLogic.HashCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ${Boris} Grunwald} on 29/11/2016.
 */
public class AAccountDAOTest {

    DAO<Account> dao;
    Account a;
    Account b;
    Account c;

    @Before
    public void setUp() throws Exception {

        a = new Customer("Anders" , HashCode.createHash("1234"), 2);
        b = new Employee("Jikol1906", HashCode.createHash("2345"),1, "signe", "andersen", "signe@gmail.com");
        c = new Admin("username1234", HashCode.createHash("3456"), 0, "Preben", "Jensen", "Preben@gmail.com");
        dao = new AAccountDAO();

    }

    @Test
    public void save() throws Exception {
        dao.save(b);
    }

    @Test
    public void get() throws Exception {

    }

    @Test
    public void exists() throws Exception {

    }

    @Test
    public void exists1() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findAllByName() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }
}