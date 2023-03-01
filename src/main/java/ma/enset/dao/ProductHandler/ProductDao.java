package ma.enset.dao.ProductHandler;

import ma.enset.dao.Dao;
import ma.enset.dao.entites.Product;

import java.util.List;

public interface ProductDao extends Dao<Product> {
    List<Product> findByQuery(String query);

}
