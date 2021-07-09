package domain.user;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DLUserDao {

    private ConnectionMaker connectionMaker;

    public DLUserDao() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            DaoFactory.class);
        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    }

}