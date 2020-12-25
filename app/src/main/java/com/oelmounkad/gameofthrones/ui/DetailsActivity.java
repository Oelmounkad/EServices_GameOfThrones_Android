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

        pos = getIntent().getStringExtra("position");
        //tvFirstName.setText(pos);

        id = Integer.parseInt(pos);
        Log.d("DetailsActivity", String.valueOf(id));
        Character ch = viewModel.getCharacterById(id);

        tvFullname.setText(ch.getFullName());
        tvFamily.setText(ch.getFamily());
        tvTitle.setText(ch.getTitle());

        Glide.with(ivImage.getContext()).load(ch.getImageUrl())
                .into(ivImage);


    }
}