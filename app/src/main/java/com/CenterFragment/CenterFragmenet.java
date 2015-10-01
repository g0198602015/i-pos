package com.CenterFragment;

/**
 * Created by Jerome on 2015/8/8.
 */

import android.app.Activity;
import android.app.SearchManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.CenterFragment.TabFragment.BaseFragment;
import com.CenterFragment.TabFragment.GoodsFragement;
import com.tools.ImageUtility;

import java.util.LinkedList;

import jerome.i_pos.MainActivity;
import jerome.i_pos.R;

/**
 * Created by Jerome on 2015/8/8.
 *
 */
public class CenterFragmenet extends BaseFragment
{
    private CenterFragmentSlidingTabLayout slidingTabLayout;
    private ViewPager pager;
    private FragmentPagerAdapter adapter;
    private String[] titles = {"打單", "進貨", "盤點", "紀錄"};
    private GoodsFragement mGoodsFragment = null;
    private ShippmentFragement mShippmentFragment = null;
    private BaseFragment mBaseFragment = null;

    public static CenterFragmenet newInstance()
    {
        CenterFragmenet f = new CenterFragmenet();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View view =  inflater.inflate(R.layout.center_fragement, container, false);
       InitToolbar(view);
       setHasOptionsMenu(true);
       int type = getArguments().getInt("Type", -1);
       //adapter
       final LinkedList<BaseFragment> fragments = createFragments(type);
       adapter = new TabFragmentPagerAdapter(getFragmentManager(), fragments);
       //pager
       pager = (ViewPager) view.findViewById(R.id.pager);
       pager.setAdapter(adapter);
       pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

           @Override
           public void onPageSelected(int position) {

               Toolbar toolbar = (Toolbar) ((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
               TextView titleTextView = (TextView) toolbar.findViewById(R.id.toolbarTitle);
               titleTextView.setText(titles[position]);
               if (myPageChangeListener != null)
                   myPageChangeListener.onPageSelected(position);
           }

           @Override
           public void onPageScrolled(int arg0, float arg1, int arg2) {


           }

           @Override
           public void onPageScrollStateChanged(int position) {


           }
       });

       //tabs
       slidingTabLayout = (CenterFragmentSlidingTabLayout) view.findViewById(R.id.tabs);
       slidingTabLayout.setViewPager(pager);
       return view;
    }

    private void InitToolbar(View view)
    {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).showLeft();
                // popBackStackToTop(mHostingActivity);
            }
        });
        TextView titleTextView = (TextView)toolbar.findViewById(R.id.toolbarTitle);
        titleTextView.setText(titles[0]);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
       // ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        ((AppCompatActivity) getActivity()).getMenuInflater().inflate(R.menu.toolbar_menu, menu);
//        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) ((AppCompatActivity) getActivity()).getSystemService(((AppCompatActivity) getActivity()).SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(((AppCompatActivity) getActivity()).getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                updateFragmentData(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    //
    // type:0 建立[打單Fragment]
    // type:1 建立[進貨Fragment]
    private LinkedList<BaseFragment> createFragments(int type)
    {
        int indicatorColor = Color.BLUE;
        int dividerColor = Color.TRANSPARENT;
        Bitmap bitmap = ImageUtility.createBitmap(getActivity(), R.mipmap.ic_launcher, 50, 50);
        LinkedList<BaseFragment> fragments = new LinkedList<BaseFragment>();
        if (type == 0) {
            mGoodsFragment = GoodsFragement.newInstance();
            mBaseFragment = mGoodsFragment;
            //fragments.add(mGoodsFragment);
        }
        else if (type ==1 )
        {
            mShippmentFragment = ShippmentFragement.newInstance();
            mBaseFragment = mShippmentFragment;
        }
        fragments.add(mBaseFragment);
//        fragments.add(GoodsFragement.newInstance("進貨", indicatorColor, dividerColor, ImageUtility.createBitmap(getActivity(), R.drawable.shipment, 50, 50)));
//        fragments.add(GoodsFragement.newInstance("盤點", indicatorColor, dividerColor, ImageUtility.createBitmap(getActivity(), R.drawable.inventory, 50, 50)));
//        fragments.add(RecordFragement.newInstance("消費紀錄", Color.BLUE, dividerColor, ImageUtility.createBitmap(getActivity(), R.drawable.record, 50, 50)));
//        fragments.add(ContentFragment.newInstance("Look", Color.CYAN, dividerColor, bitmap));
//        fragments.add(ContentFragment.newInstance("Wood", Color.MAGENTA, dividerColor, bitmap));
        return fragments;
    }
    public void updateFragmentData(String searchText)
    {
        if (mBaseFragment != null) {
            mBaseFragment.setSearchText(searchText);
            mBaseFragment.refreshListViewData();
        }
    }
    public boolean isFirst()
    {
        if (pager.getCurrentItem() == 0)
            return true;
        else
            return false;
    }

    public boolean isEnd()
    {
        if (pager.getCurrentItem() == adapter.getCount() - 1)
        {

            //Toolbar toolbar = (Toolbar) getView().findViewById(R.id.tool_bar);
            //((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            return true;
        }
        else
            return false;
    }
    private MyPageChangeListener myPageChangeListener;

    public void setMyPageChangeListener(MyPageChangeListener l) {

        myPageChangeListener = l;

    }

    @Override
    public void refreshListViewData() {

    }

    public interface MyPageChangeListener {
        public void onPageSelected(int position);
    }
}