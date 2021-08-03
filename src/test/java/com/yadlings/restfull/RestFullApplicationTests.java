package com.yadlings.restfull;

import com.yadlings.restfull.Exception.RestException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
@Log4j2
class RestFullApplicationTests {

	@Test
	void contextLoads() {
	}
	@Test void Bcrypt(){
		log.info("Nindaba becomes {}",new BCryptPasswordEncoder().encode("Nindaba"));
	}
}
