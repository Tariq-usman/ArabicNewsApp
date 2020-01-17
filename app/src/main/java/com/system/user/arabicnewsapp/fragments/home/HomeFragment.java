package com.system.user.arabicnewsapp.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.system.user.arabicnewsapp.R;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FrameLayout layout,layoutHome,layoutSettingTab,layoutSearchBar;
    private LinearLayout linearLayoutLatest,linearLayoutMostRead,linearLayoutSections;
    private View viewLatest,viewMostRead,viewSections;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        layout = getActivity().findViewById(R.id.main_frame_header);
        layout.setVisibility(View.VISIBLE);
        layoutSearchBar = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutSearchBar.setVisibility(View.VISIBLE);
        layoutSettingTab = getActivity().findViewById(R.id.settings_layout);
        layoutSettingTab.setVisibility(View.GONE);
        getFragmentManager().beginTransaction().replace(R.id.home_container,new LatestNewsFragment()).commit();

        linearLayoutLatest = view.findViewById(R.id.layout_latest);
        linearLayoutLatest.setOnClickListener(this);
        linearLayoutMostRead= view.findViewById(R.id.layout_most_read);
        linearLayoutMostRead.setOnClickListener(this);
        linearLayoutSections= view.findViewById(R.id.layout_sections);
        linearLayoutSections.setOnClickListener(this);
        viewLatest = view.findViewById(R.id.view_latest);
        viewMostRead= view.findViewById(R.id.view_most_read);
        viewSections= view.findViewById(R.id.view_sections);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_latest:
                getFragmentManager().beginTransaction().replace(R.id.home_container,new LatestNewsFragment()).commit();
                viewLatest.setVisibility(View.VISIBLE);
                viewMostRead.setVisibility(View.INVISIBLE);
                viewSections.setVisibility(View.INVISIBLE);
                break;
            case R.id.layout_most_read:
                getFragmentManager().beginTransaction().replace(R.id.home_container,new MostReadFragment()).commit();
                viewLatest.setVisibility(View.INVISIBLE);
                viewMostRead.setVisibility(View.VISIBLE);
                viewSections.setVisibility(View.INVISIBLE);
                break;
            case R.id.layout_sections:
                getFragmentManager().beginTransaction().replace(R.id.home_container,new SectionsFragment()).commit();
                viewLatest.setVisibility(View.INVISIBLE);
                viewMostRead.setVisibility(View.INVISIBLE);
                viewSections.setVisibility(View.VISIBLE);
                break;
        }
    }
}
