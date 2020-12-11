package com.oelmounkad.gotapistarter.db;

import androidx.lifecycle.LiveData;
import androidx.gameofthrones.Dao;
import androidx.gameofthrones.Insert;
import androidx.gameofthrones.Query;

import com.oelmounkad.gotapistarter.model.Character;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface CharDao {

    @Insert
    void insertCharacter(Character character);

    @Query("SELECT * FROM characters WHERE id=:id")
    Character getCharacterById(int id);

    @Query("SELECT * FROM characters")
    List<Character> getAllCharacters();
}
