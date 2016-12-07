package BusinessLogic;

import Dao.AccountDAO;
import Dao.AccountDAOInterface;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ${Boris} Grunwald} on 03/12/2016.
 */
public class CreateNewAccountTest {


    AccountDAOInterface dao;

    @Before
    public void setUp() throws Exception {

        dao = new AccountDAO();

    }

    @After
    public void tearDown() throws Exception {

        dao.delete(dao.getId("bobby1234") );
        dao.delete(dao.getId("Preben1234"));
        dao.delete(dao.getId("Anders1234"));
        dao.delete(dao.getId("Johnny323"));
        dao.delete(dao.getId("Kasper23423"));

    }

    @Test
    public void createNewAccount() throws Exception {

        CreateNewAccount.createNewAccount("bobby1234","423","bob@gmail.com",0,"bob","bobbine");
        assertEquals(true,dao.exists("bobby1234"));
        assertEquals(false,dao.exists("notavalidusername1234"));
    }

    @Test
    public void createNewCustomer() throws Exception {

        String password = CreateNewAccount.createNewCustomer("Preben1234",2);
        String password1 = CreateNewAccount.createNewCustomer("Anders1234",2);
        String password2 = CreateNewAccount.createNewCustomer("Johnny323",2);
        CreateNewAccount.createNewAccount("kasper23423","4323","bob@gmail.com",0,"bob","bobbine");


        assertEquals(true,UserValidation.isCustomer(password));
        assertEquals(true,UserValidation.isCustomer(password1));
        assertEquals(true,UserValidation.isCustomer(password2));
        assertEquals(false,UserValidation.isCustomer("4323"));
        assertEquals(false,UserValidation.isCustomer("notavalidpassword121234"));

    }

}