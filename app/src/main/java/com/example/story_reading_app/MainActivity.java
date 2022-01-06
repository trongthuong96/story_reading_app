package com.example.story_reading_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.story_reading_app.fragment.CategoryFragment;
import com.example.story_reading_app.fragment.HomeFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //menu
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //fragment
    private HomeFragment homeFragment = new HomeFragment();
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_CATEGORY = 1;

    private int mCurrentFragment = FRAGMENT_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingLayout();
        replateFragment(new HomeFragment());
        navigationView.getMenu().performIdentifierAction(R.id.menu_home, FRAGMENT_HOME);

    }

    //mapping
    private void mappingLayout(){
        //menu
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.openNavDrawer,
                R.string.closeNavDrawer
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    //onclick menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_home) {
            if(mCurrentFragment != FRAGMENT_HOME){
                replateFragment(homeFragment);
                mCurrentFragment = FRAGMENT_HOME;
            }
        } else if(id == R.id.menu_category){
            if(mCurrentFragment != FRAGMENT_CATEGORY){
                replateFragment(new CategoryFragment());
                mCurrentFragment = FRAGMENT_CATEGORY;
            }
        }

        item.setCheckable(true);
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    //fragment home
    private void replateFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    // MENU search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    // page search
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.app_bar_search:
                Intent intentSearch = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intentSearch);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}