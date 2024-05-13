package tw.team.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@ServletComponentScan
public class Springboot3HotelApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot3HotelApplication.class, args);
	}

}
