package domain.user;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {

    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public UserDao userDao() {
        // 생성자 주입방식을 이용했을 때의 코드
//        return new UserDao(connectionMaker());
        UserDao userDao = new UserDao();
//        userDao.setConnectionMaker(connectionMaker());
        userDao.setDataSource(dataSource());
        return userDao;
    }

//    @Bean
//    public UserDao specialUserDao() {
//        return new UserDao(new NConnectionMaker());
//    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost/study_toby?autoReconnect=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSource.setUsername("toby");
        dataSource.setPassword("7882");

        return dataSource;
    }

    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }


}