package com.oelmounkad.gameofthrones.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.oelmounkad.gameofthrones.R;
import com.oelmounkad.gameofthrones.databinding.ActivityMainBinding;
import com.oelmounkad.gameofthrones.ui.fragments.GridList;
import com.oelmounkad.gameofthrones.ui.fragments.Home;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isGridListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                .commit();

        binding.changeFragment.setOnClickListener(new View.OnClickListener() {

            //Quand on clique sur le bouton qui permet de changer le fragment qui s'affiche
            @Override
            public void onClick(View view) {
                // si on est sur le fragment Grid List on change vers le fragment Home
                if(isGridListVisible){
                  isGridListVisible = false;
                  // on change le test du button vers Grid View
                  binding.changeFragment.setText("Grid View");
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                          .commit();
                }
                // si on est sur le fragment Home on change vers le fragment Grid List
                else {
                    isGridListVisible = true;
                    // on change le test du button vers List View
                    binding.changeFragment.setText("List View");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new GridList())
                            .commit();
                }
            }
        });
    }
}
