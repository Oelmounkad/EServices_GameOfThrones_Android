package com.oelmounkad.gameofthrones.repository;

import androidx.lifecycle.LiveData;

import com.oelmounkad.gameofthrones.db.CharDao;
import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.network.CharApiService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class Repository {

    private CharDao charDao;
    private CharApiService apiService;

    @Inject
    public Repository(CharDao charDao, CharApiService apiService) {
        this.charDao = charDao;
        this.apiService = apiService;
    }


    public Observable<ArrayList<Character>> getCharacters(){
        return apiService.getCharacters();
    }

    public List<Character> getAllCharacters(){
        return charDao.getAllCharacters();
    }

    public void insertCharacter(Character c){
        charDao.insertCharacter(c);
    }


    public Character getCharacterById(int id){
        return charDao.getCharacterById(id);
    }
}
