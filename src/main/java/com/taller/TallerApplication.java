package com.taller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taller.database.DataBaseManager;

@SpringBootApplication
public class TallerApplication implements CommandLineRunner{

	@Autowired
	private DataBaseManager dataBaseManager;

	public static void main(String[] args) {
		SpringApplication.run(TallerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataBaseManager.crearTablas();
	}

}
