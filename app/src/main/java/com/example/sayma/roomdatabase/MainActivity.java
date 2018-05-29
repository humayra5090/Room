package com.example.sayma.roomdatabase;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.stetho.Stetho;

import java.util.List;
import android.arch.lifecycle.Observer;

import javax.annotation.Nullable;

import static com.example.sayma.roomdatabase.AppDatabase.MIGRATION_1_2;

public class MainActivity extends AppCompatActivity{
    private  int LOADER_ID = 1011;
    Adapter adapter;
    RecyclerView rv;
    LinearLayoutManager layoutManager;
    AppDatabase database;
    TextView tvInsert, tvDelete;
    static int insert = 22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        tvInsert = findViewById(R.id.insert);
        tvDelete = findViewById(R.id.delete);
        layoutManager = new LinearLayoutManager(this);
        Stetho.initializeWithDefaults(this);
        database = AppDatabase.getInstance(this);
        new LoadData().execute();

        final LoanTrackerRepository repository = new LoanTrackerRepository(database);


        tvInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoanTracker loanTracker = new LoanTracker();
                loanTracker.set_id(insert);
                loanTracker.setName("Drik");
                loanTracker.setAmount("540" + insert);
                loanTracker.setDate(System.currentTimeMillis());
                loanTracker.setType(0);
                loanTracker.setDeal_type(0);
                repository.createNewEntry(loanTracker);
                insert++;
            }
        });

        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

//
    }


    private class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
//            LoanTrackerDao loanTrackerDao = database.getContactDao();
//            LoanTrackerRepository repository = new LoanTrackerRepository(database);
//            for(int i = 500 ; i< 520; i++){
//                LoanTracker loanTracker = new LoanTracker();
//                loanTracker.set_id(i);
//                loanTracker.setName("Alice");
//                loanTracker.setAmount(i + "10");
//                loanTracker.setDate(System.currentTimeMillis());
//                loanTracker.setType(0);
//                loanTracker.setDeal_type(0);
//                loanTrackerDao.insert(loanTracker);
//            }

            MyViewModel viewModel = ViewModelProviders.of(MainActivity.this).get(MyViewModel.class);
            adapter = new Adapter(MainActivity.this);

            viewModel.getAllInfo().observe(MainActivity.this, new Observer<List<LoanTracker>>() {
                @Override
                public void onChanged(@Nullable List<LoanTracker> loanTrackers) {
                    adapter.setList(loanTrackers);
                    rv.setAdapter(adapter);
                    rv.setLayoutManager(layoutManager);
                    adapter.notifyDataSetChanged();
                }
            });
            return null;
        }

    }
}
