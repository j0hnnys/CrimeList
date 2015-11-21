package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.support.v4.app.DialogFragment;
import java.sql.Time;

/**
 * Created by Johnny on 9/21/15.
 * For challenge problem
 */

public class TimePickerFragment extends android.support.v4.app.DialogFragment {

    public static final String EXTRA_TIME =
            "come.bignerdranch.android.criminalintent.time";

    // When the Time class is initialized, the hour is set
    // to 16 for some reason. We use this timeConstant
    // to fix that by subtracting from time.
    private static final long timeConstant = 16*1000*60*60;

    private TimePicker mTimePicker;
    private Time mTime;
    private long time;

    public static TimePickerFragment newInstance() {
        TimePickerFragment fragment = new TimePickerFragment();
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_time_picker, null);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_time_picker);
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Sets time to time set in TimePicker whenever
                // the user adjusts the time.
                time = 1000*((hourOfDay*60*60) + (minute*60));

                mTime = new Time(time - timeConstant);

                Log.i("Time:", mTime.toString());
            }
        });

        mTime = new Time(time);

        return new AlertDialog.Builder(getActivity())
            .setView(v)
            .setTitle("Time of crime:")
            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Returns to the target fragment
                    // that instantiated TimePickerFragment
                    // (in this case, it would be CrimeFragment)
                    getTargetFragment();
                    sendResult(Activity.RESULT_OK);
                }
            })
            .create();
    }

    /**
     * Sends result code and time back to parent fragment
     * @param resultCode
     */
    private void sendResult(int resultCode) {

        if (getTargetFragment() == null) {
            // DatePickerFragment never set as target,
            // which results in no request code
            return;
        }

        // Create intent and add mTime as bundle
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, mTime);

        // Returns to target fragment
        // (the fragment that instantiated DatePickerFragment)
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);

    }
}
