package com.example.cureya_chatbot.ui.homeScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.cureya_chatbot.R;
import com.example.cureya_chatbot.ui.chat.ChatFragment;

public class HomeScreenFragment extends Fragment {

    private HomeScreenModel homeScreenModel;
    private ImageView imgChat;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        /*homeScreenModel =
                new ViewModelProvider(this).get(HomeScreenModel.class);*/
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        imgChat = root.findViewById(R.id.imgChatBtn);
        imgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ChatFragment chatFragment = new ChatFragment();
                ft.replace(R.id.nav_host_fragment, chatFragment);
                ft.commit();
            }
        });
      /*  final TextView textView = root.findViewById(R.id.text_home_screen);
        homeScreenModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }
}
