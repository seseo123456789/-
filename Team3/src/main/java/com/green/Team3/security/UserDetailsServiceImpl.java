package com.green.Team3.security;

import com.green.Team3.member.service.MemberServiceImpl;
import com.green.Team3.member.vo.MemberVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource(name = "memberService")
    private MemberServiceImpl memberService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO loginInfo = memberService.login(username);
        User user = (User) User.builder()
                .username(loginInfo.getMemberId())
                .password(loginInfo.getMemberPw())
                .roles(String.valueOf(loginInfo.getMemberRoll())).build();
        return user;
    }
}
