package com.elifnurtelase.stickerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class FavoriteStickersAdapter extends RecyclerView.Adapter<FavoriteStickerViewHolder> {
    ArrayList<FavoriteSticker> favoriteStickerList;
    StickersDB stickersDB;

    public FavoriteStickersAdapter(ArrayList<FavoriteSticker> favoriteStickerArrayList,StickersDB stickersDB) {
        this.favoriteStickerList = favoriteStickerArrayList;
        this.stickersDB = stickersDB;
    }

    @NonNull
    @Override
    public FavoriteStickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.favorite_sticker_image_item,parent,false);
        return new FavoriteStickerViewHolder(view).linkAdapter(this,stickersDB);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteStickerViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(StickerPackLoader.getStickerAssetUri(favoriteStickerList.get(position).getItem_pack_number(), favoriteStickerList.get(position).getItem_title()));
    }

    @Override
    public int getItemCount() {
        return favoriteStickerList.size();
    }


}


