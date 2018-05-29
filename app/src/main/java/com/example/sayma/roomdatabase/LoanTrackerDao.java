package com.example.sayma.roomdatabase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Sayma on 5/17/2018.
 */
@Dao
public interface LoanTrackerDao {
    @Insert(onConflict = REPLACE)
    void insert(LoanTracker... contacts);

    @Update
    void update(LoanTracker... contacts);

    @Delete
    void delete(LoanTracker contact);

    @Query("select * from loan_tracker order by _id asc")
    LiveData<List<LoanTracker>> getData();

    @Query("select * from loan_tracker where name = :name ")
    LiveData<List<LoanTracker>> getDataForPerosn(String name);
}
