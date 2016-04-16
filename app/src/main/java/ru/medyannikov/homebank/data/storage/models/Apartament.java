package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 26.03.2016.
 */
@Table(name = "Apartaments")
public class Apartament extends Model {
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
