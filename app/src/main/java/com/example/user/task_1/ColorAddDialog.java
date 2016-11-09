package com.example.user.task_1;

import android.app.DialogFragment;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by User on 11/8/2016.
 */

public class ColorAddDialog extends DialogFragment {
    
    static final int[] colors = {R.drawable.circle_red, R.drawable.circle_orange,
            R.drawable.circle_yellow, R.drawable.circle_green, R.drawable.circle_lightblue,
    R.drawable.circle_blue, R.drawable.circle_purple};

    private static final Map<Integer, Integer> colorMap;
    private static final Map<Integer, Integer> drawableMap;

    Drawable circle;

    static {
        colorMap = new HashMap<>();
        colorMap.put(R.id.red_btn, R.color.red);
        colorMap.put(R.id.orange_btn, R.color.orange);
        colorMap.put(R.id.yellow_btn, R.color.yellow);
        colorMap.put(R.id.green_btn, R.color.green);
        colorMap.put(R.id.light_blue_btn, R.color.light_blue);
        colorMap.put(R.id.blue_btn, R.color.blue);
        colorMap.put(R.id.purple_btn, R.color.purple);

        drawableMap = new HashMap<>();
        drawableMap.put(R.id.red_btn, R.drawable.circle_red);
        drawableMap.put(R.id.orange_btn, R.drawable.circle_orange);
        drawableMap.put(R.id.yellow_btn, R.drawable.circle_yellow);
        drawableMap.put(R.id.green_btn, R.drawable.circle_green);
        drawableMap.put(R.id.light_blue_btn, R.drawable.circle_lightblue);
        drawableMap.put(R.id.blue_btn, R.drawable.circle_blue);
        drawableMap.put(R.id.purple_btn, R.drawable.circle_purple);

    }

    private int chosenColor = R.drawable.circle_red;
    private ListAdapter adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.d_add_color, null);

        adapter = (ListAdapter) this.getArguments().getSerializable("adapter");

        final TextView title = (TextView) view.findViewById(R.id.dialog_title);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        final EditText nameField = (EditText) view.findViewById(R.id.input_name);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        radioGroup.check(R.id.red_btn);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                title.setBackgroundColor(getResources().getColor(colorMap.get(i)));
                circle = MainActivity.getContext().getResources().getDrawable(R.drawable.color_circle);
                circle.setColorFilter(getResources().getColor(colorMap.get(i)), PorterDuff.Mode.SRC); //change!

                chosenColor = drawableMap.get(i);
            }
        });

        Button cancelBtn = (Button) view.findViewById(R.id.dialog_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().cancel();
            }
        });

        Button confirmBtn = (Button) view.findViewById(R.id.dialog_add);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = nameField.getText().toString();
                if (itemName.equals("")){
                    Snackbar.make(view, R.string.snackbar_enter_msg, Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (adapter.isNameContained(itemName))
                    Snackbar.make(view, R.string.name_exist, Snackbar.LENGTH_SHORT).show();
                else {
                    adapter.addItemToView(itemName, circle);
                    getDialog().dismiss();
                }
            }
        });

        return view;
    }
}
