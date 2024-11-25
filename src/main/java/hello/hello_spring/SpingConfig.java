package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpingConfig {

    //private DataSource dataSource;
    //private final EntityManager em;
    private final MemberRepository memberRepository;

    @Autowired
    public SpingConfig(DataSource dataSource, EntityManager em, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        //this.em = em;
        //this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        //return new JpaMemberRepository(em);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JdbcMemberRepository(dataSource);
//        //return new MemoryMemberRepository();
//    }
}
