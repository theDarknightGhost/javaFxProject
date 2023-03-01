package ma.enset.dao.ProductHandler;

import ma.enset.dao.BD.ConnexionBD;
import ma.enset.dao.entites.Category;
import ma.enset.dao.entites.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao{
    @Override
    public Product findById(int id) {
        Product p = new Product();
        Category cat = new Category();
        Connection con = ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pts = con.prepareStatement("select * from product where id_prod=?");
            pts.setInt(1,id);
            ResultSet res = pts.executeQuery();
            if(res.next()){
                p.setId(res.getInt("id_prod"));
                p.setDesignation(res.getString("designation"));
                p.setReference(res.getString("reference"));
                p.setPrix(res.getFloat("prix"));
                cat.setId(res.getInt("id_cat"));
                cat.setNomCat("");
                p.setCat(cat);
            }
            return p;
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }

    }

    @Override
    public List<Product> findAll() {
       List<Product> products = new ArrayList<>();


        Connection con = ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pts = con.prepareStatement("select * from product");
            ResultSet res = pts.executeQuery();
            while(res.next()){
                Product p=new Product() ;
                p.setId(res.getInt("id_prod"));
                p.setDesignation(res.getString("designation"));
                p.setReference(res.getString("reference"));
                p.setPrix(res.getFloat("prix"));

                Category cat = new Category();
//                cat.setId(res.getInt(("id_cat")));
//                cat.setNomCat("");
                PreparedStatement pst = con.prepareStatement("select * from category where id_cat=?");
                pst.setInt(1,Integer.parseInt(res.getString("id_cat")));
                ResultSet result =pst.executeQuery();
                if(result.next()){
                    cat.setId(result.getInt("id_cat"));
                   cat.setNomCat(result.getString("nom"));
                }
                p.setCat(cat);
                products.add(p);
            }
            return products;
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean save(Product product) {
        Connection con = ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pst = con.prepareStatement("insert into product (designation,reference,prix,id_cat) values (?,?,?,?)");
            pst.setString(1 , product.getDesignation());
            pst.setString(2 , product.getReference());
            pst.setFloat(3 , product.getPrix());
            pst.setInt(4 , product.getCat().getId());
            pst.executeUpdate();
            return true ;
        }catch(Exception ex){
            return false ;
        }

    }

    @Override
    public boolean delete(Product product) {
        Connection con = ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pst = con.prepareStatement("delete from product where id_prod=?");
            pst.setInt(1,product.getId());
            pst.executeUpdate();
            return  true ;
        }catch(Exception ex){
            return  false ;
        }
    }

    @Override
    public boolean update(Product product) {
        Connection con = ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pst = con.prepareStatement("update  product set designation = ? , reference =? , prix=? , id_cat=? where id_prod=?");
            pst.setString(1,product.getDesignation());
            pst.setString(2,product.getReference());
            pst.setFloat(3,product.getPrix());
            pst.setInt(4,product.getCat().getId());
            pst.setInt(5,product.getId());
            pst.executeUpdate();
            return  true ;
        }catch(Exception ex){
            return  false ;
        }
    }


    @Override
    public List<Product> findByQuery(String query) {
        Connection con= ConnexionBD.getConnetionBD();

        List<Product> products = new ArrayList<Product>();
        try{
            PreparedStatement pst = con.prepareStatement("select * from product where designation like ? or reference like ? or prix like ? ");
            pst.setString(1,"%"+query+"%");
            pst.setString(2,"%"+query+"%");
            pst.setString(3,"%"+query+"%");
            ResultSet res= pst.executeQuery();
            while(res.next()){

                Product p = new Product();
             //   p.setId(res.getInt("id_prod"));
                p.setDesignation(res.getString("designation"));
                p.setReference(res.getString("reference"));
                p.setPrix(res.getFloat("prix"));
                Category cat = new Category();
//                cat.setId(res.getInt(("id_cat")));
//                cat.setNomCat("");
                PreparedStatement prst = con.prepareStatement("select * from category where id_cat=?");
                prst.setInt(1,Integer.parseInt(res.getString("id_cat")));
                ResultSet result =prst.executeQuery();
                if(result.next()){
                    cat.setId(result.getInt("id_cat"));
                    cat.setNomCat(result.getString("nom"));
                }
                p.setCat(cat);
                products.add(p);
            }
            return products;
        }catch(Exception ex){
            return null;
        }
    }

    public static void main(String[] args) {
//        Category cat = new Category(1);
//        Product product= new Product();
//        product.setDesignation("trousse");
//        product.setPrix(16);
//        product.setReference("GR665");
//        product.setCat(cat);
          ProductDao productDao =new ProductDaoImp();
          System.out.println(new ProductDaoImp().findAll());
//        List<Product> list = productDao.findAll();
//        for (Product p1:list) {
//            System.out.println(p);
//        }
    }
}
