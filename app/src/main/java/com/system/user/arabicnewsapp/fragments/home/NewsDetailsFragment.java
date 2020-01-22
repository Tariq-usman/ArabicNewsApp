package com.system.user.arabicnewsapp.fragments.home;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
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
import com.system.user.arabicnewsapp.adapters.home.latest_news.CommentsAdapter;
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestOpinionArticlesAdapter;
import com.system.user.arabicnewsapp.fragments.home.HomeFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsDetailsFragment extends Fragment {
    private RecyclerView recyclerView,recyclerViewOpinionArticles;
    private AdView adView;
    AdRequest adRequest;
    private FrameLayout layoutSpecialReport,layoutHome;
    private ImageView backBtn;
    private String [] comments = {"Lurem Ipsum is simply dummy text of the printing","Lurem Ipsum is simply dummy","Lurem Ipsum"};

    VideoView videoView;
    ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4", "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"));
    int index = 0;
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



        videoView = view.findViewById(R.id.videoView);
        final MediaController mediacontroller = new MediaController(getContext());
        mediacontroller.setAnchorView(videoView);


        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(Uri.parse(arrayList.get(index)));
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
                if (index++ == arrayList.size()) {
                    index = 0;
                    mp.release();
                    Toast.makeText(getContext(), "Videos completed", Toast.LENGTH_SHORT).show();
                } else {
                    videoView.setVideoURI(Uri.parse(arrayList.get(index)));
                    videoView.start();
                }


            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.d("API123", "What " + what + " extra " + extra);
                return false;
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
