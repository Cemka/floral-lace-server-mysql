package ru.myitschool.florallace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.servlet.function.ServerResponse;
import ru.myitschool.florallace.service.ProductsDemoService;

import javax.naming.Context;

@SpringBootApplication
public class FloralLaceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(FloralLaceApplication.class, args);
		context.getBean(ProductsDemoService.class).userDemo();

	}

}
