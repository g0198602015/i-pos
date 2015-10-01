package com.CenterFragment.TabFragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.CenterFragment.TabFragment.Goods.GoodsDetailActivity;
import com.CenterFragment.TabFragment.Goods.GoodsListViewAdapter;
import com.LeftFragement.ListViewData;

import java.util.ArrayList;
import java.util.HashMap;

import jerome.i_pos.R;
import model.BaseDataClass;

import static java.lang.Thread.sleep;


public class GoodsFragement extends BaseFragment implements AbsListView.OnItemClickListener
{

    private ProgressDialog mProgressDialog = null;
    private boolean mFinished = false;
    private int mRequestCode = 1;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment GoodsFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsFragement newInstance()
    {
        GoodsFragement fragment = new GoodsFragement();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("i-pos", getTitle()+"onCreate()............");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.i("i-pos", getTitle()+"onCreateView()............");
        View view = inflater.inflate(R.layout.goods_fragement, container, false);

        return view;

    }
    @Override
    public void onResume()
    {
        try
        {

            super.onResume();
            Log.i("i-pos", getTitle()+" "+"onResume()............");


            mFinished = false;
            //設定訊息內容後顯示於前景
            mProgressDialog = new ProgressDialog(this.getActivity());
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.setMessage("載入中，請稍後 ...");
            mProgressDialog.setCancelable(true);
            mProgressDialog.show();
            //建構執行緒
            new Thread(){
                @Override
                public void run()
                {
                    try
                    {

                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    finally
                    {
                        mFinished = true;
                    }
                }
            }.start(); //開始執行執行緒

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    while (true)
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (mFinished)
                        {
                            refreshListViewData();

                            handler.removeCallbacks(this);
                            mProgressDialog.dismiss();
                            break;
                        }
                    }
                }}, 500);

        }
        catch(Exception ex)
        {

        }
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
        Log.i("i-pos", getTitle() + " " + "onAttach()............");
        try {
            mCallback = (BaseFragmentEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("i-pos", getTitle() + " " + "onDetach()............");
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
    private ListViewData mListViewItems;
    private ListView mlistView;
    private GoodsListViewAdapter mGoodsListViewAdapter = null;
    public void refreshListViewData()
    {
        if (mListViewItems == null)
            mListViewItems = parseData();
        mCallback.onListViewDataChanged(mListViewItems);
        mGoodsListViewAdapter = new GoodsListViewAdapter(
                getActivity(),
                mListViewItems,
                getSearchText()
        );
        mGoodsListViewAdapter.notifyDataSetChanged();
        // Inflate the layout for this fragment
        try {

            if (getView() != null)
            {
                View listView = getView().findViewById(R.id.listView);
                if (listView != null)
                {
                    mlistView = (ListView) listView;
                    mlistView.setAdapter(mGoodsListViewAdapter);
                    mlistView.setOnItemClickListener(this);
                }
            }
        }
        catch (Exception ex)
        {
            String str = ex.toString();
            str = str;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //new一個intent物件，並指定Activity切換的class
        Intent intent = new Intent();
        intent.setClass(getActivity(), GoodsDetailActivity.class);

//        //new一個Bundle物件，並將要傳遞的資料傳入
        Bundle bundle = new Bundle();
        bundle.putInt("Type", 0);
        bundle.putSerializable("ListViewData", (ListViewData) mGoodsListViewAdapter.getItem(position));
        intent.putExtras(bundle);

//
//        //將Bundle物件assign給intent
//        intent.putExtras(bundle);
        //切換Activity
        startActivityForResult(intent, mRequestCode);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("i-pos", getTitle() + " " + "onDestory()............");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("i-pos", getTitle()+" "+"onPause()............");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("i-pos", getTitle()+" "+"onStart()............");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("i-pos", getTitle()+" "+"onStop()............");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }
    public ListViewData parseData()
    {
        ListViewData _RootItemData = new ListViewData("全部");
        _RootItemData.setParent(null);
        ListViewData goodItem = createViewData("商品", _RootItemData);
        ListViewData goodsChildItem = createViewData("商品的子類別", goodItem);
        createViewData("商品 1", goodsChildItem).setIconResourceID(R.drawable.hair_salon_goods).setInfo("商品描述");
        createViewData("商品 2", goodsChildItem).setIconResourceID(R.drawable.hair_salon_goods).setInfo("商品描述");
        ListViewData operationItem = createViewData("技術操作", _RootItemData);
        ListViewData operationChildItem = createViewData("促銷折扣", operationItem);
        createViewData("促銷折扣 1", operationChildItem).setIconResourceID(R.drawable.discount).setInfo("折扣描述");
        //睫毛
        operationChildItem = createViewData("睫毛", operationItem);
        createViewData("睫毛 1", operationChildItem).setIconResourceID(R.drawable.mascara2).setInfo("商品描述");
        createViewData("睫毛 2", operationChildItem).setIconResourceID(R.drawable.mascara1).setInfo("商品描述");
        createViewData("睫毛 3", operationChildItem).setIconResourceID(R.drawable.mascara2).setInfo("商品描述");
        createViewData("睫毛 4", operationChildItem).setIconResourceID(R.drawable.mascara1).setInfo("商品描述");

        //剪髮
        operationChildItem = createViewData("剪髮", operationItem);
        createViewData("剪髮 1", operationChildItem).setIconResourceID(R.drawable.scissors).setInfo("商品描述");
        createViewData("剪髮 2", operationChildItem).setIconResourceID(R.drawable.scissors2).setInfo("商品描述");
        createViewData("剪髮 3", operationChildItem).setIconResourceID(R.drawable.scissors).setInfo("商品描述");
        createViewData("剪髮 4", operationChildItem).setIconResourceID(R.drawable.scissors2).setInfo("商品描述");

        //燙髮
        operationChildItem = createViewData("燙髮", operationItem);
        createViewData("燙髮 1", operationChildItem).setIconResourceID(R.drawable.hairdresser).setInfo("商品描述");
        createViewData("燙髮 2", operationChildItem).setIconResourceID(R.drawable.hairdresser2).setInfo("商品描述");
        createViewData("燙髮 3", operationChildItem).setIconResourceID(R.drawable.hairdresser).setInfo("商品描述");
        createViewData("燙髮 4", operationChildItem).setIconResourceID(R.drawable.hairdresser2).setInfo("商品描述");

        //洗髮
        operationChildItem = createViewData("洗髮", operationItem);
        createViewData("洗髮 1", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");
        createViewData("洗髮 2", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");
        createViewData("洗髮 3", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");
        createViewData("洗髮 4", operationChildItem).setIconResourceID(R.drawable.shampoo).setInfo("商品描述");

        //染髮
        operationChildItem = createViewData("染髮", operationItem);
        createViewData("染髮 1", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        createViewData("染髮 2", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        createViewData("染髮 3", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        createViewData("染髮 4", operationChildItem).setIconResourceID(R.drawable.hairdresser7).setInfo("商品描述");
        return _RootItemData;
    }
    public ListViewData createViewData(String title,ListViewData parent)
    {
        ListViewData item = new ListViewData(title);
        if (parent != null) {
            item.setParent(parent);
            parent.addChild(item);
        }
        return item;
    }
}
