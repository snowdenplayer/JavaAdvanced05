package service.impl;

import dao.ProductDao;
import dao.impl.ProductDaoImpl;
import domain.Product;
import service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public ProductServiceImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        productDao = new ProductDaoImpl();
    }

    @Override
    public Product create(Product product) throws SQLException {
        return productDao.create(product);
    }

    @Override
    public Product read(Integer id) throws SQLException {
        return productDao.read(id);
    }

    @Override
    public Product update(Product product) throws SQLException {
        return productDao.update(product);
    }

    @Override
    public void delete(Integer id) throws SQLException {

        productDao.delete(id);
    }

    @Override
    public List<Product> readAll() throws SQLException {
        return productDao.readAll();
    }
}
