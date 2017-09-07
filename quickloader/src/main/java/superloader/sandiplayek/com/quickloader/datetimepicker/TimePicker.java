package superloader.sandiplayek.com.quickloader.datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by sandip on 6/21/17.
 */

public class TimePicker {
    public TimePicker(final Context context, final OnTimePickerListner listener){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(android.widget.TimePicker timePicker, int selectedHour, int selectedMinute) {
                String selected_time="";
                selected_time=selectedHour + ":" + selectedMinute;
                listener.onTimeResponse(selected_time);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }
    public interface OnTimePickerListner {
        void onTimeResponse(String selected_time);
    }
}
