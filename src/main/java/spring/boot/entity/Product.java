package spring.boot.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity(name = "Product")
@Table(name = "Products")
public class Product implements Serializable {

    private static final long serialVersionUID = 476151177562655457L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "P_SEQ")
    @SequenceGenerator(name = "P_SEQ", sequenceName = "P_SEQ", allocationSize = 1)
	private long id;

	
	@NotNull(message = "Product name is required.")
	@Column(name = "Name" )
	private String name;
	
	@NotNull(message = "Product price is required.")
	@Column(name = "Price")
	private Double Price;

	@NotNull(message = "Product quantity is required.")
	@Column(name = "QuantitytInStock")
	private int Qt;
	
	//@NotNull(message = "Product category is required.")
	//@Column(name = "Pcat")
	//private String cat;
	

	@Column(name = "Star")
	private int averageStar ;
	


	
    @Column(name = "created_At")
	//@JsonFormat(pattern = "dd/MM/yyyy") 
	private String  createdAt ;

	
	@NotNull
	@Column(name = "CreatedBy")
		private Long createdBy;
	
	
	@Column(name = "publish")
	private boolean publish ;
	

	@Column(name = "description")
	private String desc;
	
	

	@Column(name = "Descount")
	private float Descount;
	
	
	
	  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Set<Image> images = new HashSet<>();
	  
	  
	  
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "category_id", nullable = false)
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    @JsonIgnore
	    private Category category;
	

		
	
	
	
	//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    //@JoinColumn(name = "category_id", nullable = false)
	   // @OnDelete(action = OnDeleteAction.CASCADE)
	   // @JsonIgnore
	  //  private Category category;
	
	
	
	public Product( String name, Double price, int qt, int averageStar,
			String createdAt,  Long createdBy, boolean publish,  String desc) {
		
		this.name = name;
		Price = price;
		Qt = qt;
	
		this.averageStar = averageStar;
	
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.publish = publish;
	
		this.desc = desc;
	}


	public boolean isPublish() {
		return publish;
	}


	public void setPublish(boolean publish) {
		this.publish = publish;
	}


	public Product( String name,Double price, int qt, int averageStar, 
			String createdAt,  Long createdBy,  String desc) {
	
		this.name = name;
		Price = price;
		Qt = qt;
		
		this.averageStar = averageStar;
		//Image = image;
		this.createdAt = createdAt;
		this.createdBy = createdBy;

		this.desc = desc;
	}





	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Product() {
	
	}


	public Product( String name,Double price, int qt,  int averageStar, 
			String createdAt  , String desc) {
		
		this.name = name;
		Price = price;
		Qt = qt;
		this.averageStar = averageStar;
		//Image = image;
		this.createdAt = createdAt; 
		this.desc = desc ;
	
	}


	public Product( String name, Double price,int qt, int averageStar,
			String createdAt, Long createdBy) {
	
		this.name = name;
		Price = price;
		Qt = qt;
		
		this.averageStar = averageStar;
		//Image = image;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}





	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getPrice() {
		return Price;
	}


	public void setPrice(Double price) {
		Price = price;
	}


	public int getQt() {
		return Qt;
	}


	public void setQt(int qt) {
		Qt = qt;
	}



	public int getAverageStar() {
		return averageStar;
	}


	public void setAverageStar(int averageStar) {
		this.averageStar = averageStar;
	}


	public Long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "" + id + "";
	}


	public float getDescount() {
		return Descount;
	}


	public void setDescount(float descount) {
		Descount = descount;
	}


	public Set<Image> getImages() {
		return images;
	}


	public void setImages(Set<Image> images) {
		this.images = images;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Product(String name,
			 Double price, int qt,String cat, int averageStar, 
			 String createdAt,
			 Long createdBy, 
			Category category) {
	
		this.name = name;
		Price = price;
		Qt = qt;
	
		this.averageStar = averageStar;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.category = category;
	}


public void setCatId() {
	this.category.getId() ; }




	

}