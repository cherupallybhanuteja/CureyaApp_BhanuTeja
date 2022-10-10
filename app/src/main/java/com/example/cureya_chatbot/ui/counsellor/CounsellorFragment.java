//package com.example.cureya_chatbot.ui.counsellor;
//
//import androidx.lifecycle.ViewModelProvider;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.cureya_chatbot.R;
//
//public class CounsellorFragment extends Fragment {
//
//    private CounsellorViewModel mViewModel;
//
//    public static CounsellorFragment newInstance() {
//        return new CounsellorFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_counsellor, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(CounsellorViewModel.class);
//        // TODO: Use the ViewModel
//    }
//
//}

package com.example.cureya_chatbot.ui.counsellor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cureya_chatbot.R;
import com.example.cureya_chatbot.ui.chat.ChatFragment;

public class CounsellorFragment extends Fragment {

    private CounsellorViewModel counsellorViewModelModel;
    private ImageView imgConnect;
    private ProgressBar loading;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_counsellor, container, false);

        loading=root.findViewById(R.id.loading);
        imgConnect = root.findViewById(R.id.imgNextBtn);
        imgConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loading.setVisibility(View.VISIBLE);
            }
        });
        return root;
    }
}
