package logic;

public class Player {
	
	private String name, email, ip;
	private int port;
	
	public Player(String name) {
		this.setName(name);
		this.setEmail(email);
		this.setIp(ip);
		this.setPort(port);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String toString() {
		return this.getName();
	}
	


}
