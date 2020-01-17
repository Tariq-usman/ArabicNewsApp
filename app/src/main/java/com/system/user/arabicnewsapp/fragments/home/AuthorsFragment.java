package com.system.user.arabicnewsapp.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.home.latest_news.LatestOpinionArticlesAdapter;

public class AuthorsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FrameLayout layout;
    private ImageView ivBack;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authors,container,false);
        layout = getActivity().findViewById(R.id.main_frame_header);
        layout.setVisibility(View.VISIBLE);
        ivBack = view.findViewById(R.id.iv_back_authors);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container,new SectionsOpinionArticlesDetailsFragment()).commit();
            }
        });
        recyclerView = view.findViewById(R.id.recycler_view_authors);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(new LatestOpinionArticlesAdapter());
        return view;
    }
}
