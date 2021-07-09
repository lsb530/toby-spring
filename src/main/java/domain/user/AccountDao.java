package domain.user;

public class AccountDao {

    private final ConnectionMaker connectionMaker;

    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }
}