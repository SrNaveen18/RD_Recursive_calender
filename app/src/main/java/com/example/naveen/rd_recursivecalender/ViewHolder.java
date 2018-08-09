package com.example.naveen.rd_recursivecalender;


import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.naveen.rd_recursivecalender.model.CustomArray;
import java.util.Calendar;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;

class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.lnrDate)
    LinearLayout lnrDate;



    ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    void onBind(MainActivity mainActivity, Date dateArray, CustomArray customArray, Calendar calendar,
                View.OnClickListener listener, int position) {
        txtDate.setText(String.valueOf(dateArray.getDate()));

        Date itemDate = dateArray;
        int day = itemDate.getDate();
        int month = itemDate.getMonth();
        int year = itemDate.getYear();

        Date currentMonth = calendar.getTime();

        Date todayDate = new Date();


        if (month != currentMonth.getMonth()) {
            txtDate.setTextColor(ContextCompat.getColor(mainActivity, R.color.next_previous_month_color));
        } else if (day == todayDate.getDate() && currentMonth.getMonth() == todayDate.getMonth()) {
            txtDate.setTextColor(ContextCompat.getColor(mainActivity, R.color.current_day_color));
        } else {
            txtDate.setTextColor(ContextCompat.getColor(mainActivity, R.color.current_month_color));
        }



        if (customArray.getDate() == 1) {
            lnrDate.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.subcriptionDate));
        } else if (customArray.getDate() == -1) {
            lnrDate.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.passedSubcriptionDate));
        } else if(customArray.getDate() == -2){
            lnrDate.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.pausedSubcription));
        }else {
            lnrDate.setBackgroundColor(ContextCompat.getColor(mainActivity, R.color.unSubcriptionDate));
        }
        lnrDate.setTag(position);
        lnrDate.setOnClickListener(listener);

    }
}
