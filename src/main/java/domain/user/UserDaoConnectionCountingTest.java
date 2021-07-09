package domain.user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            ContingDaoFactory.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        //
        // DAO 사용 코드
        //

        CountingConnectionMaker connectionCountMaker = context
            .getBean("connectionMaker", CountingConnectionMaker.class);
        System.out.println("Connection counter : " + connectionCountMaker.getCounter());
    }
}