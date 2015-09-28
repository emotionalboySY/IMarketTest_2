package com.serahaeyum.imarkettest2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mListItems;
        private LinearLayout Drawer_main;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TextView textView_id = (TextView) findViewById(R.id.drawer_id);
            TextView textView_pw = (TextView) findViewById(R.id.drawer_pw);

        Intent login = getIntent();

        String id = login.getStringExtra("id");
        String pw = login.getStringExtra("pw");

        textView_id.setText(String.valueOf(id));
        textView_pw.setText(String.valueOf(pw));

        mTitle = mDrawerTitle = getTitle();
        mListItems = getResources().getStringArray(R.array.navigation_items);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mDrawerList = (ListView)findViewById(R.id.navList);
        Drawer_main = (LinearLayout) findViewById(R.id.drawer_main);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mActivityTitle = getTitle().toString();

        mAdapter = new ArrayAdapter<String>(this, R.layout.drawer_item_style, mListItems);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                mDrawerLayout.openDrawer(Drawer_main);
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle(R.string.navigation_name);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {

        // update the main content by replacing fragments
        Fragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(DetailFragment.LIST_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.drawer_detail, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mListItems[position]);
        mDrawerLayout.closeDrawer(Drawer_main);

    }

    public static class DetailFragment extends Fragment {
        public static final String LIST_NUMBER = "list_number";

        public DetailFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            int i = getArguments().getInt(LIST_NUMBER);
            int ViewId = 0;
            switch (i) {
                case 0:
                    ViewId = R.layout.drawer_detail_1;
                    break;
                case 1:
                    ViewId = R.layout.drawer_detail_2;
                    break;
                case 2:
                    ViewId = R.layout.drawer_detail_3;
                    break;
                case 3:
                    ViewId = R.layout.drawer_detail_4;
            }
            View rootView = inflater.inflate(ViewId, container, false);
            return rootView;
        }
    }
}