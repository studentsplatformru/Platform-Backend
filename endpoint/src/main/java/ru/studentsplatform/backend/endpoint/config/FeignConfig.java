package ru.studentsplatform.backend.endpoint.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Класс конфигурации feign. Устанавливает HTTP Proxy для выполнения запросов.
 */
@Configuration
public class FeignConfig {

	@Value("${feign.proxy.ip}")
	private String ip;
	@Value("${feign.proxy.port}")
	private Integer port;

	/**
	 * Метод настройки proxy.
	 * @return настройка feign client
	 */
	@Bean
	public feign.okhttp.OkHttpClient okHttpClient() {
		Proxy newproxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
		OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(newproxy).build();
		return new feign.okhttp.OkHttpClient(okHttpClient);
		//"166.98.140.51" 3128
	}
}
