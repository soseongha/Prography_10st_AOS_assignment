package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.prography.prography_10st_aos_assignment.databinding.FragmentMainBinding;
import com.prography.prography_10st_aos_assignment.databinding.FragmentRandomphotoBinding;

public class RandomphotoFragment extends Fragment {
    private FragmentRandomphotoBinding binding;
    //private HomeViewModel viewModel;
    private Context context;
    private final String TAG = getClass().toString();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentRandomphotoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();

        return root;
    }
}
