package spring.boot.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity(name = "Category")
@Table(name = "Category")
public class Category {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "C_SEQ")
    @SequenceGenerator(name = "C_SEQ", sequenceName = "C_SEQ", allocationSize = 1)
	private long id;

	
	@NotNull(message = "Category name is required.")
	@Column(name = "Name" )
	private String name;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Category(long id, @NotNull(message = "Category name is required.") String name) {
	
		this.id = id;
		this.name = name;
	}


	public Category() {
		super();
	}


	public Category(@NotNull(message = "Category name is required.") String name) {
		super();
		this.name = name;
	}
	
	
	  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<Product> products = new HashSet<>();


	public Category(long id, @NotNull(message = "Category name is required.") String name, Set<Product> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}


	public Category(@NotNull(message = "Category name is required.") String name, Set<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}


	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	  
}
