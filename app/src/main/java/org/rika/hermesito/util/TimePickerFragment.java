package org.rika.hermesito.util;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by Rika on 2/20/2015.
 */
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    int hour, min, ampm;
    EditText editText;

    public void setEditText(EditText edit){
        editText = edit;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar cal = Calendar.getInstance();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        min = cal.get(Calendar.MINUTE);
        ampm = cal.get(Calendar.AM_PM);

        return new TimePickerDialog(getActivity(), this, hour, min, true);
    }

    public void onTimeSet(TimePicker view, int hh, int mm){
        editText.setText(hour + ":" + min);
    }
}
