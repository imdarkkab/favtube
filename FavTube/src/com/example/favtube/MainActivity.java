package com.example.favtube;


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
import android.util.Log;

import com.example.favtube.ItemFragment.OnFragmentInteractionListener;

public class MainActivity extends FragmentActivity implements TabListener , OnFragmentInteractionListener  {

	static private String TAG = "FVT" ; 
	
	ViewPager viewPager ;
	ActionBar actionBar ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager) ;
		viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
//				Log.d(TAG, "onPageSelected at position "+position) ;
				actionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int position, float arg1, int arg2) {
				// TODO Auto-generated method stub
//				Log.d(TAG, "onPageScrolled at position "+position+" from "+arg1+" with number of pixels="+ arg2) ;
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				if(state==ViewPager.SCROLL_STATE_IDLE){
//					Log.d(TAG, "onPageScrollStateChanged Idle") ;
				}
				if(state==ViewPager.SCROLL_STATE_DRAGGING){
//					Log.d(TAG, "onPageScrollStateChanged Dragging") ;	
				}
				if(state==ViewPager.SCROLL_STATE_SETTLING){
//					Log.d(TAG, "onPageScrollStateChanged Settings") ;
				}
				
			}
		});
		

		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		

		
		ActionBar.Tab tab1 = actionBar.newTab() ;
		tab1.setText(R.string.fragment_a_name);
		tab1.setIcon(R.drawable.ic_action_search);
		tab1.setTabListener(this) ;
	
		ActionBar.Tab tab2 = actionBar.newTab() ;
		tab2.setText(R.string.fragment_b_name);
		tab2.setIcon(R.drawable.ic_action_favorite);
		tab2.setTabListener(this) ;
		
		ActionBar.Tab tab3 = actionBar.newTab() ;
		tab3.setText(R.string.fragment_c_name);
		tab3.setIcon(R.drawable.ic_action_video);
		tab3.setTabListener(this) ;
		
		ActionBar.Tab tab4 = actionBar.newTab() ;
		tab4.setText(R.string.fragment_d_name);
		tab4.setIcon(R.drawable.ic_action_headphones);
		tab4.setTabListener(this) ;
		
		actionBar.addTab(tab1);
		actionBar.addTab(tab2);
		actionBar.addTab(tab3);
		actionBar.addTab(tab4);
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		Log.d(TAG, "onTabReselected at position "+ tab.getPosition() +" name " + tab.getText()) ;
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		Log.d(TAG, "onTabSelected at position "+ tab.getPosition() +" name " + tab.getText()) ;
		viewPager.setCurrentItem(tab.getPosition());
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
//		Log.d(TAG, "onTabUnselected at position "+ tab.getPosition() +" name " + tab.getText()) ;
	}


	@Override
	public void onFragmentInteraction(String id) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onFragmentInteraction id "+ id) ;
	}

}


class MyAdapter extends FragmentPagerAdapter{

	public MyAdapter( FragmentManager fm) {
		super(fm) ;
	}
	@Override
	public Fragment getItem(int itemId) {
		Fragment fragment = null ;
		
		if(itemId==0){
			fragment = new ItemFragment() ;
			
	
		}
		
		if(itemId==1){
			fragment = new FragmentB() ;
		}
		
		if(itemId==2){
			fragment = new FragmentC() ;
		}
		
		if(itemId==3){
			fragment = new FragmentD() ;
		}
		
		return fragment ;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4 ;
	}
	

}
