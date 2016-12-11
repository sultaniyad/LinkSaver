package com.iyad.sultan.linksaver;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.iyad.sultan.linksaver.View.AllLinks;
import com.iyad.sultan.linksaver.View.ImportantLinks;
import com.iyad.sultan.linksaver.View.InsertLink;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.viewpager)ViewPager viewPager;
    @BindView(R.id.tabs)TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//TooBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//ViewPager
        setupViewPager(viewPager);
//Tabs
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adpater = new ViewPagerAdapter(getSupportFragmentManager());
        adpater.addFragment(new AllLinks(), getResources().getString(R.string.all_Links));
        adpater.addFragment(new ImportantLinks(),getResources().getString(R.string.important_link));


        viewPager.setAdapter(adpater);
    }



//Menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
/*
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
         case R.id.new_link:startActivity(new Intent(MainActivity.this, InsertLink.class)); break;
            case android.R.id.home:finish(); break;
        }
       return true;
    }



    //ViewPager Adapter
    class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> Flist =new ArrayList<>();
        private final List<String> Fname =new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Flist.get(position);
        }

        @Override
        public int getCount() {
            return Flist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Fname.get(position);
        }
        public void addFragment(Fragment fragment,String name){
            Flist.add(fragment);
            Fname.add(name);
        }
    }



}
