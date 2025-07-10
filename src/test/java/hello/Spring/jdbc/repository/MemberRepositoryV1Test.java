package hello.Spring.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.Spring.jdbc.connection.ConnectionConst;
import hello.Spring.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.Spring.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
@Slf4j
public class MemberRepositoryV1Test {


    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        HikariDataSource dataSource=new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }
    @Test
    void crud() throws SQLException {
        //save
        Member member = new Member("memberV9", 10000);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("find member : {}", findMember);
        log.info("member == findMember {} :", member == findMember);
        log.info("member equals findMember {} :", member.equals(findMember));

        assertThat(findMember).isEqualTo(member);

        //update
        repository.update(member.getMemberId(), 20000);
        Member updateMember = repository.findById(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        //delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}
