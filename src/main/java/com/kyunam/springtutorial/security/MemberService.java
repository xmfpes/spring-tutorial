package com.kyunam.springtutorial.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kyunam.springtutorial.domain.Member;
import com.kyunam.springtutorial.domain.MemberRepository;
import com.kyunam.springtutorial.web.UserController;

import groovy.util.logging.Log;

@Service
@Log
public class MemberService implements UserDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member member = memberRepository.findByUid(username);
		if(member != null) {
			logger.info("no member");
			return new MemberSecurityUser(member);
		}
		return null;
	}

}
