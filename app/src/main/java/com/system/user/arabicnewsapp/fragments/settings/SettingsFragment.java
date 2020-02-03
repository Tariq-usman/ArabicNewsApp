package com.system.user.arabicnewsapp.fragments.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.system.user.arabicnewsapp.R;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private FrameLayout layoutSearchBar, layoutMenu;
    private LinearLayout layoutSettings, layoutNewsTools, layoutAboutUs, layoutTermsConditions, layoutSupport;
    private ImageView ivBackBtn,ivShareBtn;
    private TextView textViewTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        textViewTitle = getActivity().findViewById(R.id.tv_settings_page);
        textViewTitle.setText("Menu");
        ivBackBtn = getActivity().findViewById(R.id.back_btn_settings);
        ivBackBtn.setVisibility(View.INVISIBLE);
        ivShareBtn = view.findViewById(R.id.iv_share);
        ivShareBtn.setOnClickListener(this);

        layoutSearchBar = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutSearchBar.setVisibility(View.GONE);
        layoutMenu = getActivity().findViewById(R.id.settings_layout);
        layoutMenu.setVisibility(View.VISIBLE);

        layoutSettings = view.findViewById(R.id.layout_settings);
        layoutSettings.setOnClickListener(this);
        layoutNewsTools = view.findViewById(R.id.layout_tools);
        layoutNewsTools.setOnClickListener(this);
        layoutAboutUs = view.findViewById(R.id.layout_about_us);
        layoutAboutUs.setOnClickListener(this);
        layoutTermsConditions = view.findViewById(R.id.layout_terms_conditions);
        layoutTermsConditions.setOnClickListener(this);
        layoutSupport = view.findViewById(R.id.layout_support);
        layoutSupport.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_settings:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new NotificationsSettingsFragment()).commit();
                break;
            case R.id.layout_tools:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new NewsToolsFragment()).commit();
                break;
            case R.id.layout_about_us:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new AboutUsFragment()).commit();
                break;
            case R.id.layout_terms_conditions:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new TermsConditonsFragment()).commit();
                break;
            case R.id.layout_support:
                getFragmentManager().beginTransaction().replace(R.id.main_container,new SupportFragment()).commit();
                break;
            case R.id.iv_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(shareIntent,"Share via"));
                break;
        }
    }
}
