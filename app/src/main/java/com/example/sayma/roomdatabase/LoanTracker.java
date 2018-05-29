package com.example.sayma.roomdatabase;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Sayma on 5/17/2018.
 */
@Entity(tableName = "loan_tracker")
public class LoanTracker {
    @PrimaryKey
    @NonNull
    private Integer _id;

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    @NonNull
    public int getType() {
        return type;
    }

    public void setType(@NonNull int type) {
        this.type = type;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getAmount() {
        return amount;
    }

    public void setAmount(@NonNull String amount) {
        this.amount = amount;
    }

    @NonNull
    public long getDate() {
        return date;
    }

    public void setDate(@NonNull long date) {
        this.date = date;
    }

    public long getReturn_date() {
        return return_date;
    }

    public void setReturn_date(long return_date) {
        this.return_date = return_date;
    }

    @NonNull
    public int getDeal_type() {
        return deal_type;
    }

    public void setDeal_type(@NonNull int deal_type) {
        this.deal_type = deal_type;
    }

    @NonNull
    private int type;
    @NonNull
    private String name;
    @NonNull
    private String amount;
    @NonNull
    private long date;
    private long return_date;
    @NonNull
    private int deal_type;

}
