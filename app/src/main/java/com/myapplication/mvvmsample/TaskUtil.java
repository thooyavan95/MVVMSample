package com.myapplication.mvvmsample;

import android.widget.RadioGroup;

public class TaskUtil {

    public static final int PRIORITY_LOW = 1;
    public static final int PRIORITY_MEDIUM = 2;
    public static final int PRIORITY_HIGH = 3;

    public static int getPriorityValue(RadioGroup selectedPriority)
    {
        int priorityValue = PRIORITY_LOW;

        int selectedItem = selectedPriority.getCheckedRadioButtonId();

        switch (selectedItem)
        {
            case R.id.priority_low:
                priorityValue = PRIORITY_LOW;
                break;

            case R.id.priority_medium:
                priorityValue = PRIORITY_MEDIUM;
                break;

            case R.id.priority_high:
                priorityValue = PRIORITY_HIGH;
                break;
        }

        return priorityValue;
    }

    public static String getPriorityString(int priorityValue)
    {
        String priority = "Low";

        switch (priorityValue)
        {
            case PRIORITY_LOW:
                priority = "Low";
                break;

            case PRIORITY_MEDIUM:
                priority = "Medium";
                break;

            case PRIORITY_HIGH:
                priority = "High";
                break;
        }

        return priority;
    }

}
