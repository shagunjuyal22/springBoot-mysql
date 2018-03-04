package com.mysql.mysqlproject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mysql.mysqlproject.repository.EntityRepository;

@SpringBootApplication
public class MysqlProjectApplication {

	public static void main(String[] args) throws BeansException, NoSuchAlgorithmException, IOException {
		ConfigurableApplicationContext context = SpringApplication.run(MysqlProjectApplication.class, args);
		//context.getBean(EntityRepository.class).getTeamById(101);
		context.getBean(EntityRepository.class).getTeilProjekteById(301);
		
	}
}
