/*
 * Copyright (C) 2012 yueyueniao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.LeftFragement;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import jerome.i_pos.R;


public class LeftFragment extends Fragment
{
	OnLeftFragmentEventListener mCallback;
	// Container Activity must implement this interface
	public interface OnLeftFragmentEventListener {
		public void onListViewClickChanged();
	}

//	private String[] list = {"鉛筆","原子筆","鋼筆","毛筆","彩色筆"};
//	private ArrayAdapter<String> listAdapter;
	private ListView listView;
	private BaseItemData _CurrentItem;
	private LeftListViewAdapter _LeftListViewAdapter;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.left_fragment_listview, null);
		listView = (ListView)view.findViewById(R.id.listView);

		return view;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		try {
			mCallback = (OnLeftFragmentEventListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnHeadlineSelectedListener");
		}
	}
	public void setListViewData(BaseItemData item)
	{
		_CurrentItem = item;
		if (_CurrentItem != null) {
			_LeftListViewAdapter = new LeftListViewAdapter(getActivity(), _CurrentItem, mCallback);
			listView.setAdapter(_LeftListViewAdapter);
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//					if (_CurrentItem.getParent() == null) {
//						_CurrentItem = _CurrentItem.getChild(position);
//					} else {
//						if (position == 0)
//							_CurrentItem = _CurrentItem.getParent();
//						else
//							_CurrentItem = _CurrentItem.getChild(position - 1);
//					}
//					_LeftListViewAdapter.setListViewData(_CurrentItem);
//					_LeftListViewAdapter.notifyDataSetChanged();
					for (int index = 0 ; index < _CurrentItem.getChildSize() ; index++) {
						if (position == 0)
							_CurrentItem.getChild(index).setVisible(true);
						else if (index == (position - 1))
							_CurrentItem.getChild(index).setVisible(true);
						else
							_CurrentItem.getChild(index).setVisible(false);
					}
					mCallback.onListViewClickChanged();
				}
			});
		}
	}
}
