package spring.boot.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private String Lastname ; 
	private String firstname ; 


	/*
	 * public JwtResponse(String accessToken, Long id, String username, String
	 * email, String fs , String ls , List<String> roles ) { this.token =
	 * accessToken; this.id = id; this.username = username; this.email = email;
	 * this.roles = roles; this.firstname=fs ; this.Lastname = ls ;
	 * 
	 * }
	 */
	
	public JwtResponse(String accessToken, Long id, String username,  String email,String firstname , String Lastname ,List<String> roles ) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.firstname = firstname ; 
	    this.Lastname = Lastname ; 

	}
	
	public JwtResponse( Long id, String username,  String email,String firstname , String Lastname ) {

		this.id = id;
		this.username = username;
		this.email = email;
	
		this.firstname = firstname ; 
	    this.Lastname = Lastname ; 

	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		this.Lastname = lastname;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
