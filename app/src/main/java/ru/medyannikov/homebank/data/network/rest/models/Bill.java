package ru.medyannikov.homebank.data.network.rest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladimir on 07.03.2016.
 */
public class Bill {
    @SerializedName("idBill")
    @Expose
    private Integer idBill;
    @SerializedName("nameBill")
    @Expose
    private String nameBill;
    @SerializedName("aboutBill")
    @Expose
    private String aboutBill;
    @SerializedName("summ")
    @Expose
    private Double summ;

    /**
     *
     * @return
     * The idBill
     */
    public Integer getIdBill() {
        return idBill;
    }

    /**
     *
     * @param idBill
     * The idBill
     */
    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    /**
     *
     * @return
     * The nameBill
     */
    public String getNameBill() {
        return nameBill;
    }

    /**
     *
     * @param nameBill
     * The nameBill
     */
    public void setNameBill(String nameBill) {
        this.nameBill = nameBill;
    }

    /**
     *
     * @return
     * The aboutBill
     */
    public String getAboutBill() {
        return aboutBill;
    }

    /**
     *
     * @param aboutBill
     * The aboutBill
     */
    public void setAboutBill(String aboutBill) {
        this.aboutBill = aboutBill;
    }

    /**
     *
     * @return
     * The summ
     */
    public Double getSumm() {
        return summ;
    }

    /**
     *
     * @param summ
     * The summ
     */
    public void setSumm(Double summ) {
        this.summ = summ;
    }

}
