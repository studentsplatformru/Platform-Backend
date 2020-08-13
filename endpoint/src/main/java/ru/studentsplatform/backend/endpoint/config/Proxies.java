package ru.studentsplatform.backend.endpoint.config;

public enum Proxies {
	P1("166.98.140.51", 3128), P2("95.216.68.90", 3128);
	private String ip;
	private Integer port;

	Proxies(String ip, Integer port) {
		this.ip = ip;
		this.port = port;
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
