package domain;

import java.util.Date;

public class Bucket {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Date nowDate;

    public Bucket(Integer id, Integer userId, Integer productId, Date nowDate) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.nowDate = nowDate;
    }

    public Bucket(Integer userId, Integer productId, Date nowDate) {
        this.userId = userId;
        this.productId = productId;
        this.nowDate = nowDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getNowDate() {
        return nowDate;
    }

    public void setNowDate(Date nowDate) {
        this.nowDate = nowDate;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", nowDate=" + nowDate +
                '}';
    }
}
