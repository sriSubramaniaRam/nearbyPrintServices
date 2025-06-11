package io.github.sriSubramaniaRam.printer_service;

import org.springframework.boot.SpringApplication;

public class TestPrinterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PrinterServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
