package com.example.sayma.roomdatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Sayma on 5/21/2018.
 */

public class MyViewModel extends AndroidViewModel {

    LoanTrackerDao loanTrackerDao;
    private LiveData<List<LoanTracker>> data;
    public MyViewModel(@NonNull Application application) {
        super(application);
        loanTrackerDao = AppDatabase.getInstance(application).getLoanTrackerDao();
        data = loanTrackerDao.getData();
    }

    public LiveData<List<LoanTracker>> getAllInfo(){
        return data;
    }
}
