package com.springboot;

import com.springboot.dao.UserRepository;
import com.springboot.entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableCaching
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
//
//		User user2 =  new User();
//		user2.setName("A");
//		user2.setCity("B");
//		user2.setStatus("earning");
//		//User user3 = userRepository.save(user2);
//		System.out.println(user2);
//
//
//		User user4 =  new User();
//		user4.setName("C");
//		user4.setCity("D");
//		user4.setStatus("procrastinating");
//		//User user5 = userRepository.save(user4);
//		System.out.println(user4);
//
//		List<User> res = Arrays.asList(user4, user2);
//
//		Iterable<User> val = userRepository.saveAll(res);
//
//		System.out.println(val);

//		Optional<User> optional = userRepository.findById(16);
//		User user = optional.get();
//		user.setName("new name");
//		System.out.println(user);
//
//		User save = userRepository.save(user);

//		List<User> allUser = userRepository.getAllUser();
//
//		allUser.forEach(e->{
//			System.out.println(e);
//		});
//
//		List<User> name = userRepository.findByName("A");
//		System.out.println(name);

	}

}
