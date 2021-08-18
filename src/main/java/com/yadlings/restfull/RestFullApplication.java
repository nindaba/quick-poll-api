package com.yadlings.restfull;

import com.yadlings.restfull.Domain.User;
import com.yadlings.restfull.Repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Log4j2
@SpringBootApplication
public class RestFullApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestFullApplication.class, args);
	}
	@Autowired
	private UserRepository userRepository;

	@Bean
	public User user(){
		User user = new User();
		user.setFirstName("a");
		user.setUsername("as5");
		user.setAdmin(true);
		user.setPassword(new BCryptPasswordEncoder().encode("as1234567"));
		user.setLastName("as");
//		log.info("User Created {}",userRepository.save(user));
		return  user;
	}
}

