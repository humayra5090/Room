package com.example.sayma.roomdatabase;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Sayma on 5/17/2018.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context c;
    Cursor cursor;
    List<LoanTracker> myList = null;
    public int PENDING_TYPE_VIEW = 1;
    public int DISMISSED_TYPE_VIEW = 2;

    public Adapter(Context c){
        this.c = c;
    }

    public void setList(List<LoanTracker> myList){
        this.myList = myList;
    }


    public void swapCursor(Cursor c){
        cursor = c;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == PENDING_TYPE_VIEW){
            View v = LayoutInflater.from(c).inflate(R.layout.dismissed_single_view, null);
            return new PendingViewHolder(v);
        }
        else{
            View v = LayoutInflater.from(c).inflate(R.layout.pending_single_view, null);
            return new DoneViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof DoneViewHolder){
            DoneViewHolder dh = (DoneViewHolder) holder;
            dh.bindView(position);

        }else{
            PendingViewHolder ph = (PendingViewHolder) holder;
            ph.bindView(position);
        }
    }

    @Override
    public int getItemCount() {
//        if(cursor.getCount() > 0) return cursor.getCount();
//        return 0;
        if(myList != null) return myList.size();
        return 0;
    }


    @Override
    public int getItemViewType(int position){
//        cursor.moveToPosition(position);
//        if(cursor.getInt(cursor.getColumnIndex(DataHelper.TRANSACTION_TYPE)) == 0) return PENDING_TYPE_VIEW;
//        else return DISMISSED_TYPE_VIEW;
        LoanTracker loanTracker = myList.get(position);
        if(loanTracker.getDeal_type() == 0) return  PENDING_TYPE_VIEW;
        else return  DISMISSED_TYPE_VIEW;
    }

    public class DoneViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvStatus;

        public DoneViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvStatus = itemView.findViewById(R.id.status);
        }

        public void bindView(int position ){
//            cursor.moveToPosition(position);
//            String name = cursor.getString(cursor.getColumnIndex(DataHelper.NAME));
//            int type = cursor.getInt(cursor.getColumnIndex(DataHelper.TRANSACTION_TYPE));
//            String amount = cursor.getString(cursor.getColumnIndex(DataHelper.AMOUNT));
//            long date = cursor.getLong(cursor.getColumnIndex(DataHelper.DATE));
            LoanTracker loanTracker = myList.get(position);
            String name = loanTracker.getName();
            tvName.setText(name);
            tvStatus.setText("DISMISSED");
        }
    }

    public class PendingViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvStatus;
        TextView tvAmount;
        TextView tvDate;

        public PendingViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvStatus = itemView.findViewById(R.id.status);
            tvAmount = itemView.findViewById(R.id.amount);
            tvDate = itemView.findViewById(R.id.date);
        }

        public void bindView(int position){
//            cursor.moveToPosition(position);
            LoanTracker loanTracker = myList.get(position);
            String name = loanTracker.getName(); //cursor.getString(cursor.getColumnIndex(DataHelper.NAME));
            String amount = loanTracker.getAmount(); //cursor.getString(cursor.getColumnIndex(DataHelper.AMOUNT));
            long date = loanTracker.getDate(); //cursor.getLong(cursor.getColumnIndex(DataHelper.DATE));
            tvName.setText(name);
            tvStatus.setText("PENDING");
            tvAmount.setText("Amount : " + amount);
            tvDate.setText(getDate(date));

        }
    }

    public static String getDate(long milliSeconds)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd yyyy, HH:mm aa");
        return formatter.format(milliSeconds);
    }

}
