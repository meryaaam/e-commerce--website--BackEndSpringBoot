package spring.boot.entity;


import javax.persistence.*;

@Entity
@Table(name = "roles")



public class Role {
	
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ1")
    @SequenceGenerator(name = "USER_SEQ1", sequenceName = "USER_SEQ1", allocationSize = 1)

	private Integer id;

	
	@Enumerated(EnumType.STRING)
	@Column(name = "name" ,length = 20)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}