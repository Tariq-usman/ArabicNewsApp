package com.system.user.arabicnewsapp.local_db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ArabicNews {

    @PrimaryKey(autoGenerate = true)
    private int id=0;
    private int vid_id;
    private String vid_title;
    private String vid_path;
    private int msg_count;
    private String time;

    public ArabicNews(int vid_id, String vid_title, String vid_path, int msg_count, String time) {
        this.vid_id = vid_id;
        this.vid_title = vid_title;
        this.vid_path = vid_path;
        this.msg_count = msg_count;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getVid_id() {
        return vid_id;
    }

    public String getVid_title() {
        return vid_title;
    }

    public String getVid_path() {
        return vid_path;
    }

    public int getMsg_count() {
        return msg_count;
    }

    public String getTime() {
        return time;
    }
}
