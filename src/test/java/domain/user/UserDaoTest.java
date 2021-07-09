package domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDaoTest {

    @Test
    public void addAndGet() throws ClassNotFoundException, SQLException {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        dao.add(user2);

        assertThat(dao.getCount(), is(2));

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName(), is(user1.getName()));
        assertThat(userget1.getPassword(), is(user1.getPassword()));

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName(), is(user2.getName()));
        assertThat(userget2.getPassword(), is(user2.getPassword()));
    }

    @Test
    public void deleteAll() throws SQLException {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("delete from users");
        ps.executeUpdate();
        ps.close();
        c.close();
    }

    @Test
    public void count() throws ClassNotFoundException, SQLException {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        User user1 = new User("gyumee", "박성철", "springno1");
        User user2 = new User("leegw700", "이길원", "springno2");
        User user3 = new User("bumjin", "박범진", "springno3");

        dao.deleteAll();
        assertThat(dao.getCount(), is(0));

        dao.add(user1);
        assertThat(dao.getCount(), is(1));

        dao.add(user2);
        assertThat(dao.getCount(), is(2));

        dao.add(user3);
        assertThat(dao.getCount(), is(3));
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void getUserFailure() throws SQLException {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        UserDao dao = context.getBean("userDao", UserDao.class);
        dao.deleteAll();
        assertThat(dao.getCount(), is(0));
        try {
            dao.get("unkown_id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}