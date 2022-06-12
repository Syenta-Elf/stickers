package com.elifnurtelase.stickerapp;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;

import java.util.ArrayList;

public class FavoriteStickersActivity extends AddStickerPackActivity {
    private StickersDB stickersDB;
    private ArrayList<FavoriteSticker> favoriteStickers = new ArrayList<>();
    private  RecyclerView recyclerView;
    private ImageButton remove_favorite_button;
    private GridLayoutManager layoutManager;
    private int numColumns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_stickers);


        stickersDB = new StickersDB(this);
        layoutManager = new GridLayoutManager(this,1);

        remove_favorite_button = findViewById(R.id.favorite_sticker_button);
        recyclerView = findViewById(R.id.favorite_sticker_list);
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(pageLayoutListener);
        recyclerView.setLayoutManager(layoutManager);
        loadData();
        stickersDB.close();
    }


    private void loadData() {
        if (favoriteStickers != null) {
            favoriteStickers.clear();
        }
        SQLiteDatabase db = stickersDB.getReadableDatabase();
        Cursor cursor = stickersDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(StickersDB.ITEM_TITLE));
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(StickersDB.KEY_ID));
                @SuppressLint("Range") String itemStickerPack = cursor.getString(cursor.getColumnIndex(StickersDB.ITEM_STICKER_PACK));
                @SuppressLint("Range") String favStatus = cursor.getString(cursor.getColumnIndex(StickersDB.FAVORITE_STATUS));

                FavoriteSticker favItem = new FavoriteSticker(title, id, itemStickerPack,favStatus);
                favoriteStickers.add(favItem);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }
        FavoriteStickersAdapter favoriteStickersAdapter = new FavoriteStickersAdapter(favoriteStickers,stickersDB);
        recyclerView.setAdapter(favoriteStickersAdapter);
    }



    private final ViewTreeObserver.OnGlobalLayoutListener pageLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            setNumColumns(recyclerView.getWidth() / recyclerView.getContext().getResources().getDimensionPixelSize(R.dimen.sticker_pack_details_image_size));
        }
    };
    private void setNumColumns(int numColumns) {
        if (this.numColumns != numColumns) {
            layoutManager.setSpanCount(numColumns);
            this.numColumns = numColumns;
        }
    }
}
