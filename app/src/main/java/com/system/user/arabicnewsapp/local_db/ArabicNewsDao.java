package com.system.user.arabicnewsapp.local_db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArabicNewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(ArabicNews arabicNews);

    @Query("select * from ArabicNews")
    LiveData<List<ArabicNews>> getNewsData();

    @Query("UPDATE ArabicNews SET msg_count=:count WHERE vid_id = :vid_id")
    void update(int count, int vid_id);


    @Delete
    public void delete(ArabicNews arabicNews);

    /*@Query("delete from ArabicNews")
    void deleteAllRecord(ArabicNews arabicNews);*/
}
