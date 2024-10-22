package com.oelmounkad.gameofthrones.db;

import androidx.lifecycle.LiveData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.oelmounkad.gameofthrones.model.Character;


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
