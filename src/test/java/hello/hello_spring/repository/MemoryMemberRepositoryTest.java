package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //테스트 주도 개발(TDD): 뭔가를 만들때 테스트를 먼저 만들고 구현하는 것도 있음

    //Test 한번에 돌리면 메서드 순서는 랜덤하게 돌아간다. 코드 순서와 관계없음.
    //그렇기 때문에 각 메서드별로 테스트 끝날때마다 레포지토리를 깔끔하게 지워주는 코드를 넣어야한다.
    @AfterEach //테스트가 끝날때마다 실행되는 메서드
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring"); //문장 마무리: command + shift + enter
        repository.save(member);
        Member result = repository.findById(member.getId()).get(); //option + command + V

        //System.out.println("result = "+ (result == member)); //sout

        assertEquals(member, result); //같지 않으면 error
        assertThat(member).isEqualTo(result); //assertThatd을 static import
        // add on-demand static import for 'org.assertj.core.api.Assertions'
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //변수 바꾸기 shift + F6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //변수 바꾸기 shift + F6
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result).size().isEqualTo(2);
    }
}
