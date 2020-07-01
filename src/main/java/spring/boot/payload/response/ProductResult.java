package spring.boot.payload.response;

import spring.boot.entity.Product;

public class ProductResult {
 Product product ;

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public ProductResult(Product product) {
	super();
	this.product = product;
}
	
}
