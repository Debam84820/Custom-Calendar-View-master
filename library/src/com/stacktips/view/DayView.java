/*
 * Copyright (c) 2016 Stacktips {link: http://stacktips.com}.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stacktips.view;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DayView extends TextView {
    private Date date;
    private List<DayDecorator> decorators;

    public DayView(Context context) {
        this(context, null, 0);
    }

    public DayView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            if (isInEditMode())
                return;
        }
    }

    public void bind(Date date, List<DayDecorator> decorators) {
        this.date = date;
        this.decorators = decorators;

        final SimpleDateFormat df = new SimpleDateFormat("d");
        SimpleDateFormat df1 = new SimpleDateFormat("d/MMM/yyyy");
        int day = Integer.parseInt(df.format(date));
        String month = String.valueOf(df1.format(date));
        Log.e("month", month);
        String[] holiday = {"Holi", "Ram Navami"};
        if (month.equalsIgnoreCase("1/Mar/2018")){
            String first = String.valueOf(day);
            String next = "<font color='#EE0000'>"+holiday[0]+"</font>";
            setText(Html.fromHtml(first+"<br/>"+next));
        } else if (month.equalsIgnoreCase("25/Mar/2018")){
            String first = String.valueOf(day);
            String next = "<font color='#EE0000'>"+holiday[1]+"</font>";
            setText(Html.fromHtml(first+"<br/>"+next));
        } else setText(String.valueOf(day)+"\n"+"");

    }

    public void decorate() {
        //Set custom decorators
        if (null != decorators) {
            for (DayDecorator decorator : decorators) {
                decorator.decorate(this);
            }
        }
    }

    public Date getDate() {
        return date;
    }
}