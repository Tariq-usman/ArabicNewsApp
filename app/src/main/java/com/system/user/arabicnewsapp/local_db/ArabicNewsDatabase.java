package com.system.user.arabicnewsapp.local_db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ArabicNews.class},version = 2,exportSchema = false)
public abstract class ArabicNewsDatabase extends RoomDatabase {
    private static ArabicNewsDatabase instance;
    public abstract ArabicNewsDao newsDao();
    public static synchronized ArabicNewsDatabase getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ArabicNewsDatabase.class,ArabicNewsDatabase.class.getName())
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
