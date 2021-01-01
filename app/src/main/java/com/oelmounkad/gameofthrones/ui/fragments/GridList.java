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
    private static final String TAG = "Grid List";
    private GridListBinding binding;

    private CharacterViewModel viewModel;

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


    // On observe les données s'ils changent cette fonction vas etre notifié du coup on peut update l'Adapter de notre recycler view
    private void observeData() {
        viewModel.getCharacterList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Character>>() {
            @Override
            public void onChanged(ArrayList<Character> characters) {
                adapter.updateList(characters);
            }
        });
    }

    private void initRecyclerView() {
        //Pour l'affichage sous forme de Grille
        binding.characterRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        //Donner la liste a l'adapteur
        adapter = new CharacterAdapterGrid(getContext(),characterList);
        // Binder l'adapter au recyclerView
        binding.characterRecyclerView.setAdapter(adapter);
    }
}
