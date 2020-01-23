package com.system.user.arabicnewsapp.fragments.saved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;

public class SavedFragment extends Fragment implements View.OnClickListener {
    private LinearLayout layoutSaved,layoutHistory;
    private View viewSaved,viewHistory;
    private FrameLayout layout,layoutSearchBar,layoutMenu;
    ItemTouchHelper itemTouchHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved,container,false);

        layout = getActivity().findViewById(R.id.main_frame_header);
        layout.setVisibility(View.VISIBLE);
        layoutSearchBar = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutSearchBar.setVisibility(View.VISIBLE);
        layoutMenu = getActivity().findViewById(R.id.settings_layout);
        layoutMenu.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().replace(R.id.saved_news_container, new SavedArticles()).commit();
        }
        layoutSaved = view.findViewById(R.id.layout_saved_articles);
        layoutSaved.setOnClickListener(this);
        layoutHistory= view.findViewById(R.id.layout_history);
        layoutHistory.setOnClickListener(this);
        viewSaved = view.findViewById(R.id.view_saved_articles);
        viewHistory = view.findViewById(R.id.view_history);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_saved_articles:
                getFragmentManager().beginTransaction().replace(R.id.saved_news_container, new SavedArticles()).commit();
                viewSaved.setVisibility(View.VISIBLE);
                viewHistory.setVisibility(View.INVISIBLE);
                break;
            case R.id.layout_history:
                getFragmentManager().beginTransaction().replace(R.id.saved_news_container, new SavedHistory()).commit();
                viewSaved.setVisibility(View.INVISIBLE);
                viewHistory.setVisibility(View.VISIBLE);
                break;
        }
    }
}
