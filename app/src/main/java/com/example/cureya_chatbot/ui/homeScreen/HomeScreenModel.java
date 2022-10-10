package com.example.cureya_chatbot.ui.homeScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeScreenModel extends ViewModel {
    private MutableLiveData<String> mText;

    public HomeScreenModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is HomeScreen fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
