package ru.studentsplatform.backend.service.proxy;

public enum Proxies {

	P1("166.98.140.51", 3128), P2("5.249.152.109", 5836);

	private String ip;
	private Integer port;

	private static int counter = 0;

	private static Proxies[] proxyArray = values();

	/**
	 * Конструктор.
	 * @param ip 	IP прокси сервера
	 * @param port 	Порт прокси сервера
	 */
	Proxies(String ip, Integer port) {
		this.ip = ip;
		this.port = port;
	}

	/**
	 * Возвращает следующий объект Proxies в массиве proxyArray.
	 * Цикличен, сохраняет состояние.
	 * @return следующий Proxies в proxyArray
	 */
	public static Proxies next() {
		return proxyArray[(++counter) % proxyArray.length];
	}

	/**
	 * Возвращает текущий объект Proxies в массиве proxyArray.
	 * @return текущий Proxies в proxyArray
	 */
	public static Proxies current() {
		return proxyArray[(counter) % proxyArray.length];
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
