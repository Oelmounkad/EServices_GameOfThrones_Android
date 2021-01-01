package com.oelmounkad.gameofthrones.di;

import com.oelmounkad.gameofthrones.network.CharApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class RetrofitModule {

    @Provides
    @Singleton
    public static CharApiService provideCharacterApiService(){

        return  new Retrofit.Builder()
                .baseUrl(" https://thronesapi.com/api/v2/") // Url de notre API
                .addConverterFactory(GsonConverterFactory.create()) // Convertisseur Gson pour Serialization / De-serialization
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //Pour Avoir un Observable comme reponse finale
                .build()
                .create(CharApiService.class); // La classe des methodes qui font appel a l'API
    }
}
