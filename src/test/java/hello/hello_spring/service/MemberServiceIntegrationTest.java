package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest //스프링까지 띄워서 테스트. 단, 스프링 컨테이너 안띄우고 단위테스트로 하는 것이 좀더 좋음
@Transactional
//test가 끝나면 commit하기 전에 rollback시켜줌 (TEST에 붙었을때만)
class MemberServiceIntegrationsTest {
    //이전에 실행한거 그대로 실행: ctrl + R
    @Autowired  MemberService memberService;
    @Autowired  MemberRepository memberRepository;


    //TEST코드 짤 때 추천: given -> when -> then
    @Test
    void 회원가입() { //test는 과감하게 한글로 메서드이름 만들어도 괜찮음!
        //given
        Member member  = new Member();
        member.setName("Lee");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외처리(){
        //given
        Member member1 = new Member();
        member1.setName("Kim");

        Member member2 = new Member();
        member2.setName("Kim");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2); //예외 터지는 부분
//            fail();
//        } catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }
}