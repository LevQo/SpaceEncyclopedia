package com.levqo.spaceencyclopedia;

import android.view.Display;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03.05.2018.
 */

class ModelItem {
    private int imgId;

    public ModelItem(int imgId){
        this.imgId = imgId;
    }

    public int getImgId() {
        return imgId;
    }

    public static List<ModelItem> getFakeItems(){
        ArrayList<ModelItem> itemList = new ArrayList<>();
        itemList.add(new ModelItem(R.drawable.sun));
        itemList.add(new ModelItem(R.drawable.mercury));
        itemList.add(new ModelItem(R.drawable.venus));
        itemList.add(new ModelItem(R.drawable.earth));
        itemList.add(new ModelItem(R.drawable.mars));
        itemList.add(new ModelItem(R.drawable.jupiter));
        itemList.add(new ModelItem(R.drawable.saturn));
        itemList.add(new ModelItem(R.drawable.uranus));
        itemList.add(new ModelItem(R.drawable.neptune));

        return itemList;

    }
}
