package com.tokyonth.weather.receiver;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.tokyonth.weather.R;
import com.tokyonth.weather.model.WeatherModel;
import com.tokyonth.weather.model.WeatherModelImpl;
import com.tokyonth.weather.model.bean.DefaultCity;
import com.tokyonth.weather.model.bean.Weather;
import com.tokyonth.weather.presenter.OnWeatherListener;
import com.tokyonth.weather.utils.helper.WeatherInfoHelper;
import com.tokyonth.weather.activity.MainActivity;

import org.litepal.crud.DataSupport;

public class WeatherLargeReceiver extends AppWidgetProvider implements OnWeatherListener {

    private ComponentName componentName;
    private AppWidgetManager manager;
    private RemoteViews remoteViews;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_large);
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,R.id.widget_large_ll,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.widget_large_ll,pi);
        componentName = new ComponentName(context,WeatherLargeReceiver.class);
        manager = AppWidgetManager.getInstance(context);
        DefaultCity defaultCity = DataSupport.find(DefaultCity.class,1);
        WeatherModel weatherModel = new WeatherModelImpl();
        weatherModel.loadLocationWeather(defaultCity,this);
    }

    @Override
    public void loadSuccess(Weather weather) {
        showWeather(weather);
    }

    @Override
    public void loadFailure(String msg) {
        remoteViews.setTextViewText(R.id.widget_large_forecast_tv,"小部件获取数据失败");
        manager.updateAppWidget(componentName,remoteViews);
    }

    @Override
    public void loadOffline() {
       // showWeather();
    }

    private void showWeather(Weather weather) {
        int weatherIMagePath01 = WeatherInfoHelper.getWeatherImagePath(weather.getInfo().getHourlyList().get(0).getImg());
        remoteViews.setImageViewResource(R.id.widget_large_imge_01_iv,weatherIMagePath01);
        String temp01 = weather.getInfo().getHourlyList().get(0).getTemp() + "℃";
        remoteViews.setTextViewText(R.id.widget_large_temp_01_tv,temp01);
        String time01 = weather.getInfo().getHourlyList().get(0).getTime();
        remoteViews.setTextViewText(R.id.widget_large_time_01_tv,time01);
        int weatherIMagePath02 = WeatherInfoHelper.getWeatherImagePath(weather.getInfo().getHourlyList().get(1).getImg());
        remoteViews.setImageViewResource(R.id.widget_large_image_02_iv,weatherIMagePath02);
        String temp02 = weather.getInfo().getHourlyList().get(1).getTemp() + "℃";
        remoteViews.setTextViewText(R.id.widget_large_temp_02_tv,temp02);
        String time02 = weather.getInfo().getHourlyList().get(1).getTime();
        remoteViews.setTextViewText(R.id.widget_large_time_02_tv,time02);
        int weatherIMagePath03 = WeatherInfoHelper.getWeatherImagePath(weather.getInfo().getHourlyList().get(2).getImg());
        remoteViews.setImageViewResource(R.id.widget_large_image_03_iv,weatherIMagePath03);
        String temp03 = weather.getInfo().getHourlyList().get(2).getTemp() + "℃";
        remoteViews.setTextViewText(R.id.widget_large_temp_03_tv,temp03);
        String time03 = weather.getInfo().getHourlyList().get(2).getTime();
        remoteViews.setTextViewText(R.id.widget_large_time_03_tv,time03);
        int weatherIMagePath04 = WeatherInfoHelper.getWeatherImagePath(weather.getInfo().getHourlyList().get(3).getImg());
        remoteViews.setImageViewResource(R.id.widget_large_image_04_iv,weatherIMagePath04);
        String temp04 = weather.getInfo().getHourlyList().get(3).getTemp() + "℃";
        remoteViews.setTextViewText(R.id.widget_large_temp_04_tv,temp04);
        String time04 = weather.getInfo().getHourlyList().get(3).getTime();
        remoteViews.setTextViewText(R.id.widget_large_time_04_tv,time04);
        int weatherIMagePath05 = WeatherInfoHelper.getWeatherImagePath(weather.getInfo().getHourlyList().get(4).getImg());
        remoteViews.setImageViewResource(R.id.widget_large_image_05_iv,weatherIMagePath05);
        String temp05 = weather.getInfo().getHourlyList().get(4).getTemp() + "℃";
        remoteViews.setTextViewText(R.id.widget_large_temp_05_tv,temp05);
        String time05 = weather.getInfo().getHourlyList().get(4).getTime();
        remoteViews.setTextViewText(R.id.widget_large_time_05_tv,time05);
        String forecastTips = WeatherInfoHelper.getDayWeatherTipsInfo(weather.getInfo().getDailyList());
        String[] forecasts = forecastTips.split("\n");
        String forecast = forecasts[0] + forecasts[1];
        remoteViews.setTextViewText(R.id.widget_large_forecast_tv,forecast);
        manager.updateAppWidget(componentName,remoteViews);
    }


}
