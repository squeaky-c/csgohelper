package com.spinfused.csgohelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class InventoryItemFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private InventoryItemRecyclerViewAdapter.OnClickListener listener;
    private InventoryItemRecyclerViewAdapter adapter;
    private JsonController controller;
    private View view;
    private RecyclerView recyclerView;
    private TextView mTextView;
    private OnListFragmentInteractionListener mListener;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public InventoryItemFragment() { //Required empty constructor
    }

    @SuppressWarnings("unused")
    public static InventoryItemFragment newInstance(int columnCount) {
        InventoryItemFragment fragment = new InventoryItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new InventoryItemRecyclerViewAdapter(new ArrayList<InventoryItem>());
        adapter.setListener(listener);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_inventoryitem_list, container, false);
        Context context = view.getContext();
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.inventoryList);
        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        mTextView = (TextView) view.findViewById(R.id.inventoryHelp);
        mTextView.setText(getString(R.string.tab_inventory_idle));

        controller = new JsonController(
                new JsonController.OnResponseListener() {
                    @Override
                    public void onSuccess(List<InventoryItem> inventory) {
                        if(inventory.size() > 0) {
                            mTextView.setText(getString(R.string.tab_inventory_loading));
                            Log.d("InventoryItemFragment","Got info from Steam JSON.");
                            recyclerView.setVisibility(View.VISIBLE);
                            recyclerView.invalidate();
                            adapter.updateDataSet(inventory);
                            recyclerView.setAdapter(new InventoryItemRecyclerViewAdapter(InventoryItemRecyclerViewAdapter.inventory));
                            mTextView.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        mTextView.setText(getString(R.string.tab_inventory_failed));
                        Log.d("InventoryItemFragment","Failed to fetch info from Steam JSON.");
                    }
                });

        controller.sendRequest("76561197962695731");
        Log.d("Inventory","Request sent");

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItems();
            }
        });

        Log.d("InventoryItemFragment","Am I displaying yet?");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.sendRequest("76561197962695731");
        recyclerView.invalidate();
        adapter.notifyDataSetChanged();
        view.invalidate();
    }

    public void refreshItems() {
        //Sushi: 76561198041374792
        //Starworshipper: 76561197987633944

        controller.sendRequest("76561198041374792");
        recyclerView.setVisibility(View.INVISIBLE);
        recyclerView.invalidate();
        view.invalidate();
        recyclerView.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setRefreshing(false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(InventoryItem item);

    }
}
