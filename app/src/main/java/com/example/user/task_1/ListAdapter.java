package com.example.user.task_1;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by User on 11/2/2016.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> implements Serializable {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        ImageView circle;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemTextView);
            circle = (ImageView) itemView.findViewById(R.id.itemImageView);
        }
    }

    List<ColorListItem> list;
    Context context;

    public ListAdapter(List<ColorListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_color_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemName.setText(list.get(position).getName());
        //holder.circle.setImageResource(list.get(position).imageId);
        holder.circle.setImageDrawable(list.get(position).image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Snackbar.make(view, MainActivity.getContext().getResources().getString(R.string.clicked) + (list.get(position).getId()), Snackbar.LENGTH_SHORT).show();
            }
        });


        //TODO почитать про adapterView


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        showDialogForDeleting(context, position);
                        return false;
                    }
                });
                popupMenu.show();

                return false;
            }
        });
    }

    private void showDialogForDeleting(Context context, final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.deleting);
        builder.setMessage(R.string.sure);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    public boolean isNameContained(String name){
        for (ColorListItem item : list){
            if (item.getName().equals(name))
                return true;
        }
        return false;
    }

    public void addItemToView(String name, Drawable circle){
        int id = list.get(list.size() - 1).getId() + 1;
        list.add(new ColorListItem(id, name, circle));
        notifyItemInserted(list.size());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
