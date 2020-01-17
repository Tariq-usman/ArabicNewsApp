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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.home.latest_news.CommentsAdapter;
import com.system.user.arabicnewsapp.adapters.home.sections.SectionsOpinionArticlesRelatedAdapter;

public class SectionsOpinionArticlesDetailsFragment extends Fragment {
    private RecyclerView recyclerView, recyclerViewComments;
    private ImageView ivBackBtn;
    private AdView adView;
    AdRequest adRequest;
    private FrameLayout layout;
    private String [] comments = {"Lurem Ipsum is simply dummy text of the printing","Lurem Ipsum is simply dummy","Lurem Ipsum"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sectons_opinion_articles_details, container, false);

        layout = getActivity().findViewById(R.id.main_frame_header);
        layout.setVisibility(View.GONE);
        adView = view.findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        ivBackBtn = view.findViewById(R.id.iv_back_btn_opinion_details);
        ivBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new SectionsOpinionArticlesFragment()).commit();
            }
        });

        recyclerViewComments = view.findViewById(R.id.recycler_view_sections_opinion_comments);
        recyclerViewComments.setHasFixedSize(true);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewComments.setAdapter(new CommentsAdapter(getContext(),comments));

        recyclerView = view.findViewById(R.id.recycler_view_sections_related_articles);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(new SectionsOpinionArticlesRelatedAdapter(getContext()));
        return view;
    }
}
