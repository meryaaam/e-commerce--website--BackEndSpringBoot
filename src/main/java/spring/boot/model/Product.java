package spring.boot.model;
import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
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
	
	

	@Column(name = "Image")
	private byte[] img;
	     
	
	
	@NotNull(message = "Product createdAt ?")
    @Column(name = "createdAt")
    private Date  createdAt ;


	public Product( String name,  float price,  int qt,  String cat,   Date createdAt
			, int averageStar, byte[] img) {
	
		this.name = name;
		Price = price;
		Qt = qt;
		this.cat = cat;
		this.averageStar = averageStar;
		this.img = img;
		this.createdAt = createdAt;
	}


	public Product(long id,  String name,  float price,  int qt,  String cat,
			 int averageStar, byte[] img,  Date createdAt) {
	
		this.id = id;
		this.name = name;
		Price = price;
		Qt = qt;
		this.cat = cat;
		this.averageStar = averageStar;
		this.img = img;
		this.createdAt = createdAt;
	}


	public Product() {

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


	public byte[] getImg() {
		return img;
	}


	public void setImg(byte[] img) {
		this.img = img;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", Price=" + Price + ", Qt=" + Qt + ", cat=" + cat
				+ ", averageStar=" + averageStar + ", img=" + img + ", createdAt=" + createdAt + "]";
	}



		
		

	

}
