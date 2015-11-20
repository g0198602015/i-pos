package com.CenterFragment.TabFragment.Goods;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.CenterFragment.BaseFragment;
import com.LeftFragement.BaseItemData;

import i_so.pos.R;
import model.ActivityRequestCodeConstant;
import model.BundleConstant;
import model.GoodsCartRecordData;
import model.GoodsItemAllData;
import model.GoodsItemData;

import static java.lang.Thread.sleep;


public class GoodsFragement extends BaseFragment implements AbsListView.OnItemClickListener
{

//    private ProgressDialog mProgressDialog = null;
    private boolean mFinished = false;
    private AnimationSet animationSet;
//    private int mRequestCode = 1;

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
    private View mView = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        Log.i("i-pos", getTitle()+"onCreateView()............");
        mView = inflater.inflate(R.layout.goods_fragement, container, false);

        return mView;

    }
    @Override
    public void onResume()
    {
        try
        {

            super.onResume();
            Log.i("i-pos", getTitle()+" "+"onResume()............");

            if (GoodsItemAllData.getChildSize() == 0)
            {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        while(Util.WebServiceAPI.mBGettingProducts == true) {
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        refreshListViewData();
                    }
                });
            }
            else
                refreshListViewData();

        }
        catch(Exception ex)
        {

        }
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

    private BaseItemData mListViewItems;
    private ListView mlistView;
    private GoodsListViewAdapter mGoodsListViewAdapter = null;

    public void refreshListViewData()
    {
        if (mListViewItems == null)
        {
            mListViewItems =  GoodsItemAllData.getGoodsItemRecordsData();
//            mListViewItems =parseData();
        }
        mCallback.onListViewDataChanged(mListViewItems);
        mGoodsListViewAdapter = new GoodsListViewAdapter(
                getActivity(),
                mListViewItems,
                getSearchText(),
                getBarcode()
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
//                    if (mGoodsListViewAdapter.getCount() == 1)
//                    {
//                        GoodsItemData goodsItemData = (GoodsItemData) mGoodsListViewAdapter.getItem(0);
//                        if (goodsItemData.getBarcode().equalsIgnoreCase(getBarcode()) && getBarcode().length() > 0)
//                        {
//                            ShowGoodsDetailActivity(goodsItemData);
//                        }
//                    }
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        GoodsItemData goodsItemData = (GoodsItemData) mGoodsListViewAdapter.getItem(position);
        if (GoodsCartRecordData.contains(goodsItemData.getSerialIndex()) != null)
        {

            GoodsCartRecordData.addGoodsItem(goodsItemData, false);
            //final ImageView goodsIconImageView = (ImageView)view.findViewById(R.id.goods_detail_goods_icon);
            int width = mView.getWidth();
            int[] goodsImageViewlocation = new int[2];
            goodsImageViewlocation[0] = 0 ;
            goodsImageViewlocation[1] = 0;
            view.getLocationOnScreen(goodsImageViewlocation);
            int delX = width - goodsImageViewlocation[0];
            int delY = 0 - goodsImageViewlocation[1];
            int timeDuraction = 1000;
            final Animation am = new TranslateAnimation(0, delX, 0, delY);
            am.setDuration(timeDuraction);
            am.setRepeatCount(0);
            animationSet = new AnimationSet(true);
            animationSet.addAnimation(am);
            AlphaAnimation alpha=new AlphaAnimation(1,0);
            alpha.setDuration(timeDuraction);
            alpha.setFillAfter(true);
            animationSet.addAnimation(alpha);
            view.startAnimation(animationSet);

        }
        else {
            ShowGoodsDetailActivity(goodsItemData);
        }
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
        Log.i("i-pos", getTitle() + " " + "onStart()............");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("i-pos", getTitle() + " " + "onStop()............");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ShowGoodsDetailActivity(GoodsItemData goodsItemData) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), GoodsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(BundleConstant.TYPE, 0);
        bundle.putSerializable("ListViewData", goodsItemData);
        intent.putExtras(bundle);
        startActivityForResult(intent, ActivityRequestCodeConstant.GOODS_FRAGMENT);
    }
  }
