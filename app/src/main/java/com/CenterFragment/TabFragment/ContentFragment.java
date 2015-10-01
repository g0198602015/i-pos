/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.CenterFragment.TabFragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jerome.i_pos.R;

/**
 * Simple Fragment used to display some meaningful content for each page in the sample's
 * {@link android.support.v4.view.ViewPager}.
 */
public class ContentFragment extends BaseFragment {

    private static final String KEY_TITLE = "title";
    private static final String KEY_INDICATOR_COLOR = "indicator_color";
    private static final String KEY_DIVIDER_COLOR = "divider_color";

    /**
     * @return a new instance of {@link ContentFragment}, adding the parameters into a bundle and
     * setting them as arguments.
     */
    public static ContentFragment newInstance(CharSequence title, int indicatorColor,
            int dividerColor, Bitmap icon) {
//        Bundle bundle = new Bundle();
//        bundle.putCharSequence(KEY_TITLE, title);
//        bundle.putInt(KEY_INDICATOR_COLOR, indicatorColor);
//        bundle.putInt(KEY_DIVIDER_COLOR, dividerColor);
//        ContentFragment fragment = new ContentFragment();
        //fragment.setArguments(bundle);

        ContentFragment fragment = new ContentFragment();
        fragment.setTitle(String.valueOf(title));
//        fragment.setmIndicatorColor(indicatorColor);
//        fragment.setmDividerColor(dividerColor);
//        fragment.setIcon(icon);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.content_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Bundle args = getArguments();
//
//        if (args != null)
//        {
//            //Title
//            TextView title = (TextView) view.findViewById(R.id.item_title);
//            title.setText("Title: " + args.getCharSequence(KEY_TITLE));
//            setTitle(String.valueOf(args.getCharSequence(KEY_TITLE)));
//            int indicatorColor = args.getInt(KEY_INDICATOR_COLOR);
//            //Indicator Color
//            TextView indicatorColorView = (TextView) view.findViewById(R.id.item_indicator_color);
//            indicatorColorView.setText("Indicator: #" + Integer.toHexString(indicatorColor));
//            indicatorColorView.setTextColor(indicatorColor);
//            //Divider Color
//            int dividerColor = args.getInt(KEY_DIVIDER_COLOR);
//            TextView dividerColorView = (TextView) view.findViewById(R.id.item_divider_color);
//            dividerColorView.setText("Divider: #" + Integer.toHexString(dividerColor));
//            dividerColorView.setTextColor(dividerColor);
//        }
        TextView title = (TextView) view.findViewById(R.id.item_title);
        title.setText("Title: " + getTitle());

//        TextView indicatorColorView = (TextView) view.findViewById(R.id.item_indicator_color);
//        indicatorColorView.setText("Indicator: #" + Integer.toHexString(getmIndicatorColor()));
//        indicatorColorView.setTextColor(getmIndicatorColor());
//
//        TextView dividerColorView = (TextView) view.findViewById(R.id.item_divider_color);
//        dividerColorView.setText("Divider: #" + Integer.toHexString(getmDividerColor()));
//        dividerColorView.setTextColor(getmDividerColor());
    }

    @Override
    public void refreshListViewData() {

    }
}
