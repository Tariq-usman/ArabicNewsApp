package com.system.user.arabicnewsapp.fragments.home;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

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

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;

public class LatestNewsFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView, recyclerViewOpinionArticles, recyclerViewTopStories;
    private AdView adView;
    private ScrollView scrollView;
    private VideoView videoView;
    private AdRequest adRequest;
    private LinearLayout special_report;
    private FrameLayout layoutSpecialReport, layoutHome;
    public static ProgressBar progressBar;

    private String[] title = {"Ministry of IT and Telecommunication", "PTA Test Zong 5G network"};
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("http://androhub.com/demo/demo.mp4", "http://androhub.com/demo/demo.mp4"));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);
       /* scrollView = view.findViewById(R.id.scroll_view);
        scrollView.scrollTo(0,0);
        scrollView.pageScroll(View.FOCUS_UP);*/
        videoView = view.findViewById(R.id.main_latest_video_view);
        final MediaController mediacontroller = new MediaController(getContext());
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        //videoView.setVideoURI(Uri.parse(arrayList.get(index)));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        videoView.setMediaController(mediacontroller);
                        mediacontroller.setAnchorView(videoView);

                    }
                });
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getContext(), "Video over", Toast.LENGTH_SHORT).show();
                mp.release();
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("API123", "What " + what + " extra " + extra);
                return false;
            }
        });
        progressBar = view.findViewById(R.id.main_progress_bar);

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
        recyclerView.setAdapter(new LatestNewsAdapter(getContext(), arrayList, title, videoView));


        recyclerViewOpinionArticles = view.findViewById(R.id.recycler_view_latest_opinion_articles);
        recyclerViewOpinionArticles.setHasFixedSize(true);
        recyclerViewOpinionArticles.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewOpinionArticles.setAdapter(new LatestOpinionArticlesAdapter());


        recyclerViewTopStories = view.findViewById(R.id.recycler_view_latest_top_stories);
        recyclerViewTopStories.setHasFixedSize(true);
        recyclerViewTopStories.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTopStories.setAdapter(new LatestNewsAdapter(getContext(), arrayList, title, videoView));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.special_report:
                layoutHome.setVisibility(View.GONE);
                layoutSpecialReport.setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.main_container, new SpecialReportFragment()).commit();
                break;
        }
    }
}
