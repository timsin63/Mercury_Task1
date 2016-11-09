package com.example.user.task_1;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    List<ColorListItem> list;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.a_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        if (savedInstanceState == null) {
            list = ColorListItem.initList();
        } else {
            list = (ArrayList<ColorListItem>) savedInstanceState.getSerializable("list");
        }

        final ListAdapter adapter = new ListAdapter(list, MainActivity.this);
        recyclerView.setAdapter(adapter);

       final FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialog = new ColorAddDialog();
                Bundle args = new Bundle();
                args.putSerializable("adapter", adapter);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(), "dialog");
            }
        });


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) floatingActionButton.getLayoutParams();
                    int fab_bottomMargin = layoutParams.bottomMargin;
                    floatingActionButton.animate().translationY(floatingActionButton.getHeight() + fab_bottomMargin).setInterpolator(new LinearInterpolator()).start();
                }
                if (dy < 0){
                    floatingActionButton.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
                }
            }
        });
    }

    public static Context getContext(){
        return context;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putSerializable("list", (Serializable) list);
        super.onSaveInstanceState(outState);
    }

}
