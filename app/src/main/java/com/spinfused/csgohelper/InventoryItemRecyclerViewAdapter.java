package com.spinfused.csgohelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class InventoryItemRecyclerViewAdapter extends RecyclerView.Adapter<InventoryItemRecyclerViewAdapter.ViewHolder> {

    private static OnClickListener sListener;
    static List<InventoryItem> sInventory;

    public InventoryItemRecyclerViewAdapter(List<InventoryItem> inventory) {
        this.sInventory = inventory;
    }

    public interface OnClickListener {
        void onNameClick(InventoryItem item);
        void onIconClick(InventoryItem item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inventoryitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        InventoryItem item = sInventory.get(position);

        holder.mName.setText(item.getName());
        holder.mDescription.setText(item.getDescription());
        holder.setIcon(item.getIcon());

        if(sListener!=null) {
            holder.bindClickListener(sListener, item);
        }
    }

    public void setListener(OnClickListener listener) {
        this.sListener = listener;
    }

    public void updateDataSet(List<InventoryItem> modelList) {
        this.sInventory.clear();
        this.sInventory.addAll(modelList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return sInventory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private TextView mName;
        private TextView mDescription;
        private NetworkImageView mIconUrl;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.mName = (TextView) view.findViewById(R.id.id);
            this.mDescription = (TextView) view.findViewById(R.id.content);
            this.mIconUrl = (NetworkImageView) view.findViewById(R.id.icon);
        }

        void setIcon(String iconUrl) {
            ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
            this.mIconUrl.setImageUrl(iconUrl, imageLoader);

        }

        void bindClickListener(final OnClickListener listener, final InventoryItem item){
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNameClick(item);
                    Toast.makeText(view.getContext(), "Hello", Toast.LENGTH_SHORT).show();
                }
            });

            mIconUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onIconClick(item);
                }
            });
        }
    }
}
