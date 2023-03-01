package ma.enset.metier;

import ma.enset.dao.CategoryHandler.CategoryDao;
import ma.enset.dao.CategoryHandler.CategoryDaoImp;
import ma.enset.dao.ProductHandler.ProductDao;
import ma.enset.dao.ProductHandler.ProductDaoImp;
import ma.enset.dao.entites.Category;
import ma.enset.dao.entites.Product;

import java.util.List;

public class OperationCatalogImp implements OperationCatalog {

       private CategoryDao categoryDao ;
       private ProductDao productDao;
       public OperationCatalogImp(CategoryDao categoryDao , ProductDao productDao){
           this.categoryDao=categoryDao;
           this.productDao=productDao;
       }
    @Override
    public Product findByIdProduct(int id) {
        return productDao.findById(id);
    }

    @Override
    public List<Product> findAllProduct() {
        return productDao.findAll();
    }

    @Override
    public boolean saveProduct(Product t) {
        return productDao.save(t);
    }

    @Override
    public boolean deleteProduct(Product t) {
        return productDao.delete(t);
    }

    @Override
    public boolean updateProduct(Product t) {
        return productDao.update(t);
    }

    @Override
    public List<Product> findByQueryProduct(String query) {
        return productDao.findByQuery(query);
    }

    @Override
    public List<Product> findByQueryCategory(String query) {
        return null;
    }

    @Override
    public Category findByIdCat(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryDao.findAll();
    }

    @Override
    public boolean saveCategory(Category t) {
        return categoryDao.save(t);
    }

    @Override
    public boolean deleteCategory(Category t) {
        return categoryDao.delete(t);
    }

    @Override
    public boolean updateCategory(Category t) {
        return categoryDao.update(t);
    }

    public static void main(String[] args) {
        OperationCatalog op = new OperationCatalogImp(new CategoryDaoImp(), new ProductDaoImp());
        //System.out.println(op.saveCategory(new Category("Bijoux")));
        Product p=op.findByIdProduct(2);
//        p.setDesignation("collier");
//        p.setReference("FSa128");
//        p.setPrix(134);
//        p.setCat(new Category(2));
        System.out.println(op.deleteProduct(p));
        System.out.println(op.findByIdProduct(2).getId());
    }
}
