package com.system.user.arabicnewsapp.local_db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ArabicNewsRepository {
    private ArabicNewsDao newsDao;
    private LiveData<List<ArabicNews>> newsData;

    public ArabicNewsRepository(Application application) {
        ArabicNewsDatabase newsDatabase = ArabicNewsDatabase.getInstance(application);
        newsDao = newsDatabase.newsDao();
        newsData = newsDao.getNewsData();
    }
    public void insert(ArabicNews arabicNews){
        new InsertNewsDataAsyncTask(newsDao).execute(arabicNews);
    }

    public LiveData<List<ArabicNews>> getNewsData(){
        return newsData;
    }
    private static class InsertNewsDataAsyncTask extends AsyncTask<ArabicNews , Void,Void>{
        private ArabicNewsDao newsDao;
        private InsertNewsDataAsyncTask(ArabicNewsDao arabicNews){
            this.newsDao = arabicNews;
        }
        @Override
        protected Void doInBackground(ArabicNews... arabicNews) {
            newsDao.insert(arabicNews[0]);
            return null;
        }
    }
}
