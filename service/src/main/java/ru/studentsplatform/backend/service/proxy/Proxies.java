package ru.studentsplatform.backend.service.proxy;

public enum Proxies {

	P1("191.233.198.18", 80), P2("68.183.121.227", 3128), P3("61.252.54.66", 3128),
	P4("81.201.60.130", 80);

	private static int counter = 0;
	private static Proxies[] proxyArray = values();
	private String ip;
	private Integer port;

	/**
	 * Конструктор.
	 *
	 * @param ip   IP прокси сервера
	 * @param port Порт прокси сервера
	 */
	Proxies(String ip, Integer port) {
		this.ip = ip;
		this.port = port;
	}

	/**
	 * Возвращает следующий объект Proxies в массиве proxyArray.
	 * Цикличен, сохраняет состояние.
	 *
	 * @return следующий Proxies в proxyArray
	 */
	public static Proxies next() {
		return proxyArray[(++counter) % proxyArray.length];
	}

	/**
	 * Возвращает текущий объект Proxies в массиве proxyArray.
	 *
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
