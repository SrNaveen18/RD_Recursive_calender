package com.example.naveen.rd_recursivecalender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.naveen.rd_recursivecalender.helper.MonthHelper;
import com.example.naveen.rd_recursivecalender.model.CustomArray;
import com.example.naveen.rd_recursivecalender.model.DateList;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,MainView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private static final String TAG = MainActivity.class.getSimpleName();

    List<CustomArray> customArrays = new ArrayList<>();

    private Calendar currentDate = Calendar.getInstance();
    private static final int DAYS_COUNT = 35;

    private  int PAUSE_DATES = 4;

    private String startDate = "18/06/2018 00:00:00";
    private String endDate = "16/07/2018 00:00:00";


    Date startConvertedDate;
    Date endConvertedDate;
    boolean subscribtionDate;
    boolean isDatePassed;

    List<Date> pauseDateList = new ArrayList<>();
    @BindView(R.id.txtDateMonth)
    TextView txtDateMonth;
    @BindView(R.id.frmLeft)
    FrameLayout frmLeft;
    @BindView(R.id.frmRight)
    FrameLayout frmRight;

//    String[] pauseDates = {"25/06/2018 00:00:00", "4/07/2018 00:00:00", "17/07/2018 00:00:00", "20/07/2018 00:00:00"};
    private ArrayList<Date> cells;
    private List<CustomArray> temp;
    private MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter(this);
        mainPresenter.getDateList();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(gridLayoutManager);


        frmLeft.setOnClickListener(this);
        frmRight.setOnClickListener(this);

    }
    @Override
    public void displaySubcription(DateList dateList) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        startDate = dateList.startDate;
        endDate = dateList.endDate;

        try {

            startConvertedDate = dateFormat.parse(startDate);
            PAUSE_DATES = dateList.pausedDates.dates.split(",").length;
            for (int i = 0; i < dateList.pausedDates.dates.split(",").length; i++) {
                pauseDateList.add(dateFormat.parse(dateList.pausedDates.dates.split(",")[i]));
            }

            endConvertedDate = dateFormat.parse(endDate);
            Calendar c = Calendar.getInstance();
            c.setTime(endConvertedDate);
            c.add(Calendar.DATE, PAUSE_DATES);
            endConvertedDate = c.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        updateCalender();
    }


    private void setAdapter(ArrayList<Date> cells, List<CustomArray> temp, Calendar calendar) {
        Adapter adapter = new Adapter(this, cells, temp, calendar, this);
        recyclerView.setAdapter(adapter);
    }


    public void updateCalender() {
//      int subcribtionCompletedDays = 0;

        cells = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();

        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        txtDateMonth.setText((MonthHelper.getMonth(String.valueOf(month + 1)) + "\t" + year));
        temp = new ArrayList<>();

        // determine the cell for current month's beginning
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        int monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        // move calendar backwards to the beginning of the week
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell);


        // fill cells
        while (cells.size() < DAYS_COUNT) {
            cells.add(calendar.getTime());
            Date calenderDate = calendar.getTime();


            if (startConvertedDate.getTime() <= calenderDate.getTime()
                    && endConvertedDate.getTime() >= calenderDate.getTime()) {
                subscribtionDate = true;
            } else {
                subscribtionDate = false;
            }


            Calendar c = Calendar.getInstance();
            // set the calendar to start of today
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            // and get that as a Date
            Date today = c.getTime();

            if (today.before(calendar.getTime())) {
                isDatePassed = true;
            } else {
                isDatePassed = false;
            }


            if (subscribtionDate && isDatePassed) {
                int valueForDate = 1;
                for (int i = 0; i < PAUSE_DATES; i++) {
                    if (pauseDateList.get(i).getTime() == calenderDate.getTime()) {
                        valueForDate = -2;
                        break;
                    }
                }
                temp.add(new CustomArray(valueForDate));
            } else if (subscribtionDate && !isDatePassed) {
//                ++ subcribtionCompletedDays;
                temp.add(new CustomArray(-1));
            } else {
                temp.add(new CustomArray(0));
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

//        long diff = endConvertedDate.getTime() - startConvertedDate.getTime();
//        int betweenDays = (int) (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 2 - pauseDateList.size());
//        int balanceDays=  betweenDays - subcribtionCompletedDays;
//
//        Log.w(TAG,"BETWEEN DAYS    ====" + String.valueOf(betweenDays));
//        Log.w(TAG,"PAUSED  DAYS    ====" + pauseDateList.size());
//        Log.w(TAG,"COMPLETED DAYS  ====" + subcribtionCompletedDays);
//        Log.w(TAG,"BALANCE  DAYS   ====" + String.valueOf(balanceDays));


        setAdapter(cells, temp, currentDate);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.frmLeft:
                currentDate.add(Calendar.MONTH, -1);
                updateCalender();
                break;
            case R.id.frmRight:
                currentDate.add(Calendar.MONTH, 1);
                updateCalender();
                break;

            case R.id.lnrDate:
                int position = (int) view.getTag();
                if (customArrays != null && cells != null) {
                    Toast.makeText(getApplicationContext(),
                            String.valueOf(cells.get(position).getDate()), Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;

        }
    }


}
