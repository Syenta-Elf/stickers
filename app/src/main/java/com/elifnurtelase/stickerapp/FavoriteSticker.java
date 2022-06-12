package com.elifnurtelase.stickerapp;

public class FavoriteSticker {

        private String item_title;
        private String key_id;
        private String item_pack_number;
        private String favStatus;

        public FavoriteSticker(String item_title, String key_id, String item_pack_number,String favStatus) {
            this.item_title = item_title;
            this.key_id = key_id;
            this.item_pack_number = item_pack_number;
            this.favStatus = favStatus;
        }

        public String getItem_title() {
            return item_title;
        }

        public void setItem_title(String item_title) {
            this.item_title = item_title;
        }

        public String getKey_id() {
            return key_id;
        }

        public void setKey_id(String key_id) {
            this.key_id = key_id;
        }

        public String getItem_pack_number() {
            return item_pack_number;
        }

        public void setItem_pack_number(int item_image) {
            this.item_pack_number = item_pack_number;
        }

    public void setItem_pack_number(String item_pack_number) {
        this.item_pack_number = item_pack_number;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}

