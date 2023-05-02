package ru.myitschool.florallace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.UserRepository;
import ru.myitschool.florallace.service.DemoService;

import java.util.List;

@SpringBootApplication
public class FloralLaceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FloralLaceApplication.class, args);

	}

}
