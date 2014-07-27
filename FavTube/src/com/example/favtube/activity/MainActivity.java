package com.example.favtube.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;


import com.example.favtube.R;
import com.example.favtube.fragment.FragmentB;
import com.example.favtube.fragment.FragmentC;
import com.example.favtube.fragment.FragmentD;
import com.example.favtube.fragment.SearchFragment;
import com.example.favtube.util.Log;

public class MainActivity extends FragmentActivity implements TabListener {



	ViewPager viewPager;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				 Log.d( "onPageSelected at position "+position) ;
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// Log.d(
				// "onPageScrolled at position "+position+" from "+arg1+" with number of pixels="+
				// arg2) ;
			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if (state == ViewPager.SCROLL_STATE_IDLE) {
					 Log.d("onPageScrollStateChanged Idle") ;
				}
				if (state == ViewPager.SCROLL_STATE_DRAGGING) {
					 Log.d("onPageScrollStateChanged Dragging") ;
				}
				if (state == ViewPager.SCROLL_STATE_SETTLING) {
					 Log.d("onPageScrollStateChanged Settings") ;
				}

			}
		});
		
		
	


		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);

		ActionBar.Tab searchTab = actionBar.newTab();
		searchTab.setText(R.string.tab_videos_name);
		searchTab.setTabListener(this);

		ActionBar.Tab tab2 = actionBar.newTab();
		tab2.setText(R.string.fragment_b_name);
		tab2.setTabListener(this);

		ActionBar.Tab tab3 = actionBar.newTab();
		tab3.setText(R.string.fragment_c_name);
		tab3.setTabListener(this);

		ActionBar.Tab tab4 = actionBar.newTab();
		tab4.setText(R.string.fragment_d_name);
		tab4.setTabListener(this);

		actionBar.addTab(searchTab);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		actionBar.addTab(tab4);
		

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}

class MyAdapter extends FragmentPagerAdapter {
	
	FragmentManager manager ;
	
	public MyAdapter(FragmentManager fm) {
		super(fm);
		this.manager = fm ;
	}

	@Override
	public Fragment getItem(int itemId) {
//		Fragment fragment = null;

		if (itemId == 0) {
			SearchFragment searchFragment = new SearchFragment();
			return searchFragment;
		} else if (itemId == 1) {
			
			return new FragmentB();
		} else if (itemId == 2) {
			return new FragmentC();
		} else if (itemId == 3) {
			return new FragmentD();
		}else{
			return null ;
		}


	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4 ;
	}




}


