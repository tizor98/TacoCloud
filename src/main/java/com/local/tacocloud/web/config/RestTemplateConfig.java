package com.local.tacocloud.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
// Config for unsecured restTemplate - aka restTemplate making request to HTTPS endpoints without signature verification
public class RestTemplateConfig {

   @Bean
   public RestTemplate restTemplate() {
      TrustManager[] trustAllCerts = new TrustManager[]{
         new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
               return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
         }
      };

      SSLContext sslContext = null;
      try {
         sslContext = SSLContext.getInstance("SSL");
         sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
      } catch (NoSuchAlgorithmException | KeyManagementException e) {
         e.printStackTrace();
      }

      CloseableHttpClient httpClient = HttpClients.custom()
         .setSSLContext(sslContext)
         .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
         .build();

      HttpComponentsClientHttpRequestFactory requestFactory =
         new HttpComponentsClientHttpRequestFactory();
      requestFactory.setHttpClient(httpClient);

      return new RestTemplate(requestFactory);
   }

}
