package com.hvaghani.ecommerce.ecommerce_restapi;

import org.springframework.boot.SpringApplication;

public class TestEcommerceRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.from(EcommerceRestapiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
