package ru.medyannikov.homebank.data.storage.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ru.medyannikov.homebank.data.managers.DataManager;

/**
 * Created by Vladimir on 16.03.2016.
 */
@Table(name = "Bills")
public class Bill extends Model implements Serializable {
    public static final String BILL_EXTRA = "bill_extra";
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
        super();
        name = "Unknow";
        about = "Thi is a new bill";
        value = 1832.4;
    }

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void calcucateSumm() {
        List<Operation> list = getOperation();
        double sum = 0;
        for(Operation op: list){
           sum += op.getValue();
        }
        this.value = sum;
        this.save();

    }

    public List<Operation> getOperation(){
        return getMany(Operation.class, "idBill");
    }
}
