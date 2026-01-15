package com.example.jpa2.repository;

import com.example.jpa2.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {

        Member member = Member.builder()
                .name("")
                .age(20)
                .address("시흥시")
                .phone("010-9999-8888")
                .build();

        memberRepository.save(member);
    }

    @Test
    public void updateTest() {
        Member member = memberRepository.findById(1).orElseThrow();
        member.setName("하늘");

        memberRepository.save(member);
    }

    @Test
    public void deleteTest() {
        memberRepository.deleteById(10);

    }

    @Test
    public void selectAllTest() {
        List<Member> memberList = memberRepository.findAll();

        memberList.forEach(m -> log.info(m));
    }
}