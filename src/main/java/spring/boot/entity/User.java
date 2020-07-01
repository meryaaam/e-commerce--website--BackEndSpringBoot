package spring.boot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;



import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;

import java.util.Set;

@Entity
@Table(	name = "users" )

public class User {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	private Long id;

	
	
	 @Column(name = "USERNAME", length = 50, unique = true)
	 
	    @NotNull(message = "Username is required .")
	    @Size(min = 4, max = 50)
	    private String username;
	 
	 
	 @Column(name = "EMAIL", length = 50 , unique = true)
	    @Email
	    @NotNull(message = "Email is required .")
	    @Size(min = 4, max = 50)
	    private String email;
	 
	 
	    @JsonIgnore
	    @Column(name = "PASSWORD", length = 100)
	    @NotNull(message = "Password is required .")	
	    @Size(min = 4, max = 100)
	    private String password;

	
	  @Column(name = "FIRSTNAME", length = 50)
	  @Size(min = 4, max = 50) private String firstname;

	  
	  @Column(name = "LASTNAME", length = 50)
	  @Size(min = 4, max = 50) private String lastname;
	 

	  @Column(name = "Birthday", length = 50)
	 private String birthday;

	  @Column(name = "Adresse", length = 50)
		 private String Adr;
	  
	  @Column(name = "Telephone", length = 8)
		 private String tlf;
	  

	    @ManyToMany
	    @JoinTable(
	            name = "user_roles",
	            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
	            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "ID")})
	    @BatchSize(size = 20)
	    private Set<Role> Roles = new HashSet<>();

    
		  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
		            cascade = CascadeType.ALL)
		  @OnDelete(action = OnDeleteAction.CASCADE)
		    private Set<ImageUser> imageuser = new HashSet<>();
		  
		  
	public User() {
	}

	public Set<ImageUser> getImageuser() {
		return imageuser;
	}

	public void setImageuser(Set<ImageUser> imageuser) {
		this.imageuser = imageuser;
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	

	public User( String username,  String email,String password,  String firstname, String lastname) {
		
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	
	  public String getFirstname() { return firstname; }
	  
	  public void setFirstname(String firstname) { this.firstname = firstname; }
	 
	
	  public String getLastname() { return lastname; }
	  
	  public void setLastname(String lastname) { this.lastname = lastname; }
	 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	/*
	public User(@NotNull(message = "Username is required .") @Size(min = 4, max = 50) String username,
			@Email @NotNull(message = "Email is required .") @Size(min = 4, max = 50) String email,
			@NotNull(message = "Password is required .") @Size(min = 4, max = 100) String password,
			@Size(min = 4, max = 50) String firstname, @Size(min = 4, max = 50) String lastname, String birthday,
			String adr, String tlf) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		Adr = adr;
		this.tlf = tlf;
	}
*/
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return Roles;
	}

	public void setRoles(Set<Role> roles) {
		this.Roles = roles;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAdr() {
		return Adr;
	}

	public void setAdr(String adr) {
		Adr = adr;
	}

	public String getTlf() {
		return tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public User(  String email,
			 String password,
			 String firstname, String lastname, String birthday,
			String adr, String tlf) {

		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		Adr = adr;
		this.tlf = tlf;
	}

	public User(String email,
			String firstname, 
			String lastname, String birthday,
			String adr, String tlf) {
	
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		Adr = adr;
		this.tlf = tlf;
	}
	
	
	
}
