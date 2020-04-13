package com.banque.db;

public class ConnexionParams {

	private String driver;
	
	private String url;
	
	private String pwd;
	
	private String login;
	
	public ConnexionParams(String driver, String url, String login, String pwd) {
		this.driver = driver;
		this.url = url;
		this.login = login;
		this.pwd = pwd;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("{driver").append(driver).append(", ");
		sb.append("url = ").append(url).append(", ");
		sb.append("pwd = ").append(pwd).append(", ");
		sb.append("login = ").append(login).append("} ");
		return sb.toString();
	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
	
}
