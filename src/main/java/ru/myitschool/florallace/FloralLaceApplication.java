package ru.myitschool.florallace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.myitschool.florallace.service.ProductsDemoService;

@SpringBootApplication
public class FloralLaceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FloralLaceApplication.class, args);

		context.getBean(ProductsDemoService.class).userDemo();

		context.close();
	}

}
