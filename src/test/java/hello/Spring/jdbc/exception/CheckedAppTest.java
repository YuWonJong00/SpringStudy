package hello.Spring.jdbc.exception;

import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CheckedAppTest {

    @Test
    void checked(){
        Controller controller = new Controller();
        assertThatThrownBy(()->controller.request()).isInstanceOf(Exception.class);

    }
    static class Controller{
        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            Service service = new Service();
            service.logic();
        }

    }
    static class Service {
        NetworkClient networkClient=new NetworkClient();
        Repository repository=new Repository();
        public void logic() throws ConnectException, SQLException {
            repository.call();
            networkClient.call();
        }

    }
    static class NetworkClient {
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }

    }
    static class Repository {
        public void call() throws SQLException {
            throw new SQLException("ex");
        }

    }
}
