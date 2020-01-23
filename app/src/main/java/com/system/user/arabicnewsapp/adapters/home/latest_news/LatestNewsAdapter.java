package com.system.user.arabicnewsapp.adapters.home.latest_news;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.system.user.arabicnewsapp.fragments.home.LatestNewsFragment;
import com.system.user.arabicnewsapp.fragments.home.NewsDetailsFragment;
import com.system.user.arabicnewsapp.others.InputStreamVolleyRequest;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.local_db.ArabicNews;
import com.system.user.arabicnewsapp.local_db.ArabicNewsViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.ViewHolder> {
    int productId, count;
    Dialog MyDialog;
    String p_title, p_time;
    String p_count;
    String fileName = "hello";
    String pathWithName;
    Context context;
    ArrayList<String> arrayList;
    String[] title;
    ArabicNewsViewModel viewModel;
    private VideoView videoView;

    public LatestNewsAdapter(Context context, ArrayList<String> arrayList, String[] title, VideoView videoView) {
        this.context = context;
        this.arrayList = arrayList;
        this.title = title;
        this.videoView = videoView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_latest, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.textViewTitle.setText(title[position]);
        holder.videoView.setVideoPath(arrayList.get(position));
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.videoView.start();
                holder.btnPlay.setVisibility(View.GONE);
            }
        });
        holder.videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                videoView.setVideoPath(arrayList.get(position));
                videoView.start();
                LatestNewsFragment.progressBar.setVisibility(View.VISIBLE);
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
                                LatestNewsFragment.progressBar.setVisibility(View.GONE);
                                mp.start();
                            }
                        });
                    }
                });
                return true;
            }
        });


        holder.imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyCustomAlertDialog();
                downloadFile(arrayList.get(position), fileName);

                productId = 12;
                p_title = holder.textViewTitle.getText().toString();
                p_count = holder.textViewCount.getText().toString();
                p_time = holder.textViewTime.getText().toString();
                count = Integer.parseInt(p_count);

            }
        });

        holder.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, arrayList.get(position));
                context.startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsFragment fragment = new NewsDetailsFragment();
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle, textViewCount, textViewTime;
        private ImageView imageViewSave, imageView, ivShare;
        private VideoView videoView;
        private ImageButton btnPlay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnPlay = itemView.findViewById(R.id.btn_play);
            videoView = itemView.findViewById(R.id.video_view);
            imageView = itemView.findViewById(R.id.image);
            textViewCount = itemView.findViewById(R.id.tv_msg_count);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            imageViewSave = itemView.findViewById(R.id.iv_save);
            textViewTime = itemView.findViewById(R.id.tv_time);
            ivShare = itemView.findViewById(R.id.share_latest_news);
        }
    }

    private void downloadFile(String url, final String fileName) {
        InputStreamVolleyRequest request = new InputStreamVolleyRequest(Request.Method.GET, url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                try {

                    if (response != null) {
                        File dir = new File(context.getFilesDir() + "/ArabicNews/");
                        try {
                            /*If directory is not exist then it create directory*/
                            if (!dir.isDirectory()) {
                                if (dir.mkdir()) {
                                    System.out.println("Directory created");
                                } else {
                                    System.out.println("Directory is not created");
                                }
                            }


                            pathWithName = context.getFilesDir() + "/ArabicNews/" + p_title + ".mp4";
                            Log.i("path", pathWithName);
                            OutputStream out = new FileOutputStream(pathWithName);
                            out.write(response);
                            out.close();
                            viewModel = ViewModelProviders.of((FragmentActivity) context).get(ArabicNewsViewModel.class);
                            ArabicNews arabicNews = new ArabicNews(productId, p_title, pathWithName, count, p_time);
                            // Note note = new Note(productId, image, p_title, count, p_time);
                            viewModel.insert(arabicNews);
                            MyDialog.dismiss();
                            //exitAppDialog();
                            Toast.makeText(context, "File Download Sucessfully!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, "Note insert Successfully", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                            /*holder.btn_download.setEnabled(true);
                            progressBar.setVisibility(View.GONE);*/
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    /*holder.btn_download.setEnabled(true);
                    progressBar.setVisibility(View.GONE);*/
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }, null);
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RequestQueue mRequestQueue = Volley.newRequestQueue(context, new HurlStack());
        mRequestQueue.add(request);
    }

    public void MyCustomAlertDialog() {
        MyDialog = new Dialog(context);
        MyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        MyDialog.setContentView(R.layout.customdialog);
        MyDialog.show();
    }

}
