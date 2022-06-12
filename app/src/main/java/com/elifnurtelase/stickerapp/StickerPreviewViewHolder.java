/*
 * Copyright (c) WhatsApp Inc. and its affiliates.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.elifnurtelase.stickerapp;

import android.view.View;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

class StickerPreviewViewHolder extends RecyclerView.ViewHolder {

    final SimpleDraweeView stickerPreviewView;
    final ImageButton imageButton;
    private StickersDB stickersDB;
    private StickerPreviewAdapter adapter;

    StickerPreviewViewHolder(final View itemView) {
        super(itemView);
        stickerPreviewView = itemView.findViewById(R.id.sticker_preview);
        imageButton = itemView.findViewById(R.id.not_favorite_sticker);

        //Database'e kaydetmek için + anlık ekleyip eklemediğini gösteriyor.
        itemView.findViewById(R.id.not_favorite_sticker).setOnClickListener(v ->
        {
            FavoriteSticker sticker = adapter.allStickers.get(getLayoutPosition());
            if(sticker.getFavStatus().equals("0")){
                sticker.setFavStatus("1");
                imageButton.setImageResource(R.drawable.heart_fill);
                stickersDB.add_fav(sticker.getKey_id());
            }else if(sticker.getFavStatus().equals("1")){
                sticker.setFavStatus("0");
                imageButton.setImageResource(R.drawable.heart);
                stickersDB.remove_fav((sticker.getKey_id()));
            }
        });
    }

    public StickerPreviewViewHolder linkAdapter(StickerPreviewAdapter adapter,StickersDB stickersDB){
        this.adapter = adapter;
        this.stickersDB = stickersDB;
        return this;
    }
}