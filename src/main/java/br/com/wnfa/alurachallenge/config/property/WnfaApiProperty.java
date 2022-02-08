package br.com.wnfa.alurachallenge.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("wnfa")
public class WnfaApiProperty {
	
	private final Security security = new Security();
	
	public Security getSecurity() {
		return security;
	}

	public static class Security {
		private boolean enableHttps;
		
		public boolean isEnableHttps() {
			return enableHttps;
		}
		
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
}