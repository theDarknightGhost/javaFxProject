package ma.enset.dao.CategoryHandler;

import com.mysql.cj.x.protobuf.MysqlxExpect;
import ma.enset.dao.BD.ConnexionBD;
import ma.enset.dao.entites.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImp implements CategoryDao{


    @Override
    public Category findById(int id) {
        Connection con = ConnexionBD.getConnetionBD();
        Category cat = new Category();
        try{
            PreparedStatement pst = con.prepareStatement("select * from category where id_cat=?");
            pst.setInt(1,id);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                cat.setId(res.getInt("id_cat"));
                cat.setNomCat(res.getString("nom"));
            }
            return cat ;
        }catch(Exception ex){
            return null;
        }


    }

    @Override
    public List<Category> findAll() {
        Connection con = ConnexionBD.getConnetionBD();

        List<Category> list = new ArrayList<>();
        try{
            PreparedStatement pst = con.prepareStatement("select * from category");
            ResultSet res = pst.executeQuery();
            while(res.next()){
                Category cat = new Category();
                cat.setId(res.getInt("id_cat"));
                cat.setNomCat(res.getString("nom"));
                list.add(cat);
            }
            return list ;
        }catch(Exception ex){
            return null;
        }
    }

    @Override
    public boolean save(Category category) {
        Connection con= ConnexionBD.getConnetionBD();
        try
        {
            PreparedStatement pst = con.prepareStatement("insert into category (nom)  values (?)");
            pst.setString(1,category.getNomCat());
            pst.executeUpdate() ;
            return true ;
        } catch(Exception ex)
        {
            return false ;
        }
    }

    @Override
    public boolean delete(Category category) {
        Connection con = ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pst =con.prepareStatement("delete * from Category where nom=?");
            pst.setString(1,category.getNomCat());
            pst.executeUpdate();
            return true ;
        }catch(Exception ex){
            return false ;
        }
    }

    @Override
    public boolean update(Category category) {
        Connection con =ConnexionBD.getConnetionBD();
        try{
            PreparedStatement pst = con.prepareStatement("update category set nom =? where id_cat=?");
            pst.setString(1 , category.getNomCat());
            pst.setInt(2,category.getId());
            pst.executeUpdate();
            return true ;
        }catch(Exception ex){
            return false ;
        }

    }

    @Override
    public List<Category> findByQuery(String query) {
        return null;
    }

    public static void main(String[] args) {
        //Category cat = new Category("Eléctronique");
 CategoryDao categoryDao=new CategoryDaoImp();
//        //categoryDao.save(cat);
//        Category cat2= new Category(1,"Fourniture scolaire");
//        Category cat1 = categoryDao.findById(1);
//        System.out.println(categoryDao.update(cat2));
        System.out.println(categoryDao.findAll());
    }
}
