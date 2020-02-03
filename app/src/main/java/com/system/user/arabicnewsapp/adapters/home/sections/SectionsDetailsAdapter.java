package com.system.user.arabicnewsapp.adapters.home.sections;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.fragments.home.SectionsOpinionArticlesFragment;

public class SectionsDetailsAdapter extends RecyclerView.Adapter<SectionsDetailsAdapter.ViewHolder> {
    Context context;
    private String[] titles;
    public SectionsDetailsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_items_latest, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       // holder.textView.setText(titles[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SectionsOpinionArticlesFragment fragment = new SectionsOpinionArticlesFragment();
                FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, fragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            textView = itemView.findViewById(R.id.tv_title);
        }
    }
}
