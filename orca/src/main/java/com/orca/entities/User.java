package com.orca.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "ORCA_ACCOUNT")
public class User {



    public User() {

        setUserId(UUID.randomUUID().toString());
    }



    @Id
    @Column(name="ACCOUNT_ID", length=36, unique=true, nullable=false)
    private String userId;

    @Column(name="FULL_NAME", nullable = false)
    private String fullName;


    @Column(name="EMAIL", nullable = false)
    private String email;

    @Column(name="PASSWORD" , nullable = false)
    private String password;

    @Column(name="PHONE" , nullable = false)
    private String phone;

    @Column(name="BIRTH_DATE" , nullable = false)
    private Date birthDate;

    @Column(name="CAN_SWIM" , nullable = false)
    private boolean canSwim;

    @Column(name="TERM_AGREED" ,nullable = false)
    private boolean termAgreed;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    public void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    public boolean isTermAgreed() {
        return termAgreed;
    }

    public void setTermAgreed(boolean termAgreed) {
        this.termAgreed = termAgreed;
    }
}
