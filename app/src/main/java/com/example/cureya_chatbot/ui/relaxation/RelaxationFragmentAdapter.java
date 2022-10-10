package com.example.cureya_chatbot.ui.relaxation;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class RelaxationFragmentAdapter extends FragmentStateAdapter {
    public RelaxationFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 1:
                return new YogaFragment();
            case 2:
                return new MusicFragment();
        }
        return new MeditationFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
