package com.oelmounkad.gameofthrones.di;

import android.app.Application;

import androidx.room.Room;

import com.oelmounkad.gameofthrones.db.CharDao;
import com.oelmounkad.gameofthrones.db.CharacterDB;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DbModule {

    @Provides
    @Singleton
    public static CharacterDB provideCharacterDB(Application application){
        return Room.databaseBuilder(application,CharacterDB.class,"Characters Database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static CharDao provideCharDao(CharacterDB characterDB){
        return characterDB.charDao();
    }
}
