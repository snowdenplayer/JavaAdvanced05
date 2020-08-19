package service.impl;

import dao.BucketDao;
import dao.impl.BucketDaoImpl;
import domain.Bucket;
import service.BucketService;

import java.sql.SQLException;
import java.util.List;

public class BucketServiceImpl implements BucketService {
    private BucketDao bucketDao;

    public BucketServiceImpl() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        bucketDao = new BucketDaoImpl();
    }

    @Override
    public Bucket create(Bucket bucket) throws SQLException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket read(Integer id) throws SQLException {
        return bucketDao.read(id);
    }

    @Override
    public Bucket update(Bucket bucket) throws SQLException {
        return bucketDao.update(bucket);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        bucketDao.delete(id);
    }

    @Override
    public List<Bucket> readAll() throws SQLException {
        return bucketDao.readAll();
    }
}
