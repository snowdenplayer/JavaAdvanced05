package dao.impl;

import Utils.ConnectionUtils;
import dao.BucketDao;
import domain.Bucket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketDaoImpl implements BucketDao {
    static String READ_ALL = "select * from bucket";
    static String CREATE = "insert into bucket(`userId`,`productId`,`nowDate`) values (?,?,?)";
    static String READ_BY_ID = "select * from bucket where idBucket=?";
    static String DELETE_BY_ID = "delete from bucket where idBucket=?";

    private Connection con;
    private PreparedStatement rpp;

    public BucketDaoImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.con = ConnectionUtils.connect();
    }

    public Bucket create(Bucket bucketDao) throws SQLException {
        rpp = con.prepareStatement(CREATE,Statement.RETURN_GENERATED_KEYS);
        rpp.setInt(1, bucketDao.getUserId());
        rpp.setInt(2, bucketDao.getProductId());
        rpp.setDate(3, (Date) bucketDao.getNowDate());
        rpp.executeUpdate();
        ResultSet resultSet = rpp.getGeneratedKeys();
        bucketDao.setId(resultSet.getInt(1));
        return bucketDao;
    }


    public Bucket read(Integer id) throws SQLException {

        Bucket bucket;
        rpp = con.prepareStatement(READ_BY_ID);
        rpp.setInt(1, id);
        ResultSet res = rpp.executeQuery();
        res.next();
        int bucketId = res.getInt("idBucket");
        int userId = res.getInt("userId");
        int productId = res.getInt("productId");
        java.util.Date nowDate = res.getDate("nowDate");

        bucket = new Bucket(bucketId, userId, productId, nowDate);

        return bucket;
    }


    public Bucket update(Bucket bucketDao) {
        throw new IllegalStateException("no update");
    }

    public void delete(Integer id) throws SQLException {
        rpp = con.prepareStatement(DELETE_BY_ID);
        rpp.setInt(1, id);
        rpp.executeUpdate();
    }

    public List<Bucket> readAll() throws SQLException {
        List<Bucket> list = new ArrayList<>();
        rpp = con.prepareStatement(READ_ALL);
        ResultSet res = rpp.executeQuery();
        while (res.next()) {
            int id = res.getInt("id");
            int userId = res.getInt("userId");
            int productId = res.getInt("productId");
            java.util.Date nowDate = res.getDate("nowDate");
            list.add(new Bucket(id, userId, productId, nowDate));
        }
        return list;
    }
}
