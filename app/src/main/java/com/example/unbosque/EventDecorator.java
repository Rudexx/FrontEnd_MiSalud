package com.example.unbosque;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.HashSet;

public class EventDecorator implements DayViewDecorator {
    private final Drawable drawable;
    private final CalendarDay date;

    public EventDecorator(Context context, int drawableResId, CalendarDay date) {
        this.drawable = ContextCompat.getDrawable(context, drawableResId);
        this.date = date;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date != null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setSelectionDrawable(drawable);
    }
}