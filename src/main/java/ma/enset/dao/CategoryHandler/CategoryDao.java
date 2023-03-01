package ma.enset.dao.CategoryHandler;

import ma.enset.dao.Dao;
import ma.enset.dao.entites.Category;

import java.util.List;


public interface CategoryDao extends Dao<Category> {
    List<Category> findByQuery(String query);
}
