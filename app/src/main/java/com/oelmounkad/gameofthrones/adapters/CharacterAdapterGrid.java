package com.oelmounkad.gameofthrones.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oelmounkad.gameofthrones.databinding.ListItemCharGridBinding;

import com.oelmounkad.gameofthrones.model.Character;
import com.oelmounkad.gameofthrones.ui.DetailsActivity;

import java.util.ArrayList;

public class CharacterAdapterGrid extends RecyclerView.Adapter<CharacterAdapterGrid.CharacterGridViewHolder> {
    private Context mContext;
    private ArrayList<Character> mList;
    private ListItemCharGridBinding binding;

    public CharacterAdapterGrid(Context mContext, ArrayList<Character> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CharacterGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        binding = ListItemCharGridBinding.inflate(inflater,parent,false);
        return new CharacterGridViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterGridViewHolder holder, int position) {
        holder.itemBinding.characterName.setText(mList.get(position).getFirstName());
        Glide.with(mContext).load(mList.get(position).getImageUrl())
                .into(holder.itemBinding.characterImage);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class CharacterGridViewHolder extends RecyclerView.ViewHolder{
        private ListItemCharGridBinding itemBinding;

        public CharacterGridViewHolder(ListItemCharGridBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            itemBinding.recyclerItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String s = Integer.toString(position);
                    Log.d("RecyclerView", "onClick#################################：" + s);

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
