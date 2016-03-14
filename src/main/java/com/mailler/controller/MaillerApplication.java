package com.mailler.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.context.config.annotation.EnableContextCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@SpringBootApplication
@EnableContextCredentials
public class MaillerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaillerApplication.class, args);
	}
	
	@Bean
	public ViewResolver viewResolver() {
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    return viewResolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.addTemplateResolver(emailTemplateResolver());
	    templateEngine.addTemplateResolver(webTemplateResolver());
	    templateEngine.addTemplateResolver(fileTemplateResolver());
	    return templateEngine;
	}
	
	private TemplateResolver emailTemplateResolver() {
	    TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("/mail/");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setOrder(1);
	    return templateResolver;
	}
	
	private TemplateResolver webTemplateResolver() {
	    TemplateResolver templateResolver = new ClassLoaderTemplateResolver();
	    templateResolver.setPrefix("/WEB-INF/templates/");
	    templateResolver.setTemplateMode("HTML5");
	    templateResolver.setOrder(2);
	    return templateResolver;
	}

	private ITemplateResolver fileTemplateResolver() {
		TemplateResolver templateResolver = new FileTemplateResolver();
		templateResolver.setPrefix("mail/");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(3);
		return templateResolver;
	}
}
