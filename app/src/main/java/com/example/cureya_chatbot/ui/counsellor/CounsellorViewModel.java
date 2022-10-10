package com.example.cureya_chatbot.ui.counsellor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CounsellorViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public CounsellorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Counsellor fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
