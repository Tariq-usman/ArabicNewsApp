package com.system.user.arabicnewsapp.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestNewsAdapter;
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestOpinionArticlesAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class LatestNewsFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView,recyclerViewOpinionArticles,recyclerViewTopStories;
    private AdView adView;
    AdRequest adRequest;
    private LinearLayout special_report;
    private FrameLayout layoutSpecialReport,layoutHome;

    private String[] title = {"Ministry of IT and Telecommunication", "PTA Test Zong 5G network"};
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("http://androhub.com/demo/demo.mp4", "http://androhub.com/demo/demo.mp4"));
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        adView = view.findViewById(R.id.ad_view);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        layoutHome = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutHome.setVisibility(View.VISIBLE);
        layoutSpecialReport = getActivity().findViewById(R.id.special_report_layout);
        layoutSpecialReport.setVisibility(View.GONE);

        special_report = view.findViewById(R.id.special_report);
        special_report.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recycler_view_latest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new LatestNewsAdapter(getContext(),arrayList,title));


        recyclerViewOpinionArticles = view.findViewById(R.id.recycler_view_latest_opinion_articles);
        recyclerViewOpinionArticles.setHasFixedSize(true);
        recyclerViewOpinionArticles.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerViewOpinionArticles.setAdapter(new LatestOpinionArticlesAdapter());


        recyclerViewTopStories = view.findViewById(R.id.recycler_view_latest_top_stories);
        recyclerViewTopStories.setHasFixedSize(true);
        recyclerViewTopStories.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTopStories.setAdapter(new LatestNewsAdapter(getContext(), arrayList, title));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.special_report:
                layoutHome.setVisibility(View.GONE);
                layoutSpecialReport.setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.main_container,new SpecialReportFragment()).commit();
                break;
        }
    }
}
