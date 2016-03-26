package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by Vladimir on 16.03.2016.
 */
public class Bill extends Model {
    @Column(name = "id_bill")
    @SerializedName("id_bill")
    @Expose
    private int idBill;

    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;

    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;

    @Column(name = "about")
    @SerializedName("about")
    @Expose
    private String about;

    @Column(name = "value")
    @SerializedName("summValue")
    @Expose
    private Double value;

    @Column(name = "date_create")
    @SerializedName("date_create")
    @Expose
    private Date date;

    public Bill() {
        name = "Unknow";
        about = "Thi is a new bill";
        value = 1832.4;
    }

}
