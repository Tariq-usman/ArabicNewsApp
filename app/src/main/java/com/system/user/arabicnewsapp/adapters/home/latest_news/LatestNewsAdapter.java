package com.system.user.arabicnewsapp.adapters.home.latest_news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.amitshekhar.DebugDB;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.system.user.arabicnewsapp.InputStreamVolleyRequest;
import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.local_db.ArabicNews;
import com.system.user.arabicnewsapp.local_db.ArabicNewsViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class LatestNewsAdapter extends RecyclerView.Adapter<LatestNewsAdapter.ViewHolder> {
    int productId, count;
    String p_title, p_time;
    String p_count;
    String fileName = "hello";
    String pathWithName;
    Context context;
    ArrayList<String> arrayList;
    String[] title;
    ArabicNewsViewModel viewModel;
    public LatestNewsAdapter(Context context, ArrayList<String> arrayList, String[] title) {
        this.context = context;
        this.arrayList = arrayList;
        this.title = title;
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
        holder.videoView.start();





        holder.imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, ""+ DebugDB.getAddressLog(), Toast.LENGTH_LONG).show();

                downloadFile(arrayList.get(position),fileName);

                productId = 12;
                p_title = holder.textViewTitle.getText().toString();
                p_count = holder.textViewCount.getText().toString();
                p_time = holder.textViewTime.getText().toString();
                count = Integer.parseInt(p_count);

            }
        });
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDetailsFragment fragment = new NewsDetailsFragment();
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle, textViewCount, textViewTime;
        private ImageView imageViewSave, imageView;
        private VideoView videoView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video_view);
            imageView = itemView.findViewById(R.id.image);
            textViewCount = itemView.findViewById(R.id.tv_msg_count);
            textViewTitle = itemView.findViewById(R.id.tv_title);
            imageViewSave = itemView.findViewById(R.id.iv_save);
            textViewTime = itemView.findViewById(R.id.tv_time);
        }
    }

    private void downloadFile(String url, final String fileName) {
        InputStreamVolleyRequest request = new InputStreamVolleyRequest(Request.Method.GET, url, new Response.Listener<byte[]>() {
            @Override
            public void onResponse(byte[] response) {
                try {

                    if (response!=null) {
                        File dir = new File(context.getFilesDir()+"/ArabicNews/");
                        try{
                            /*If directory is not exist then it create directory*/
                            if(!dir.isDirectory()){
                                if(dir.mkdir()) {
                                    System.out.println("Directory created");
                                } else {
                                    System.out.println("Directory is not created");
                                }
                            }


                            pathWithName = context.getFilesDir()+"/ArabicNews/"+System.currentTimeMillis()+".mp4";
                            Log.i("path",pathWithName);
                            OutputStream out = new FileOutputStream(pathWithName);
                            out.write(response);
                            out.close();
                            viewModel = ViewModelProviders.of((FragmentActivity) context).get(ArabicNewsViewModel.class);
                            ArabicNews arabicNews = new ArabicNews(productId,p_title,pathWithName,count,p_time);
                            // Note note = new Note(productId, image, p_title, count, p_time);
                            viewModel.insert(arabicNews);
                            Toast.makeText(context, "File Download Sucessfully!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, "Note insert Successfully", Toast.LENGTH_SHORT).show();
                        }catch(Exception e){
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
        } ,new Response.ErrorListener() {

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

}
