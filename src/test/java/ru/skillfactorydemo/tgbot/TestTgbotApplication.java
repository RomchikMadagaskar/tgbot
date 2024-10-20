package ru.skillfactorydemo.tgbot;

import org.springframework.boot.SpringApplication;

public class TestTgbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TgbotApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
