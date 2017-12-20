package beans;

public class Product {
	 
	   private String code; //len = 10		not null
	   private String name; //len = 30		not null
	   private float price; //len = 10(,2)
	   private float mass; // len = 10(,2)
	 //Select a.cod_product, a.name_product, a.price, a.average_mass from products a 
	   public Product() {
	 
	   }
	 
	   public Product(String code, String name, float price, float mass) {
	       this.code = code;
	       this.name = name;
	       this.price = price;
	       this.mass = mass;
	   }
	 
	   public String getCode() {
	       return code;
	   }
	 
	   public void setCode(String code) {
	       this.code = code;
	   }
	 
	   public String getName() {
	       return name;
	   }
	 
	   public void setName(String name) {
	       this.name = name;
	   }
	 
	   public float getPrice() {
	       return price;
	   }
	 
	   public void setPrice(float price) {
	       this.price = price;
	   }

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}
	 
	}
