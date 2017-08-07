package com.spinfused.csgohelper;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
    public InventoryItemRecyclerViewAdapter adapter;
    private JsonController controller;
    View view;
    RecyclerView recyclerView;
    private OnListFragmentInteractionListener mListener;

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
        recyclerView = (RecyclerView) view;
        controller = new JsonController(
                new JsonController.OnResponseListener() {
                    @Override
                    public void onSuccess(List<InventoryItem> inventory) {
                        if(inventory.size() > 0) {
                            Log.d("InventoryItemFragment","Got info from Steam JSON.");
                            adapter.updateDataSet(inventory);
                            recyclerView.invalidate();
                        }
                    }

                    @Override
                    public void onFailure(String errorMessage) {
                        Log.d("InventoryItemFragment","Failed to fetch info from Steam JSON.");
                    }
                });
        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

        recyclerView.setAdapter(new InventoryItemRecyclerViewAdapter(InventoryItemRecyclerViewAdapter.inventory));



        controller.sendRequest("Spinfusr");
        Log.d("Inventory","Request sent");

        Log.d("InventoryItemFragment","Am I displaying yet?");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        controller.sendRequest("spin");
        recyclerView.invalidate();
        view.invalidate();
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
