package br.com.wnfa.alurachallenge.config.property;

import java.util.ArrayList;
import java.util.List;

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
		private String originsAllowed;
		private String secretKeyApp;
		private Integer accessTokenValidity;
		private Integer refreshTokenValidity;
		private String clientWeb;
		private String secretKeyClientWeb;
		private String clientMobile;
		private String secretKeyClientMobile;
		
		public List<String> getOrigins() {
			List<String> origins = new ArrayList<>();
			String[] origin = this.originsAllowed.split(",");
			for (int i = 0; i < origin.length; i++) {
				origins.add(origin[i]);
			}
			return origins;
		}
	}
}
