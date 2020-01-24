package com.system.user.arabicnewsapp.fragments.notificatons;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.notifications.NotificationsAdapter;

public class NotificationsFragment extends Fragment {
    private RecyclerView recyclerView;
    private FrameLayout layout,layoutSearchBar,layoutMenu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications,container,false);

        layout = getActivity().findViewById(R.id.main_frame_header);
        layout.setVisibility(View.VISIBLE);
        layoutSearchBar = getActivity().findViewById(R.id.main_search_bar_layout);
        layoutSearchBar.setVisibility(View.VISIBLE);
        layoutMenu = getActivity().findViewById(R.id.settings_layout);
        layoutMenu.setVisibility(View.GONE);


        recyclerView = view.findViewById(R.id.recycler_view_notifications);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new NotificationsAdapter(getContext()));
        return view;
    }
}
