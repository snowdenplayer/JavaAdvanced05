package dao.impl;

import Utils.ConnectionUtils;
import dao.UserDao;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    static String READ_ALL = "select * from user";
    static String CREATE = "insert into user(`email`,`firstName`,`lastName`,`role`) values (?,?,?,?)";
    static String UPDATE_BY_ID = "update user set email=?,firstName=?,lastName=? ,role=? where idUser=?";
    static String READ_BY_ID = "select * from user where idUser=?";
    static String DELETE_BY_ID = "delete from user where idUser=?";

    private Connection con;
    private PreparedStatement rpp;

    public UserDaoImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.con = ConnectionUtils.connect();
    }

    @Override
    public User create(User user) throws SQLException {
        rpp = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
        rpp.setString(1, user.getEmail());
        rpp.setString(2, user.getFirstName());
        rpp.setString(3, user.getLastName());
        rpp.setString(4, user.getRole());
        rpp.executeUpdate();
        ResultSet resultSet = rpp.getGeneratedKeys();
        resultSet.next();
        user.setId(resultSet.getInt(1));
        return user;
    }

    @Override
    public User read(Integer id) throws SQLException {
        User user;
        rpp = con.prepareStatement(READ_BY_ID);
        rpp.setInt(1, id);
        ResultSet res = rpp.executeQuery();
        res.next();
        String email = res.getString("email");
        String firstName = res.getString("firstName");
        String lastName = res.getString("lastName");
        String role = res.getString("role");

        user = new User(email, firstName, lastName, role);

        return user;
    }

    @Override
    public User update(User user) throws SQLException {
        rpp = con.prepareStatement(UPDATE_BY_ID);
        rpp.setString(1,user.getEmail());
        rpp.setString(2,user.getFirstName());
        rpp.setString(3,user.getLastName());
        rpp.setString(4,user.getRole());
        rpp.executeUpdate();
        return user;
    }

    @Override
    public void delete(Integer id) throws SQLException {
        rpp = con.prepareStatement(DELETE_BY_ID);
        rpp.setInt(1, id);
        rpp.executeUpdate();
    }

    @Override
    public List<User> readAll() throws SQLException {
        List<User> list = new ArrayList<>();
        rpp = con.prepareStatement(READ_ALL);
        ResultSet res = rpp.executeQuery();
        while (res.next()) {
            int id = res.getInt("idUser");
            String email = res.getString("name");
            String firstName = res.getString("firstName");
            String lastName = res.getString("lastName");
            String role = res.getString("role");
            list.add(new User(id, email, firstName, lastName,role));
        }
        return list;
    }
}
