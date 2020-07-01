package spring.boot.payload.request;

import spring.boot.entity.Category;
import spring.boot.entity.Image;
import spring.boot.entity.Product;

public class ProductInfo {
	
	
	private Product p ;
	private Category category  ;
	private Image image ;
	
	
	public Product getProduct() {
		return p;
	}


	public void setP(Product p) {
		this.p = p;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category name) {
		this.category = name;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public ProductInfo(Product p, Category name) {
		super();
		this.p = p;
		this.category = name;
	}
	
	

}
