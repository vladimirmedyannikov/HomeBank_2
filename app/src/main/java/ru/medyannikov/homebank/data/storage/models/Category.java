package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Vladimir on 25.03.2016.
 */
@Table(name = "Category")
public class Category extends Model {

    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;
    @Column(name = "date_created")
    @SerializedName("date_created")
    @Expose
    private Date date;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
