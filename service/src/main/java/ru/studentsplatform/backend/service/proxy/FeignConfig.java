package ru.studentsplatform.backend.service.proxy;

import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import okhttp3.OkHttpClient;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Класс-билдер для feign http client.
 */
public class FeignConfig {

	private static SpbuProxy spbuProxy;

	static {
		instantiateFeignHttpClient(Proxies.current());
	}

	/**
	 * Собирает и возвращает feign http client, устанавливая заданный прокси сервер.
	 *
	 * @param proxy Прокси, который будет установлен для собранного feign client
	 */
	private static void instantiateFeignHttpClient(Proxies proxy) {
		Proxy newProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxy.getIp(), proxy.getPort()));
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
//				.proxy(newProxy)
				.build();

		spbuProxy = Feign.builder().client(new feign.okhttp.OkHttpClient(okHttpClient))
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.contract(new SpringMvcContract())
				.options(new Request.Options(5, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, true))
				.target(SpbuProxy.class, "https://timetable.spbu.ru/api/v1/");
	}

	/**
	 * Устанавливает новый feign client с прокси сервером, находящимся следущим в Proxies Enum.
	 */
	public static void changeProxy() {
		instantiateFeignHttpClient(Proxies.next());
	}

	/**
	 * feign client getter метод.
	 *
	 * @return СПБГУ feign client с прокси сервером
	 */
	public static SpbuProxy getSpbuProxy() {
		return spbuProxy;
	}
}
