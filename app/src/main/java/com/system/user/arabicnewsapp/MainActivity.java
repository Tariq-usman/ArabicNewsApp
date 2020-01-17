package com.system.user.arabicnewsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.system.user.arabicnewsapp.fragments.home.HomeFragment;
import com.system.user.arabicnewsapp.fragments.notificatons.NotificationsFragment;
import com.system.user.arabicnewsapp.fragments.saved.SavedFragment;
import com.system.user.arabicnewsapp.fragments.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayoutHome, linearLayoutNotifications, linearLayoutSaved, linearLayoutSettings;
    private ImageView imageViewHome, imageViewNotifications, imageViewSaved, imageViewSettings;
    private TextView textViewHome, textViewNotifications, textViewSaved, textViewSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
        }
        linearLayoutHome = findViewById(R.id.layout_home);
        linearLayoutHome.setOnClickListener(this);
        linearLayoutNotifications = findViewById(R.id.layout_notifications);
        linearLayoutNotifications.setOnClickListener(this);
        linearLayoutSaved = findViewById(R.id.layout_saved);
        linearLayoutSaved.setOnClickListener(this);
        linearLayoutSettings = findViewById(R.id.layout_settings);
        linearLayoutSettings.setOnClickListener(this);

        imageViewHome = findViewById(R.id.iv_home);
        imageViewNotifications = findViewById(R.id.iv_notification);
        imageViewSaved = findViewById(R.id.iv_saved);
        imageViewSettings = findViewById(R.id.iv_settings);

        textViewHome = findViewById(R.id.tv_home);
        textViewNotifications = findViewById(R.id.tv_notification);
        textViewSaved = findViewById(R.id.tv_saved);
        textViewSettings = findViewById(R.id.tv_settings);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_home:
                linearLayoutHome.setBackgroundResource(R.drawable.bg_bottom_nav_items);
                imageViewHome.setImageResource(R.drawable.ic_home);
                textViewHome.setTextColor(getResources().getColor(R.color.grayColor));

                linearLayoutNotifications.setBackgroundResource(0);
                imageViewNotifications.setImageResource(R.drawable.ic_notification_white);
                textViewNotifications.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutSaved.setBackgroundResource(0);
                imageViewSaved.setImageResource(R.drawable.ic_saved_white);
                textViewSaved.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutSettings.setBackgroundResource(0);
                imageViewSettings.setImageResource(R.drawable.ic_settings_white);
                textViewSettings.setTextColor(getResources().getColor(R.color.whiteColor));
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
                break;
            case R.id.layout_notifications:
                linearLayoutHome.setBackgroundResource(0);
                imageViewHome.setImageResource(R.drawable.ic_home_white);
                textViewHome.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutNotifications.setBackgroundResource(R.drawable.bg_bottom_nav_items);
                imageViewNotifications.setImageResource(R.drawable.ic_notification_purple);
                textViewNotifications.setTextColor(getResources().getColor(R.color.grayColor));
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new NotificationsFragment()).commit();

                linearLayoutSaved.setBackgroundResource(0);
                imageViewSaved.setImageResource(R.drawable.ic_saved_white);
                textViewSaved.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutSettings.setBackgroundResource(0);
                imageViewSettings.setImageResource(R.drawable.ic_settings_white);
                textViewSettings.setTextColor(getResources().getColor(R.color.whiteColor));
                break;
            case R.id.layout_saved:
                linearLayoutHome.setBackgroundResource(0);
                imageViewHome.setImageResource(R.drawable.ic_home_white);
                textViewHome.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutNotifications.setBackgroundResource(0);
                imageViewNotifications.setImageResource(R.drawable.ic_notification_white);
                textViewNotifications.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutSaved.setBackgroundResource(R.drawable.bg_bottom_nav_items);
                imageViewSaved.setImageResource(R.drawable.ic_saved_purple);
                textViewSaved.setTextColor(getResources().getColor(R.color.grayColor));
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new SavedFragment()).commit();

                linearLayoutSettings.setBackgroundResource(0);
                imageViewSettings.setImageResource(R.drawable.ic_settings_white);
                textViewSettings.setTextColor(getResources().getColor(R.color.whiteColor));
                break;
            case R.id.layout_settings:
                linearLayoutHome.setBackgroundResource(0);
                imageViewHome.setImageResource(R.drawable.ic_home_white);
                textViewHome.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutNotifications.setBackgroundResource(0);
                imageViewNotifications.setImageResource(R.drawable.ic_notification_white);
                textViewNotifications.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutSaved.setBackgroundResource(0);
                imageViewSaved.setImageResource(R.drawable.ic_saved_white);
                textViewSaved.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutSettings.setBackgroundResource(R.drawable.bg_bottom_nav_items);
                imageViewSettings.setImageResource(R.drawable.ic_settings_purple);
                textViewSettings.setTextColor(getResources().getColor(R.color.grayColor));
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container,new SettingsFragment()).commit();
                break;
        }
    }
}
