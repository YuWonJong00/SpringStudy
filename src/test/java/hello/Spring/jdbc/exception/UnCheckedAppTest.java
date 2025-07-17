package hello.Spring.jdbc.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UnCheckedAppTest {

    @Test
    void unchecked(){
        Controller controller = new Controller();
        assertThatThrownBy(()->controller.request()).isInstanceOf(RuntimeException.class);

    }
    @Test
    void printEx(){
        Controller controller = new Controller();
        try{
            controller.request();}
        catch (Exception e){
            log.info("ex", e);
        }
    }
    static class Controller{
        Service service = new Service();

        public void request()  {
            Service service = new Service();
            service.logic();
        }

    }
    static class Service {
        NetworkClient networkClient=new NetworkClient();
        Repository repository=new Repository();
        public void logic() {
            repository.call();
            networkClient.call();
        }

    }
    static class NetworkClient {
        public void call()  {
            throw new RuntimeConnectionException("연결 실패");
        }

    }
    static class Repository {
        public void call()  {
            try {
                runSQL();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }

    }
    static class RuntimeConnectionException extends RuntimeException {
        public  RuntimeConnectionException(String message) {
            super(message);
        }
    }
    static class RuntimeSQLException extends RuntimeException {
        public  RuntimeSQLException() {
        }
    }
}
