package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 25.03.2016.
 */
@Table(name = "Purses", id = "_id")
public class Purse extends Model {
    @Column(name = "id")
    @Expose
    private long id;
    @Column(name = "id_server")
    @SerializedName("id_server")
    @Expose
    private long idServer;
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "value")
    @SerializedName("value")
    @Expose
    private double value;
    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;
    @Column(name = "about")
    @SerializedName("name")
    @Expose
    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdServer() {
        return idServer;
    }

    public void setIdServer(long idServer) {
        this.idServer = idServer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
