package com.system.user.arabicnewsapp.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.home.sections.SectionsOpinionArticlesAdapter;

public class SectionsOpinionArticlesFragment extends Fragment {
    private RecyclerView recyclerView;
    private AdView adView;
    AdRequest adRequest;
    private FrameLayout layout;
    private ImageView ivBackBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sections_opinion_articles,container,false);
        layout = getActivity().findViewById(R.id.main_frame_header);
        layout.setVisibility(View.VISIBLE);
        adView = view.findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        ivBackBtn = view.findViewById(R.id.iv_back_btn_opinion);
        ivBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new SectionsDetailsFragment()).commit();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view_sections_opinion_articles);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new SectionsOpinionArticlesAdapter(getContext()));
        return view;
    }
}
