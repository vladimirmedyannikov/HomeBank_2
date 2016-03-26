package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Vladimir on 20.03.2016.
 */
@Table(name = "Operations")
public class Operation extends Model {
    @Column(name = "idOperation")
    @SerializedName("idOperation")
    @Expose
    private int idOperation;

    @Column(name = "bill")
    @SerializedName("bill")
    @Expose
    private Bill bill;

    @Column(name = "account")
    @SerializedName("account")
    @Expose
    private Account account;

    @Column(name = "valueOperation")
    @SerializedName("valueOperation")
    @Expose
    private Double value;

    @Column(name = "about")
    @SerializedName("about")
    @Expose
    private String about;

    @Column(name = "dateCreated")
    @SerializedName("dateCreated")
    @Expose
    private Date dateCreated;

    @Column(name = "sync")
    @Expose
    private int sync;

    public Operation() {
    }

    public Operation(String about, Bill bill, Date dateCreated, int idOperation, int sync) {
        this.about = about;
        this.bill = bill;
        this.dateCreated = dateCreated;
        this.idOperation = idOperation;
        this.sync = sync;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public int getSync() {
        return sync;
    }

    public void setSync(int sync) {
        this.sync = sync;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
