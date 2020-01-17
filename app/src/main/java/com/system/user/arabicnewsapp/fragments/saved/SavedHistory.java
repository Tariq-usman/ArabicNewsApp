package com.system.user.arabicnewsapp.fragments.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.saved.SavedArticlesAdapter;
import com.system.user.arabicnewsapp.adapters.saved.SavedHistoryAdapter;

public class SavedHistory extends Fragment {
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_history, container, false);

        recyclerView = view.findViewById(R.id.recycler_saved_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new SavedHistoryAdapter(getContext()));
        return view;
    }
}
