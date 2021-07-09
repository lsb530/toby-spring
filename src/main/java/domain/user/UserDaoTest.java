package domain.user;

import java.sql.SQLException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);
        // 어노테이션 방식(자바)
        ApplicationContext context =
            new AnnotationConfigApplicationContext(DaoFactory.class);
        // XML을 이용
//        ApplicationContext context = new GenericXmlApplicationContext(
//            "applicationContext.xml");

        // getBean(메소드명, 반환 클래스)
        UserDao dao = context.getBean("userDao", UserDao.class);
//        UserDao dao2 = context.getBean("specialUserDao", UserDao.class);
//        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("whiteship");
        user.setName("백기선");
        user.setPassword("married");

        dao.add(user);

        System.out.println(user.getId() + " 등록 성공");

        User user2 = dao.get(user.getId());
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//        System.out.println(user2.getId() + " 조회 성공");
        if (!user.getName().equals(user2.getName())) {
            System.out.println("테스트 실패 (name)");
        }
        else if(!user.getPassword().equals(user2.getPassword())) {
            System.out.println("테스트 실패 (password)");
        }
        else {
            System.out.println("조회 테스트 성공");
        }
    }

}