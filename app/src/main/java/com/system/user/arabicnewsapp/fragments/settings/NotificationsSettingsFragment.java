package com.system.user.arabicnewsapp.fragments.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.system.user.arabicnewsapp.R;

public class NotificationsSettingsFragment extends Fragment {
    private FrameLayout layoutSearchBar,layoutMenu;
    private ImageView ivBackBtn,ivShare;
    private TextView textViewTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notifications_settings,container,false);
        textViewTitle = getActivity().findViewById(R.id.tv_settings_page);
        textViewTitle.setText("Notification Settings");
        ivBackBtn = getActivity().findViewById(R.id.back_btn_settings);
        ivBackBtn.setVisibility(View.VISIBLE);
        layoutSearchBar = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutSearchBar.setVisibility(View.GONE);
        layoutMenu = getActivity().findViewById(R.id.settings_layout);
        layoutMenu.setVisibility(View.VISIBLE);
        ivBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new SettingsFragment()).commit();

            }
        });
        ivShare = view.findViewById(R.id.iv_share_notify_set);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(intent,"Share via"));
            }
        });
        return view;
    }
}
