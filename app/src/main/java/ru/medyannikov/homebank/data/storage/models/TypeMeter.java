package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 26.03.2016.
 */
public class TypeMeter extends Model{
    @Column(name = "name")
    @SerializedName("name")
    @Expose
    private String name;
    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;

}
