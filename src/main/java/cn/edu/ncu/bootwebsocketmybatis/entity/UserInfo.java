package cn.edu.ncu.bootwebsocketmybatis.entity;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @auther: Liu Zedi.
 * @date: Create in 2018/12/17  18:15
 * @package: cn.edu.ncu.bootwebsocketmybatis.entity
 * @project: boot-websocket-mybatis
 */
public class UserInfo {

    private BigInteger id;
    private String userId;  //对应User表Id
    private String image;
    private String sex;
    private Timestamp birthday;
    private String phone;
    private String email;
    private String country;
    private String city;

    /*public UserInfo() {
        this.image="";
        this.sex="man";
        this.birthday=new Timestamp(0);
        this.phone="";
        this.email="";
        this.country="";
        this.city="";
    }*/

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", image='" + image + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
