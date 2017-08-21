package com.kyunam.springtutorial.web;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kyunam.springtutorial.domain.Member;
import com.kyunam.springtutorial.domain.MemberRepository;
import com.kyunam.springtutorial.domain.MemberRole;


@Controller
@RequestMapping("/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/page-login";
	}
	
	@GetMapping("/registerForm")
	public String registerForm() {
		return "/user/page-register";
	}
	
	@PostMapping("")
	public String register(Member member) {
		member.setUpw((new BCryptPasswordEncoder().encode(member.getUpw())));
		MemberRole role = new MemberRole();
		role.setRoleName("ADMIN");
		member.setRoles(Arrays.asList(role));
		logger.info("userdata : " + member);
		memberRepository.save(member);
		return "redirect:/";
	}
}
