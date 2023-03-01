package ma.enset.dao.entites;

import java.io.Serializable;

public class Category implements Serializable {
    private int id ;
    private String nomCat ;

     public Category(){

     }
     public Category(String nomCat){
         this.nomCat=nomCat;
     }
     public Category(int id , String nomCat){
     this.id= id ;
     this.nomCat=nomCat ;
     }
     public  Category(int id){
         this.id=id;
     }
    public int getId() {
        return id;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

    @Override
    public String toString() {
        return nomCat ;
    }


}
