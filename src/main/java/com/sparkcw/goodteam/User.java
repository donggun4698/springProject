package com.sparkcw.goodteam;


public class User {
	private String id;
	private String name;
	private String password;
	private String email;
	private int level = 1;  //처음 레벨
	private String authority;
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User() {
		super();
	}
	
	public User(String id, String name, String password, String email, int level ) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.level = level;

	}
	
	public User(String id, String name, String password, String email, int level , String authority) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.level = level;
		this.authority = authority;
	}

	public User(String id,String password , String authority) {
		this.id = id;
		this.password = password;
		this.authority = authority;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
