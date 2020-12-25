package com.oelmounkad.gameofthrones.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.oelmounkad.gameofthrones.adapters.CharacterAdapter;
import com.oelmounkad.gameofthrones.databinding.HomeBinding;
import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.viewmodel.CharacterViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class Home extends Fragment {
    private static final String TAG = "Home";
    private HomeBinding binding;
    private CharacterViewModel viewModel;
    private CharacterAdapter adapter;
    private ArrayList<Character> characterList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = HomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);

        initRecyclerView();
        observeData();
        viewModel.getCharacters();
    }



    private void observeData() {
        viewModel.getCharacterList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Character>>() {
            @Override
            public void onChanged(ArrayList<Character> characters) {
                Log.e(TAG, "onChanged: " + characters.size() );
                adapter.updateList(characters);
            }
        });
    }

    private void initRecyclerView() {
        binding.characterRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CharacterAdapter(getContext(),characterList);
        binding.characterRecyclerView.setAdapter(adapter);
    }
}
