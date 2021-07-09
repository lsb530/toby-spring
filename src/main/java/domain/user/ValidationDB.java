package domain.user;

import java.sql.SQLException;

public class ValidationDB {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        try {
            userDao.validationQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}