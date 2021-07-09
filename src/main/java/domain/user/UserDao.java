package domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

public class UserDao {

//    private final SimpleConnectionMaker simpleConnectionMaker;
    // 초기에 설정하면 사용 중에는 바뀌지 않는 읽기전용 인스턴스 변수
    private ConnectionMaker connectionMaker;
    // 매번 새로운 값으로 바뀌는 정보를 담은 인스턴스 변수 ( 심각한 문제가 발생한다 )
    private Connection c;
    private User user;
    private DataSource dataSource;

//    public UserDao() {
//        // 상태를 관리하는 것도 아니니 한 번만 만들어 인스턴스 변수에 저장해두고 메소드에서 사용하게 한다.
//        simpleConnectionMaker = new SimpleConnectionMaker();
//        connectionMaker = new DConnectionMaker();
//    }

//     생성자를 이용한 DI
//    public UserDao(ConnectionMaker connectionMaker) {
//        this.connectionMaker = connectionMaker;
//    }

    // 수정자 메소드(setter)를 이용한 DI
    public void setConnectionMaker(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement(
            "insert into users(id, name, password) values(?,?,?)"
        );
        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3,user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement(
            "select * from users where id = ?"
        );
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        User user = null;
        if(rs.next()) {
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
        rs.close();
        ps.close();
        c.close();
        if (user == null) throw new EmptyResultDataAccessException(1);
        return user;
    }

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

    public int getCount() throws SQLException {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select count(*) from users");
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        c.close();
        return count;
    }

    public User ggg(String id) throws ClassNotFoundException, SQLException {
        this.c = connectionMaker.makeConnection();
        this.user = new User();
        this.user.setId("id");
        this.user.setName("name");
        this.user.setPassword("password");
        return this.user;
    }

    public void validationQuery() throws SQLException {
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        DataSource dataSource = context.getBean("dataSource", DataSource.class);
        Connection c = dataSource.getConnection();
        PreparedStatement ps = c.prepareStatement("select 1");
        ResultSet rs = ps.executeQuery();
        rs.close();
        ps.close();
        c.close();
    }
}