package spring.boot.payload.request;



public class ProductRequest {
	
	private String name;
	private Double Price;
	private int Qt;
	private String cat;
	private int averageStar ;
	private String  createdAt ;
	private Long createdBy;
	private boolean publish ;
	private String desc;
	private float Descount;
	private String category ;
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
	public boolean isPublish() {
		return publish;
	}
	public void setPublish(boolean publish) {
		this.publish = publish;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getDescount() {
		return Descount;
	}
	public void setDescount(float descount) {
		Descount = descount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	

}
