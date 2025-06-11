package io.github.sriSubramaniaRam.printer_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PrinterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrinterServiceApplication.class, args);//
	}

}
