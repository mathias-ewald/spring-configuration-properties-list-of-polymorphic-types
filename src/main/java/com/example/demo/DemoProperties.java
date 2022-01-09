package com.example.demo;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "demo")
@Data
public class DemoProperties {

	private List<SelectorProperties> selectors;
	
	@PostConstruct
	private void print() {
		System.out.println(this);
	}

}
