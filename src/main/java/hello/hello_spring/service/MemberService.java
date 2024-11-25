package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Test 만들기: command+shift+T
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    //DI: 클래스 안에서 생성하는게 아니라 외부에서 객체를 생성해서 주입하도록 함 (디펜더시 인젝션)
    //cmd+N : 생성자, getter, setter 등등 단축키
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 1. 회원 가입
     */
    public Long join (Member member){
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();

    }

    //같은 이름이 있는 중복 회원 X
    //소스에서 메소드 뽑기: ctrl + T (리팩토링 관련 여러가지 나옴) -> extract method
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) //리턴값 Optional
                .ifPresent( m -> {                    //값이 있으면 예외처리
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 2. 전체 회원 조회
     */
    public List<Member> findMembers(){ //서비스에서는 좀더 비지니스스럽게 이름짓기
        return memberRepository.findAll();
    }

    /**
     * 3. 아이디로 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
