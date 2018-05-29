package com.example.sayma.roomdatabase;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Sayma on 5/22/2018.
 */

public class LoanTrackerRepository {
    private final LoanTrackerDao loanTrackerDao;
    @Inject
    public LoanTrackerRepository(AppDatabase db){
        this.loanTrackerDao = db.getLoanTrackerDao(); //loanTrackerDao;
    }
    public LiveData<List<LoanTracker>> getListOfLoanTrackers(){
        return loanTrackerDao.getData();
    }
    public LiveData<List<LoanTracker>> getDataForPerson(String number){
        return loanTrackerDao.getDataForPerosn(number);
    }
    public void createNewEntry(LoanTracker loanTracker){
         loanTrackerDao.insert(loanTracker);
    }
    public void deleteEntry(LoanTracker loanTracker){
        loanTrackerDao.delete(loanTracker);
    }

}
