package com.elifnurtelase.stickerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {
    public static final String EXTRA_STICKER_PACK_LIST_DATA = "sticker_pack_list";
    private ArrayList<StickerPack> stickerPackList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        stickerPackList = getIntent().getParcelableArrayListExtra(EXTRA_STICKER_PACK_LIST_DATA);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent intent = new Intent(WelcomeActivity.this, StickerPackListActivity.class);
                intent.putParcelableArrayListExtra(WelcomeActivity.EXTRA_STICKER_PACK_LIST_DATA, stickerPackList);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            }
        },3000);


    }
}