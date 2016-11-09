package com.example.user.task_1;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/2/2016.
 */

public class ColorListItem implements Serializable{
    private int id;
    private String name;
    int imageId;
    Drawable image;

    static final ArrayList<Integer> colors = new ArrayList<>();

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    static {
        if (colors.isEmpty()) {
            colors.add(R.color.red);
            colors.add(R.color.orange);
            colors.add(R.color.yellow);
            colors.add(R.color.green);
            colors.add(R.color.light_blue);
            colors.add(R.color.blue);
            colors.add(R.color.purple);
            colors.add(android.R.color.transparent);
        }
    }


    public ColorListItem(int id, String name, Drawable image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public static List<ColorListItem> initList() {
        List<ColorListItem> list = new ArrayList<>();


        int i = 1;
        while (i < 50){
            for (int j = 0; j < colors.size(); j++) {
                Drawable image = MainActivity.getContext().getResources().getDrawable(R.drawable.color_circle);
                image.clearColorFilter();
                image.setColorFilter(MainActivity.getContext().getResources().getColor(colors.get(j)), PorterDuff.Mode.SRC);
                list.add(new ColorListItem(i, MainActivity.getContext().getResources().getString(R.string.item) + i, image));
                if (i < 50)
                    i++;
                else break;
            }
        }
        return list;
    }

}
