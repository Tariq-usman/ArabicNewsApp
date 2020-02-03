package com.system.user.arabicnewsapp.fragments.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestNewsAdapter;
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestOpinionArticlesAdapter;
import com.system.user.arabicnewsapp.fragments.dialog_fragments.CommentsFragment;
import com.system.user.arabicnewsapp.interfaces.RecyclerClickInterface;
import com.system.user.arabicnewsapp.utils.NetworkHelper;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;

public class LatestNewsFragment extends Fragment implements View.OnClickListener, RecyclerClickInterface {
    private RecyclerView recyclerView, recyclerViewOpinionArticles, recyclerViewTopStories;
    private AdView adView;
    private ScrollView scrollView;
    private VideoView videoView;
    private AdRequest adRequest;
    private LinearLayout special_report;
    private FrameLayout layoutSpecialReport, layoutHome;
    private ProgressBar progressBar;
    private ImageView ivComments,ivShare;
    private int pos;
    private boolean isNetworkAvailable;


    private String[] title = {"Ministry of IT and Telecommunication", "PTA Test Zong 5G network"};
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("http://androhub.com/demo/demo.mp4", "http://androhub.com/demo/demo.mp4"));

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_latest, container, false);

        isNetworkAvailable = NetworkHelper.isNetworkAvailable(getContext());
        Log.e("Network Availability", String.valueOf(isNetworkAvailable));

        videoView = view.findViewById(R.id.main_latest_video_view);
        final MediaController mediacontroller = new MediaController(getContext());
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
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

        ivComments = view.findViewById(R.id.latest_news_comments);
        ivComments.setOnClickListener(this);
        ivShare = view.findViewById(R.id.share);
        ivShare.setOnClickListener(this);
        special_report = view.findViewById(R.id.special_report);
        special_report.setOnClickListener(this);

        recyclerView = view.findViewById(R.id.recycler_view_latest);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new LatestNewsAdapter(getContext(), arrayList, title, this));


        recyclerViewOpinionArticles = view.findViewById(R.id.recycler_view_latest_opinion_articles);
        recyclerViewOpinionArticles.setHasFixedSize(true);
        recyclerViewOpinionArticles.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewOpinionArticles.setAdapter(new LatestOpinionArticlesAdapter());


        recyclerViewTopStories = view.findViewById(R.id.recycler_view_latest_top_stories);
        recyclerViewTopStories.setHasFixedSize(true);
        recyclerViewTopStories.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTopStories.setAdapter(new LatestNewsAdapter(getContext(), arrayList, title, this));
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.latest_news_comments:
                CommentsFragment commentsFragment = new CommentsFragment();
                commentsFragment.show(getFragmentManager(),"Comments");
                break;
            case R.id.share:
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, arrayList.get(pos));
                startActivity(Intent.createChooser(shareIntent, "Share via"));
                break;
            case R.id.special_report:
                layoutHome.setVisibility(View.GONE);
                layoutSpecialReport.setVisibility(View.VISIBLE);
                getFragmentManager().beginTransaction().replace(R.id.main_container, new SpecialReportFragment()).commit();
                break;
        }
    }

    @Override
    public void interfaceOnClick(View view, int position) {

        if (isNetworkAvailable == true) {
            videoView.setVideoPath(arrayList.get(position));
            pos = position;
            videoView.start();
            progressBar.setVisibility(View.VISIBLE);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.start();
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int arg1,
                                                       int arg2) {
                            // TODO Auto-generated method stub
                            progressBar.setVisibility(View.GONE);
                            mp.start();
                        }
                    });
                }
            });
        } else {
            Toast.makeText(getContext(), "Please check your network connectivity!", Toast.LENGTH_SHORT).show();
        }
    }
}
