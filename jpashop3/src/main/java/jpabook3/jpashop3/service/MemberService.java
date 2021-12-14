package jpabook3.jpashop3.service;

import jpabook3.jpashop3.domain.Member;
import jpabook3.jpashop3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateMemberName(member.getName());
        return memberRepository.save(member);
    }
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    public List<Member> findAll() {
       return memberRepository.findAll();
    }

    private void validateMemberName(String name) {
        List<Member> findMembers = memberRepository.findByName(name);

        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Transactional
    public void update(Long id, String name) {
        Member findMember = findOne(id);
        findMember.setName(name);
    }
}
