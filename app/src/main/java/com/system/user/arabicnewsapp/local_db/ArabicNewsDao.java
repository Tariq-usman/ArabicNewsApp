package com.system.user.arabicnewsapp.local_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArabicNewsDao {

    @Insert
    Long insert(ArabicNews arabicNews);

    @Query("select * from ArabicNews")
    LiveData<List<ArabicNews>> getNewsData();
}
