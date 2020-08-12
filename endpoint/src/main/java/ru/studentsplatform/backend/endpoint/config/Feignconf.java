package ru.studentsplatform.backend.endpoint.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class Feignconf {
	@Bean
	public feign.okhttp.OkHttpClient okHttpClient() {
		Proxy newproxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("166.98.140.51", 3128));
		OkHttpClient okHttpClient = new OkHttpClient.Builder().proxy(newproxy).build();
		return new feign.okhttp.OkHttpClient(okHttpClient);
	}
}
