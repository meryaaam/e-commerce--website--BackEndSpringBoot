package spring.boot.payload.response;

import java.util.List;

import spring.boot.entity.Image;


public class ImagesResponse {
	
	private String name;
	private Double Price;
	private int Qt;

	private int averageStar ;
	private String  createdAt ;
	private Long createdBy;

	private List<Image> image ;

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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

	public ImagesResponse(String name, Double price, int qt,  int averageStar, String createdAt,
			Long createdBy, List<Image> image) {
		super();
		this.name = name;
		Price = price;
		Qt = qt;

		this.averageStar = averageStar;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.image = image;
	}

	
	
}
