package jpabook3.jpashop3.service;

import jpabook3.jpashop3.domain.Address;
import jpabook3.jpashop3.domain.Member;
import jpabook3.jpashop3.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("city", "street", "1000000"));
        //when
        memberService.join(member);
        Member findMember = memberService.findOne(member.getId());
        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
    }

    @Test
    public void 회원의_이름중복() {
        //given
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("city", "street", "1000000"));

        Member member2 = new Member();
        member2.setName("memberA");
        member2.setAddress(new Address("city", "street", "1000000"));
        //when
        memberService.join(member);

        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}