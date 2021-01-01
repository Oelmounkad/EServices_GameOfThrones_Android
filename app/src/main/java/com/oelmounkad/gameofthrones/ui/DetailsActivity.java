package com.oelmounkad.gameofthrones.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oelmounkad.gameofthrones.R;
import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.viewmodel.CharacterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {
    private CharacterViewModel viewModel;

    TextView tvFullname , tvFamily , tvTitle;
    ImageView ivImage;
    String pos;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        viewModel = new ViewModelProvider(this).get(CharacterViewModel.class);

        tvFullname = findViewById(R.id.tvFullname);
        tvFamily = findViewById(R.id.tvFamily);
        tvTitle = findViewById(R.id.tvTitle);
        ivImage = findViewById(R.id.ivImage);

        // On récupére la position de l'élément
        pos = getIntent().getStringExtra("position");


        id = Integer.parseInt(pos);
        Log.d("DetailsActivity", String.valueOf(id));

        //On récupère le Character depuis la BDD dont l'id correspond à la position de l'élément qu'on veut afficher
        Character ch = viewModel.getCharacterById(id);
        //On set les textview avec les infos du Character
        tvFullname.setText(ch.getFullName());
        tvFamily.setText(ch.getFamily());
        tvTitle.setText(ch.getTitle());
        //Pour l'image on doit utiliser Glide
        Glide.with(ivImage.getContext()).load(ch.getImageUrl())
                .into(ivImage);


    }
}