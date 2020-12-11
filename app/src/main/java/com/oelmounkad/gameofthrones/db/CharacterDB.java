package com.oelmounkad.gameofthrones.db;


import androidx.gameofthrones.Database;
import androidx.gameofthrones.RoomDatabase;

import com.oelmounkad.gameofthrones.model.Character;

@Database(entities = {Character.class},version = 2,exportSchema = false)
public abstract class CharacterDB extends RoomDatabase {
    public abstract CharDao charDao();
}
