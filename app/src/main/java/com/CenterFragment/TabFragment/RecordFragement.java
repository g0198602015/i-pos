package com.CenterFragment.TabFragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

//import com.CenterFragment.TabFragment.Goods.GoodsListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import jerome.i_pos.R;


public class RecordFragement extends BaseFragment implements AbsListView.OnItemClickListener
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   // private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GoodsFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragement newInstance(CharSequence title, int indicatorColor,
                                              int dividerColor, Bitmap icon)
    {
        RecordFragement fragment = new RecordFragement();
        fragment.setTitle(String.valueOf(title));
//        fragment.setmIndicatorColor(indicatorColor);
//        fragment.setmDividerColor(dividerColor);
//        fragment.setIcon(icon);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.record_fragment, container, false);
//        // Inflate the layout for this fragment
//        try {
//            ArrayList<HashMap<String, Object>> Item = new ArrayList<HashMap<String, Object>>();
//            for (int i = 0; i < 20; i++) {
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put("ItemImage", R.mipmap.ic_launcher);
//                map.put("ItemName", "Name");
//                map.put("ItemInfo", "Info");
//                map.put("ItemButton", R.mipmap.ic_launcher);
//                Item.add(map);
//            }
//
//            mGoodsListViewAdapter = new GoodsListViewAdapter(
//                    this.getActivity(),
//                    Item
//            );
//            if (view != null)
//            {
//                View listView = view.findViewById(R.id.listView);
//                if (listView != null)
//                {
//                    list = (ListView) listView;
//                    list.setAdapter(mGoodsListViewAdapter);
//                    list.setOnItemClickListener(this);
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            String str = ex.toString();
//            str = str;
//        }
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
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
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
//    }
    private ListView list;
//    private GoodsListViewAdapter mGoodsListViewAdapter = null;
//    protected void refreshListViewData()
//    {
//        try {
//            ArrayList<HashMap<String, Object>> Item = new ArrayList<HashMap<String, Object>>();
//            for (int i = 0; i < 20; i++) {
//                HashMap<String, Object> map = new HashMap<String, Object>();
//                map.put("ItemImage", R.mipmap.ic_launcher);
//                map.put("ItemName", "Name");
//                map.put("ItemInfo", "Info");
//                map.put("ItemButton", R.mipmap.ic_launcher);
//                Item.add(map);
//            }
//
//            mGoodsListViewAdapter = new GoodsListViewAdapter(
//                    this.getActivity(),
//                    Item,
//                    new String[]{"ItemImage", "ItemName", "ItemInfo", "ItemButton"},
//                    new int[]{R.id.ItemImage, R.id.ItemName, R.id.ItemInfo, R.id.ItemButton}
//            );
//            if (getView() != null)
//            {
//                View listView = getView().findViewById(R.id.listView);
//                if (listView != null) {
//                    list = (ListView) listView;
//                    list.setAdapter(mGoodsListViewAdapter);
//                }
//            }
//        }
//        catch (Exception ex)
//        {
//            String str = ex.toString();
//            str = str;
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void refreshListViewData() {

    }
}
