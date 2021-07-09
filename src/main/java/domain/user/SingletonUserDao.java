package domain.user;

public class SingletonUserDao {
    private static UserDao INSTANCE;
    private ConnectionMaker connectionMaker;

    private SingletonUserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

//    public static synchronized SingletonUserDao getInstance() {
//        if (INSTANCE == null) INSTANCE = new SingletonUserDao(?);
//        return INSTANCE;
//    }
}