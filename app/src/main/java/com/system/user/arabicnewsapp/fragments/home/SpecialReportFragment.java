package com.system.user.arabicnewsapp.fragments.home;

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
import com.system.user.arabicnewsapp.fragments.home.HomeFragment;

public class SpecialReportFragment extends Fragment {
    private FrameLayout layoutSpecialReport,layoutHome,layoutSettingTab;
    private ImageView backBtn;
    private TextView textViewTitle,textViewCount;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_special_report,container,false);

        textViewTitle = getActivity().findViewById(R.id.tv_special_report);
        textViewCount= getActivity().findViewById(R.id.tv_count);

        layoutHome = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutHome.setVisibility(View.GONE);
        layoutSettingTab = getActivity().findViewById(R.id.settings_layout);
        layoutHome.setVisibility(View.GONE);
        layoutSpecialReport = getActivity().findViewById(R.id.special_report_layout);
        layoutSpecialReport.setVisibility(View.VISIBLE);
        backBtn = getActivity().findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
            }
        });

        return view;
    }
}
