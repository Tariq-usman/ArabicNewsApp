package com.system.user.arabicnewsapp.fragments.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.saved.SavedArticlesAdapter;
import com.system.user.arabicnewsapp.local_db.ArabicNews;
import com.system.user.arabicnewsapp.local_db.ArabicNewsViewModel;

import java.util.List;

public class SavedArticles extends Fragment {
    private RecyclerView recyclerView;
    SavedArticlesAdapter articlesAdapter;
    private ArabicNewsViewModel viewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_articles,container,false);

        recyclerView = view.findViewById(R.id.recycler_saved_articles);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = ViewModelProviders.of(SavedArticles.this).get(ArabicNewsViewModel.class);
        articlesAdapter = new SavedArticlesAdapter();
        recyclerView.setAdapter(articlesAdapter);
        viewModel.getAllData().observe(SavedArticles.this, new Observer<List<ArabicNews>>() {
            @Override
            public void onChanged(List<ArabicNews> arabicNews) {
                articlesAdapter.setData(arabicNews,viewModel);
                articlesAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}
