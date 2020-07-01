package spring.boot.payload.response;

import java.util.List;

import spring.boot.entity.Category;
import spring.boot.entity.Image;
import spring.boot.entity.Product;

public class ProductResponse {
	
	private Product product ;

	private String category ;
	


	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	
	public ProductResponse(Product product, String category) {
		super();
		this.product = product;
		this.category = category;
	}
	

}
