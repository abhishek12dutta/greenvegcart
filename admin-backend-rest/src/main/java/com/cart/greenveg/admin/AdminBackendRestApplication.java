package com.cart.greenveg.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AdminBackendRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminBackendRestApplication.class, args);
	}

}
//@SpringBootApplication
//public class AdminBackendRestApplication extends SpringBootServletInitializer {
//	public static void main(String[] args) {
//		SpringApplication.run(AdminBackendRestApplication.class, args);
//	}
//
//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(AdminBackendRestApplication.class);
//	}
//}