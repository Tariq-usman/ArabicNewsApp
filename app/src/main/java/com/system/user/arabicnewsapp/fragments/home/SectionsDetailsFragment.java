package com.system.user.arabicnewsapp.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.home.sections.SectionsDetailsAdapter;

public class SectionsDetailsFragment extends Fragment {
    private RecyclerView recyclerView,recyclerViewSpecials;
    private AdView adView;
    AdRequest adRequest;
    private ImageView ivBackBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.framgment_section_details, container, false);

        adView = view.findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        ivBackBtn = view.findViewById(R.id.iv_back_btn);
        ivBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new HomeFragment()).commit();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view_sections_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SectionsDetailsAdapter(getContext()));

        recyclerViewSpecials = view.findViewById(R.id.recycler_view_sections_details_special);
        recyclerViewSpecials.setHasFixedSize(true);
        recyclerViewSpecials.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSpecials.setAdapter(new SectionsDetailsAdapter(getContext()));

        return view;
    }
}
