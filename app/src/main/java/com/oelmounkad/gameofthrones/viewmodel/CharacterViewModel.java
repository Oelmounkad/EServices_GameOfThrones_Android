package com.oelmounkad.gameofthrones.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.repository.Repository;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CharacterViewModel extends ViewModel {
    private static final String TAG = "CharacterViewModel";

    private Repository repository;
    private MutableLiveData<ArrayList<Character>> characterList = new MutableLiveData<>();

    @ViewModelInject
    public CharacterViewModel(Repository repository) {
        this.repository = repository;

    }

    public MutableLiveData<ArrayList<Character>> getCharacterList() {
        return characterList;
    }

    public void getCharacters() {
        repository.getCharacters()
                .subscribeOn(Schedulers.io())
                .map(charListResponse -> {
                    ArrayList<Character> list = charListResponse;
                    if(repository.getAllCharacters() == null){
                        // Persist All characters to the DB
                        for (Character ch: list) {
                            repository.insertCharacter(ch);
                        }
                    }


                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> characterList.setValue(result),
                        error -> Log.e(TAG, "getCharacters: " + error.getMessage()));
    }

    public void insertCharacter(Character c) {
        repository.insertCharacter(c);
    }

    public Character getCharacterById(int id) {
        return repository.getCharacterById(id);
    }
}


