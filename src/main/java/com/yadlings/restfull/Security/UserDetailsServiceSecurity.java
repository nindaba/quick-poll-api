package com.yadlings.restfull.Security;

import com.yadlings.restfull.Domain.User;
import com.yadlings.restfull.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Log4j2
public class UserDetailsServiceSecurity implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) {
        return
        userRepository.findByUsername(s)
                .map(user -> userDetails(
                        grantedAuthorities(user),
                        user)
                )
                .orElseThrow(()->new UsernameNotFoundException("Incorrect Username "+s));
    }
    private List<GrantedAuthority> grantedAuthorities(User user){
        return user.getAdmin() ? AuthorityUtils.createAuthorityList("ROLE_ADMIN") : new ArrayList<GrantedAuthority>();
    }
    private UserDetails userDetails(List auth,User user){
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), auth);
    }
}
