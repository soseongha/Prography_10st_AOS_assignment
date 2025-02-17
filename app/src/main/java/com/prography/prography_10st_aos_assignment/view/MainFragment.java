package com.prography.prography_10st_aos_assignment.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.prography.prography_10st_aos_assignment.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    //private HomeViewModel viewModel;
    private Context context;
    private final String TAG = getClass().toString();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();

        return root;
    }
}
