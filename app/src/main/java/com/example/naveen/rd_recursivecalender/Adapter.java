package com.example.naveen.rd_recursivecalender;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naveen.rd_recursivecalender.model.CustomArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    private MainActivity mainActivity;
    private ArrayList<Date> dateArrays;
    private List<CustomArray> valuesArray;
    private Calendar calendar;
    private View.OnClickListener listener;

    Adapter(MainActivity mainActivity, ArrayList<Date> dateArrays, List<CustomArray> valuesArray,
            Calendar calendar, View.OnClickListener listener) {
        this.mainActivity = mainActivity;
        this.dateArrays = dateArrays;
        this.valuesArray = valuesArray;
        this.calendar = calendar;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 5;
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(mainActivity, dateArrays.get(position), valuesArray.get(position), calendar,listener,position);
    }

    @Override
    public int getItemCount() {
        return dateArrays.size();
    }
}
