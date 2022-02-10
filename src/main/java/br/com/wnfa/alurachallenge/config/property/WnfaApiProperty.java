package br.com.wnfa.alurachallenge.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("wnfa")
public class WnfaApiProperty {
	
	private final Security security = new Security();
	
	public Security getSecurity() {
		return security;
	}

	@Getter
	@Setter
	public static class Security {
		private boolean enableHttps;
		private String secretKeyApp;
		private Integer accessTokenValidity;
		private Integer refreshTokenValidity;
		private String clientWeb;
		private String secretKeyClientWeb;
		private String clientMobile;
		private String secretKeyClientMobile;		
	}
}
