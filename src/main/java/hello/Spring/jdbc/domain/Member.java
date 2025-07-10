package hello.Spring.jdbc.domain;

import lombok.Data;

@Data
public class Member {
    private String memberId;
    private int money;

    public Member( String memberId,int Money) {
        this.money = money;
        this.memberId = memberId;
    }
    public Member(){

    }
}
