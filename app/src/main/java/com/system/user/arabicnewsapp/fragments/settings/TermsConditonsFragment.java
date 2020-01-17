package com.system.user.arabicnewsapp.fragments.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.system.user.arabicnewsapp.R;

public class TermsConditonsFragment extends Fragment {
    private FrameLayout layoutSearchBar,layoutMenu;
    private ImageView ivBackBtn;
    private TextView textViewTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_terms_conditions,container,false);
        textViewTitle = getActivity().findViewById(R.id.tv_settings_page);
        textViewTitle.setText("Terms & Conditions");
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
        return view;
    }
}
