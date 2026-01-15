package com.example.jpa2.service;

import com.example.jpa2.domain.Member;
import com.example.jpa2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final MemberRepository memberRepository;


    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }


    public void insert(Member member) {
        memberRepository.save(member);
    }

    public void delete(int memberId) {
        memberRepository.deleteById(memberId);
    }

    public Member findById(int memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    public void update(Member member) {
        memberRepository.save(member);
    }

}
