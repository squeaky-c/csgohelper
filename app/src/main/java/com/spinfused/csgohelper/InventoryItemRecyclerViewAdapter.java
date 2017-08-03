package com.spinfused.csgohelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import com.spinfused.csgohelper.InventoryItemFragment.OnListFragmentInteractionListener;

import java.util.List;

public class InventoryItemRecyclerViewAdapter extends RecyclerView.Adapter<InventoryItemRecyclerViewAdapter.ViewHolder> {

    public static List<InventoryItem> inventory;
    public static OnClickListener listener;

    public InventoryItemRecyclerViewAdapter(List<InventoryItem> inventory) {
        this.inventory = inventory;
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
        InventoryItem item = inventory.get(position);

        holder.displayName.setText(inventory.get(position).itemName);
        holder.description.setText(inventory.get(position).itemDescription);
        holder.setIcon(item.getItemIcon());

        if(listener!=null) {
            holder.bindClickListener(listener, item);
        }
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void updateDataSet(List<InventoryItem> modelList) {
        this.inventory.clear();
        this.inventory.addAll(modelList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return inventory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        private TextView displayName;
        private TextView description;
        private NetworkImageView icon;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.displayName = (TextView) view.findViewById(R.id.id);
            this.description = (TextView) view.findViewById(R.id.content);
            this.icon = (NetworkImageView) view.findViewById(R.id.icon);
        }

        void setIcon(String iconUrl) {
            ImageLoader imageLoader = VolleySingleton.getInstance(App.getContext()).getImageLoader();
            this.icon.setImageUrl(iconUrl, imageLoader);
        }

        void bindClickListener(final OnClickListener listener, final InventoryItem item){
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onNameClick(item);
                }
            });

            icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onIconClick(item);
                }
            });
        }
    }
}
