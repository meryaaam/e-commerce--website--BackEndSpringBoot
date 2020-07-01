package spring.boot.payload.response;

import spring.boot.entity.Image;
import spring.boot.entity.Product;

public class ImageResponse {
	
	private Product product ; 
	private Image image ;
	public ImageResponse(Product product, Image image) {
		super();
		this.product = product;
		this.image = image;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	
	

}
