package com.example.sayma.roomdatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Sayma on 5/17/2018.
 */

@Database(entities = {LoanTracker.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract LoanTrackerDao getLoanTrackerDao();

    private static AppDatabase instance;

    static AppDatabase getInstance(final Context context){
        if(instance == null){
            synchronized (AppDatabase.class){
                if(instance == null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DataHelper.DATABASE_NAME).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }

    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("create table 'loan_tracker' ( '_id' integer not null, 'type' int not null, " +
                    "'amount' text not null, 'name' text not null, 'date' long not null, 'return_date' " +
                    " long, 'deal_type' int not null, primary key ('_id'))");
        }
    };


}
