package ma.enset.metier;

import ma.enset.dao.entites.Category;
import ma.enset.dao.entites.Product;

import java.util.List;

public interface OperationCatalog {
    Product findByIdProduct(int id);
    List<Product> findAllProduct();
    boolean saveProduct(Product t);
    boolean deleteProduct(Product t);
    boolean updateProduct(Product t) ;
    List<Product> findByQueryProduct(String query);
    List<Product> findByQueryCategory(String query);
    Category findByIdCat(int id);
    List<Category> findAllCategory();
    boolean saveCategory(Category t);
    boolean deleteCategory(Category t);
    boolean updateCategory(Category t) ;


}
