package com.oelmounkad.gameofthrones.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oelmounkad.gameofthrones.databinding.ListItemCharBinding;
import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.ui.DetailsActivity;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private Context mContext;
    private ArrayList<Character> mList;
    private ListItemCharBinding binding;

    public CharacterAdapter(Context mContext, ArrayList<Character> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = ListItemCharBinding.inflate(inflater,parent,false);
        return new CharacterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.itemBinding.characterName.setText(mList.get(position).getFullName());
        Glide.with(mContext).load(mList.get(position).getImageUrl())
                .into(holder.itemBinding.characterImage);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder{
        private ListItemCharBinding itemBinding;

        public CharacterViewHolder(ListItemCharBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.recyclerItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String s = Integer.toString(position);
                    Log.d("RecyclerView", "POSITION#################################：" + s);

                    Intent intent = new Intent(mContext, DetailsActivity.class);
                    intent.putExtra("position",s);
                    mContext.startActivity(intent);
                }
            });
        }

    }

    public  void updateList(ArrayList<Character> updatedList){
        mList = updatedList;
        notifyDataSetChanged();
    }

    public  Character getCharacterAt(int position){
        return mList.get(position);
    }
}
