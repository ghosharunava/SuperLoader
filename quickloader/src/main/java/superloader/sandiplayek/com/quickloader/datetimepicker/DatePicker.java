package superloader.sandiplayek.com.quickloader.datetimepicker;

import android.app.DatePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by sandip on 6/21/17.
 */

public class DatePicker {
    public DatePicker(final Context context, final OnDatePickerListner listener){
        Calendar mcurrentDate=Calendar.getInstance();
        final int mYear=mcurrentDate.get(Calendar.YEAR);
        final int mMonth=mcurrentDate.get(Calendar.MONTH);
        final int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(android.widget.DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                // TODO Auto-generated method stub
                String selected_date="";
                selectedmonth=selectedmonth+1;
                selected_date=""+selectedday+"/"+selectedmonth+"/"+selectedyear;
                listener.onDateResponse(selected_date);
                //et_dt_picker.setText(selectedday+"/"+selectedmonth+"/"+selectedyear);
            }
        },mYear, mMonth, mDay);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }
    public interface OnDatePickerListner {
        void onDateResponse(String selected_date);
    }
}
