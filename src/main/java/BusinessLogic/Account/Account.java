package BusinessLogic.Account;

/**
 * Created by David Stovlbaek
 * 29 November 2016.
 */
public class Account {
    private String password;
    private int userType;

    private String name;

    public Account(String name, String password, int userType) {
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (userType != account.userType) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        return name != null ? name.equals(account.name) : account.name == null;
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + userType;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AccountDAOInterface{" +
                "password='" + password + '\'' +
                ", userType=" + userType +
                ", name='" + name + '\'' +
                '}';
    }
}
