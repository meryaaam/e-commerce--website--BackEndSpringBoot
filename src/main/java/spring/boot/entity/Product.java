package spring.boot.model;


import java.util.Arrays;
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

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;



@Entity(name = "Product")
@Table(name = "Products")
public class Product {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "P_SEQ")
    @SequenceGenerator(name = "P_SEQ", sequenceName = "P_SEQ", allocationSize = 1)
	private long id;

	
	@NotNull(message = "Product name is required.")
	@Column(name = "Pname" )
	private String name;
	
	@NotNull(message = "Product price is required.")
	@Column(name = "Pprice")
	private float Price;

	@NotNull(message = "Product quantity is required.")
	@Column(name = "Qt")
	private int Qt;
	
	@NotNull(message = "Product category is required.")
	@Column(name = "Pcat")
	private String cat;
	

	@Column(name = "Star")
	private int averageStar ;
	

	//@Column(name = "Image")
	//	private String Image;
	@NotNull
    @Column(name = "created_At")
    private String  createdAt ;
	
	
	@NotNull
	@Column(name = "CreatedBy")
		private Long createdBy;
	
	
	@Column(name = "publish")
	private boolean publish ;
	
	
	

	@Column(name = "Amount")
	private int amount;
	
	@Column(name = "description")
	private String desc;
	
	
	  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL)
	    private Set<Image> images = new HashSet<>();
	
	
	
	
	
	
	
	public Product( String name, float price, int qt,String cat, int averageStar,  String createdAt,  Long createdBy, boolean publish, int amount, String desc) {
		
		this.name = name;
		Price = price;
		Qt = qt;
		this.cat = cat;
		this.averageStar = averageStar;
	
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.publish = publish;
		this.amount = amount;
		this.desc = desc;
	}


	public boolean isPublish() {
		return publish;
	}


	public void setPublish(boolean publish) {
		this.publish = publish;
	}


	public Product( String name,float price, int qt,String cat, int averageStar, String createdAt,  Long createdBy, int amount, String desc) {
	
		this.name = name;
		Price = price;
		Qt = qt;
		this.cat = cat;
		this.averageStar = averageStar;
		//Image = image;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.amount = amount;
		this.desc = desc;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Product() {
	
	}


	public Product( String name,float price, int qt, String cat, int averageStar, 
		 String createdAt  , String desc) {
		
		this.name = name;
		Price = price;
		Qt = qt;
		this.cat = cat;
		this.averageStar = averageStar;
		//Image = image;
		this.createdAt = createdAt; 
		this.desc = desc ;
	
	}


	public Product( String name, float price,int qt,String cat, int averageStar, String createdAt, Long createdBy) {
	
		this.name = name;
		Price = price;
		Qt = qt;
		this.cat = cat;
		this.averageStar = averageStar;
		//Image = image;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}





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


	public float getPrice() {
		return Price;
	}


	public void setPrice(float price) {
		Price = price;
	}


	public int getQt() {
		return Qt;
	}


	public void setQt(int qt) {
		Qt = qt;
	}


	public String getCat() {
		return cat;
	}


	public void setCat(String cat) {
		this.cat = cat;
	}


	public int getAverageStar() {
		return averageStar;
	}


	public void setAverageStar(int averageStar) {
		this.averageStar = averageStar;
	}


	//public String getImage() {
		//return Image;
	//}


	//public void setImage(String image) {
	//	Image = image;
//	}


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



	

}