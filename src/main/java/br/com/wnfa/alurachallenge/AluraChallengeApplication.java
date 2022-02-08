package br.com.wnfa.alurachallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.wnfa.alurachallenge.config.property.WnfaApiProperty;

import javax.annotation.PostConstruct;
import java.util.Locale;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(WnfaApiProperty.class)
public class AluraChallengeApplication {
	private static final String TIME_ZONE = "America/Sao_Paulo";

	public static void main(String[] args) {
		SpringApplication.run(AluraChallengeApplication.class, args);
	}
	
	/*
	 * Método responsável por fixar a região que será tratato as datas, numero, etc
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@PostConstruct
	void started(){
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
	}
}
