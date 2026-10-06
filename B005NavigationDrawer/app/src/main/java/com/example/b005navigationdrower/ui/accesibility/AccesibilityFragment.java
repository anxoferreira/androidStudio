package com.example.b005navigationdrower.ui.accesibility;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.b005navigationdrower.databinding.FragmentAccesibilityBinding;
import com.example.b005navigationdrower.databinding.FragmentGalleryBinding;

public class AccesibilityFragment extends Fragment {

    private FragmentAccesibilityBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AccesibilityViewModel accesibilityViewModel =
                new ViewModelProvider(this).get(AccesibilityViewModel.class);

        binding = FragmentAccesibilityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAccesibility;
        accesibilityViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}