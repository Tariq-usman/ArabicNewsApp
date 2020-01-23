package com.system.user.arabicnewsapp.adapters.saved;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.fragments.home.SectionsOpinionArticlesFragment;
import com.system.user.arabicnewsapp.local_db.ArabicNews;
import com.system.user.arabicnewsapp.local_db.ArabicNewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class SavedArticlesAdapter extends RecyclerView.Adapter<SavedArticlesAdapter.ViewHolder> {
    Context context;
    private String[] titles;
    private List<ArabicNews> arabicNews= new ArrayList<>();
    ArabicNewsViewModel viewModel;
    byte[] bytArray;
    public void setData(List<ArabicNews> arabicNews, ArabicNewsViewModel arabicNewsViewModel){
        this.arabicNews = arabicNews;
        this.viewModel = arabicNewsViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_saved_articles, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArabicNews news= arabicNews.get(position);
        holder.textView.setText(news.getVid_title());
        holder.videoView.setVideoPath(news.getVid_path());
        holder.videoView.start();
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsOpinionArticlesFragment fragment = new SectionsOpinionArticlesFragment();
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arabicNews.size();
    }


    public ArabicNews getNewsDataAt(int position){
        return arabicNews.get(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private VideoView videoView;
        private ImageView ivShare;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.saved_video_view);
            textView = itemView.findViewById(R.id.tv_saved_article);
            ivShare = itemView.findViewById(R.id.share_saved_article);
        }
    }
}
