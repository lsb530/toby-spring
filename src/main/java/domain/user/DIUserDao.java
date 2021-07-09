package domain.user;

public class DIUserDao {
    private ConnectionMaker connectionMaker;

    public DIUserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}