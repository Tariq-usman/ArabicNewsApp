package com.system.user.arabicnewsapp.local_db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ArabicNewsViewModel extends AndroidViewModel {
    private ArabicNewsRepository newsRepository;
    private LiveData<List<ArabicNews>> allData;
    public ArabicNewsViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new ArabicNewsRepository(application);
        allData = newsRepository.getNewsData();
    }
    public void insert(ArabicNews arabicNews){
        newsRepository.insert(arabicNews);
    }

    public LiveData<List<ArabicNews>> getAllData() {
        return allData;
    }

    public void delete(ArabicNews arabicNews){
        newsRepository.delete(arabicNews);
    }
}
