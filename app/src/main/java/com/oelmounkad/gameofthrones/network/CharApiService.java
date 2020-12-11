package com.oelmounkad.gameofthrones.network;

import com.oelmounkad.gameofthrones.model.Character;

import java.util.ArrayList;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface CharApiService {

    @GET("characters")
    Observable<ArrayList<Character>> getCharacters();
}
