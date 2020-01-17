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
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestOpinionArticlesAdapter;
import com.system.user.arabicnewsapp.fragments.home.HomeFragment;

public class NewsDetailsFragment extends Fragment {
    private RecyclerView recyclerView,recyclerViewOpinionArticles;
    private AdView adView;
    AdRequest adRequest;
    private FrameLayout layoutSpecialReport,layoutHome;
    private ImageView backBtn;
    private String [] comments = {"Lurem Ipsum is simply dummy text of the printing","Lurem Ipsum is simply dummy","Lurem Ipsum"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest_details, container, false);

        layoutHome = getActivity().findViewById(R.id.main_frame_header);
        layoutHome.setVisibility(View.GONE);
       /* layoutSpecialReport = getActivity().findViewById(R.id.special_report_layout);
        layoutSpecialReport.setVisibility(View.GONE);*/
        backBtn = view.findViewById(R.id.iv_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutHome.setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.main_container, new HomeFragment()).commit();
            }
        });

        adView = view.findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        recyclerView = view.findViewById(R.id.recycler_view_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new CommentsAdapter(getContext(),comments));


        recyclerViewOpinionArticles = view.findViewById(R.id.recycler_view_latest_opinion_articles);
        recyclerViewOpinionArticles.setHasFixedSize(true);
        recyclerViewOpinionArticles.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewOpinionArticles.setAdapter(new LatestOpinionArticlesAdapter());
        return view;
    }
}
