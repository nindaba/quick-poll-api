package com.yadlings.restfull.Service;

import com.yadlings.restfull.Domain.User;
import com.yadlings.restfull.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public void initializeUsers() {
        userRepository.saveAll(users());
    }
    public List users(){
        return
            Arrays.asList(
                new User("dxvytertdhvnnvhy","A","B","ab",true, new BCryptPasswordEncoder().encode("ab123456")),
                new User("gthgygtygtyjngyu","C","D","cd",false,new BCryptPasswordEncoder().encode("cd123456")),
                new User("sxseddcvhfvnkkmj","D","F","df",false,"ab123456"),
                new User("wertyhvbjuiknkjk","E","G","eg",false,"ab123456"),
                new User("zxcvbnjytrertyui","F","H","fh",true,"ab123456")
            );
    }
}
