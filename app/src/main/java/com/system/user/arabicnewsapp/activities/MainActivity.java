package com.system.user.arabicnewsapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.fragments.home.HomeFragment;
import com.system.user.arabicnewsapp.fragments.notificatons.NotificationsFragment;
import com.system.user.arabicnewsapp.fragments.saved.SavedFragment;
import com.system.user.arabicnewsapp.fragments.settings.SettingsFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayoutHome, linearLayoutNotifications, linearLayoutSaved, linearLayoutSettings;
    private ImageView imageViewLogo, imageViewSearch, imageViewHome, imageViewNotifications, imageViewSaved, imageViewSettings;
    private TextView textViewHome, textViewNotifications, textViewSaved, textViewSettings;
    private EditText etSearch;
    private FrameLayout layout;
    private int STORAGE_PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isPermissionOk();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
        }
        imageViewLogo = findViewById(R.id.iv_logo);
        imageViewSearch = findViewById(R.id.iv_search_view);
        imageViewSearch.setOnClickListener(this);
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

        etSearch = findViewById(R.id.et_search_view);
        textViewHome = findViewById(R.id.tv_home);
        textViewNotifications = findViewById(R.id.tv_notification);
        textViewSaved = findViewById(R.id.tv_saved);
        textViewSettings = findViewById(R.id.tv_settings);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int delayMillis = 8000;
                Handler handler = new Handler();
//                final View v = tvRQPoint; // your view
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TranslateAnimation animate = new TranslateAnimation(etSearch.getWidth(), 0, 0, 0);
                        animate.setDuration(500);
                        animate.setFillAfter(true);
                        imageViewLogo.startAnimation(animate);
                        imageViewLogo.setVisibility(View.VISIBLE);
                        etSearch.setVisibility(View.GONE);
                    }
                }, delayMillis);
            }
        });
    }

    private void isPermissionOk() {
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "You have already granted this permission!",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_view:
                TranslateAnimation animate = new TranslateAnimation(0, -etSearch.getWidth(), 0, 0);
                animate.setDuration(500);
                animate.setFillAfter(true);
                imageViewLogo.startAnimation(animate);
                imageViewLogo.setVisibility(View.GONE);
                etSearch.setVisibility(View.VISIBLE);

                break;
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
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
                break;
            case R.id.layout_notifications:
                linearLayoutHome.setBackgroundResource(0);
                imageViewHome.setImageResource(R.drawable.ic_home_white);
                textViewHome.setTextColor(getResources().getColor(R.color.whiteColor));

                linearLayoutNotifications.setBackgroundResource(R.drawable.bg_bottom_nav_items);
                imageViewNotifications.setImageResource(R.drawable.ic_notification_purple);
                textViewNotifications.setTextColor(getResources().getColor(R.color.grayColor));
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new NotificationsFragment()).commit();

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
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new SavedFragment()).commit();

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
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new SettingsFragment()).commit();
                break;
        }
    }
}
