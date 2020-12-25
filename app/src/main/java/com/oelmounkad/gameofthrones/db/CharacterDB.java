package com.oelmounkad.gameofthrones.db;

import androidx.room.RoomDatabase;
import androidx.room.Database;
import com.oelmounkad.gameofthrones.model.Character;

@Database(entities = {Character.class},version = 3,exportSchema = false)
public abstract class CharacterDB extends RoomDatabase {
    public abstract CharDao charDao();
}
