package com.elifnurtelase.stickerapp;


import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

public class FavoriteStickerViewHolder extends RecyclerView.ViewHolder{
    final SimpleDraweeView simpleDraweeView;
    private FavoriteStickersAdapter adapter;
    StickersDB stickersDB;


    public FavoriteStickerViewHolder(View itemView) {
        super(itemView);
        simpleDraweeView = itemView.findViewById(R.id.favorite_sticker_preview);
        itemView.findViewById(R.id.favorite_sticker_button).
                setOnClickListener(v ->{
                    stickersDB.remove_fav(adapter.favoriteStickerList.get(getLayoutPosition()).getKey_id());
                    adapter.favoriteStickerList.remove(getLayoutPosition());
                    adapter.notifyItemRemoved(getLayoutPosition());
                } );
    }
    public FavoriteStickerViewHolder linkAdapter(FavoriteStickersAdapter adapter,StickersDB stickersDB){
        this.adapter=adapter;
        this.stickersDB=stickersDB;
        return this;
    }
}
