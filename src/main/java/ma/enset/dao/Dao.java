package ma.enset.dao;

import java.util.ArrayList;
import java.util.List;

public interface Dao <T>{
   T findById(int id);
   List<T> findAll();
   boolean save(T t);
   boolean delete(T t);
   boolean update(T t) ;
}
