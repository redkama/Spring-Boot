package com.example.jpa2.controller;

import com.example.jpa2.domain.Member;
import com.example.jpa2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public String getList(
            @PageableDefault(size = 3, sort = "memberId", direction = Sort.Direction.DESC)
            Pageable pageable, Model model) {

        Page<Member> memberList = memberService.findAll(pageable);
        model.addAttribute("memberList", memberList);

        return "members/list";

    }

    @GetMapping("/new")
    public void getNew(Model model) {
        model.addAttribute("member", new Member());

    }

    @PostMapping("/new")
    public String postNew(Member member) {
        memberService.insert(member);
        return "redirect:/members/list";
    }

    @PostMapping("/delete/{id}")
    public String postDelete(@PathVariable("id") int memberId) {
        memberService.delete(memberId);
        return "redirect:/members/list";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable("id") int memberId, Model model) {
        Member member = memberService.findById(memberId);
        model.addAttribute("member", member);
        return "members/edit";
    }

    @PostMapping("/edit/{id}")
    public String postEdit(@PathVariable("id") int memberId, Member member) {
        Member oldMember = memberService.findById(memberId);

        oldMember.setName(member.getName());
        oldMember.setAge(member.getAge());
        oldMember.setAddress(member.getAddress());
        oldMember.setPhone(member.getPhone());

        memberService.update(oldMember);
        return "redirect:/members/list";
    }



}
