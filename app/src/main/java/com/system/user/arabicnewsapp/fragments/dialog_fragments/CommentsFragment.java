package com.system.user.arabicnewsapp.fragments.dialog_fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.system.user.arabicnewsapp.R;
import com.system.user.arabicnewsapp.adapters.MainCommentsAdapter;

import java.util.ArrayList;
import java.util.List;


public class CommentsFragment extends DialogFragment implements View.OnClickListener {
    TextView mConfirm,mTitleView;
    ImageView mSend;
    EditText commentText;
    RecyclerView recyclerView;
    List<String> list = new ArrayList<>();
    MainCommentsAdapter mainCommentsAdapter;

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d!=null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comments,container,false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        commentText = view.findViewById(R.id.comment_text);
        mSend = view.findViewById(R.id.send);
        recyclerView =view.findViewById(R.id.comments_recycler_view);
        mainCommentsAdapter = new MainCommentsAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mainCommentsAdapter);

        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentText.getText().toString().trim();
                commentText.setText("");
                list.add(comment);
                int position = list.size()-1;
                recyclerView.smoothScrollToPosition(position);
                mainCommentsAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
           /* case R.id.confirm_dialog_one:
               *//* DialogFragmentDeliveryTime dialogFragmentDeliveryTime = new DialogFragmentDeliveryTime();
                dialogFragmentDeliveryTime.show(getFragmentManager(),"Delivery Time");*//*
                dismiss();
                break;
            case R.id.close_back_view:
                dismiss();
                break;*/
        }

    }
}
