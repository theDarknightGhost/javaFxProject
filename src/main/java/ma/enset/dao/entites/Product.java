package ma.enset.dao.entites;

import java.io.Serializable;

public class Product implements Serializable {
    private int id ;
    private String designation ;
    private float prix ;
    private String reference ;
    private Category cat ;

    public Product(int id, String designation, float prix, String reference, Category cat) {
        this.id=id;
        this.designation = designation;
        this.prix = prix;
        this.reference = reference;
        this.cat = cat;
    }
public  Product(){

}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    @Override
    public String toString() {
        return "Product {" +
                "designation='" + designation + '\'' +
                ", prix=" + prix +
                ", reference=" + reference +
                ", cat=" + cat +
                '}';
    }
}
