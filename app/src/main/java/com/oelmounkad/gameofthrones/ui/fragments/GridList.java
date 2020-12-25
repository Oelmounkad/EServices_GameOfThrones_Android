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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.oelmounkad.gameofthrones.adapters.CharacterAdapter;
import com.oelmounkad.gameofthrones.adapters.CharacterAdapterGrid;
import com.oelmounkad.gameofthrones.databinding.GridListBinding;

import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.viewmodel.CharacterViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class GridList extends Fragment {
    private static final String TAG = "Home";
    private GridListBinding binding;
    // private PokemonViewModel viewModel;
    private CharacterViewModel viewModel;
    // private PokemonAdapter adapter;
    private CharacterAdapterGrid adapter;
    private ArrayList<Character> characterList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = GridListBinding.inflate(inflater,container,false);
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
        binding.characterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        adapter = new CharacterAdapterGrid(getContext(),characterList);
        binding.characterRecyclerView.setAdapter(adapter);
    }
}
