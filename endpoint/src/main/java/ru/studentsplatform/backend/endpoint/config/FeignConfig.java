package ru.studentsplatform.backend.endpoint.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;


import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Класс конфигурации feign. Устанавливает HTTP Proxy для выполнения запросов.
 */
//@Configuration     <-- раскомментировать, если планируется использовать proxy!
public class FeignConfig {

	private String ip = Proxies.P1.getIp();
	private Integer port = Proxies.P1.getPort();



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
