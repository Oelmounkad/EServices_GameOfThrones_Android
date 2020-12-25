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
    private boolean isFavoriteListVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                .commit();

        binding.changeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isFavoriteListVisible){
                  isFavoriteListVisible = false;
                  binding.changeFragment.setText("Grid View");
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new Home())
                          .commit();
                }
                else {
                    isFavoriteListVisible = true;
                    binding.changeFragment.setText("List View");
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new GridList())
                            .commit();
                }
            }
        });
    }
}
