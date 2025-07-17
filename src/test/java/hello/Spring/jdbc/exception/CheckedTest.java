package hello.Spring.jdbc.exception;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CheckedTest {

    static class MyCheckedException extends Exception {

        public MyCheckedException(String message) {
            super(message);
        }
    }
    static class Service{
      Repository repository=new Repository();
      public void  CatchTheException(){
          try {
              repository.call();
          } catch (MyCheckedException e) {
                log.info("message={}", e.getMessage(), e);
          }
      }
      public void throwTheException()throws MyCheckedException{
          repository.call();
      }


    }
    static class Repository{
        public void call()throws MyCheckedException{
            throw  new MyCheckedException("ex");
        }
    }
}
