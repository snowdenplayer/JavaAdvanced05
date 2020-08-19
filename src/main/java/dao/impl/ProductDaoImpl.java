package dao.impl;

import Utils.ConnectionUtils;
import dao.ProductDao;
import domain.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl  implements ProductDao {

    static String READ_ALL = "select * from product";
    static String CREATE = "insert into product(`name`,`description`,`price`) values (?,?,?)";
    static String UPDATE_BY_ID = "update product set name=? ,description=?,price=? where idProduct=?";
    static String READ_BY_ID = "select * from product where idProduct=?";
    static String DELETE_BY_ID = "delete from bucket where idProduct=?";

    private Connection con;
    private PreparedStatement rpp;

    public ProductDaoImpl() {
        try {
            this.con = ConnectionUtils.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product create(Product product) throws SQLException {
        rpp = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
        rpp.setString(1, product.getName());
        rpp.setString(2, product.getDescription());
        rpp.setDouble(3,product.getPrice());
        rpp.executeUpdate();
        ResultSet resultSet = rpp.getGeneratedKeys();
        product.setId(resultSet.getInt(1));
        return product;
    }

    public Product read(Integer id) throws SQLException {
        Product product;
        rpp = con.prepareStatement(READ_BY_ID);
        rpp.setInt(1, id);
        ResultSet res = rpp.executeQuery();
        res.next();
        int productId = res.getInt("idProduct");
        String name = res.getString("name");
        String description = res.getString("description");
        double price = res.getDouble("price");

        product = new Product(name, description, price);
        return product;
    }

    public Product update(Product product) throws SQLException {

        rpp = con.prepareStatement(UPDATE_BY_ID);
        rpp.setString(1,product.getName());
        rpp.setString(2,product.getDescription());
        rpp.setDouble(3,product.getPrice());
        rpp.executeUpdate();
        return product;
    }

    public void delete(Integer id) throws SQLException {
        rpp = con.prepareStatement(DELETE_BY_ID);
        rpp.setInt(1, id);
        rpp.executeUpdate();
    }

    public List<Product> readAll() throws SQLException {
        List<Product> list = new ArrayList<>();
        rpp = con.prepareStatement(READ_ALL);
        ResultSet res = rpp.executeQuery();
        while (res.next()) {
            int id = res.getInt("idProduct");
            String name = res.getString("name");
            String description = res.getString("description");
            double price = res.getDouble("price");
            list.add(new Product(id, name, description, price));
        }
        return list;
    }
}
