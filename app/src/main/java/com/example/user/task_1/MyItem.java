package com.example.user.task_1;

import android.app.Activity;
import android.graphics.drawable.ShapeDrawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/2/2016.
 */

public class MyItem {
    String name;
    int imageId;
    static ArrayList<Integer> colors = new ArrayList<>();

    static {
        colors.add(R.drawable.circle_red);
        colors.add(R.drawable.circle_orange);
        colors.add(R.drawable.circle_yellow);
        colors.add(R.drawable.circle_green);
        colors.add(R.drawable.circle_lightblue);
        colors.add(R.drawable.circle_blue);
        colors.add(R.drawable.circle_purple);
        colors.add(R.drawable.empty_figure);
    }

    public MyItem(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }


    public static List<MyItem> initList() {
        List<MyItem> list = new ArrayList<>();

        int i = 1;
        while (i < 50){
            for (int j = 0; j < colors.size(); j++) {
                list.add(new MyItem("Item " + i, colors.get(j)));
                if (i < 50)
                    i++;
                else break;
            }
        }
        return list;
    }


}
