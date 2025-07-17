package hello.Spring.jdbc.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UnCheckedTest {
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }

    }
    static class Service{
        Repository repository=new Repository();
        public void  CatchTheException(){
            try {
                repository.call();
            } catch (MyUncheckedException e) {
                log.info("message={}", e.getMessage(), e);
            }
        }
        public void throwTheException()throws MyUncheckedException {
            repository.call();
        }

    }
    static  class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }
}
