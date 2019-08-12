package com.tokyonth.weather.util.weatherview.rainy;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tokyonth.weather.util.weatherview.basic.ShowView;


/**
 * Created by Administrator on 2017/8/23 0023.
 */

public class RainView extends ShowView<RainItem> {



    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void beforeLogicLoop() {

    }

    @Override
    public RainItem getItem(int width, int height) {
        return new RainItem(width,height);
    }

    @Override
    public int getCount() {
        return 60;
    }
}