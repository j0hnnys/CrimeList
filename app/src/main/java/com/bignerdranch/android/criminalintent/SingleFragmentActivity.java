package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Johnny on 8/21/15.
 */

/**
 * Abstract class that Fragment Activity classes implement. They must define createFragment()
 * to match the Fragment class it covers.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    // The class that extends SingleFragmentActivity will implement
    // the createFragment() method to return the Fragment associated
    // with the fragment.xml layout file we wish to display.
    protected abstract Fragment createFragment();

    /**
     * This onCreate() method will be the onCreate() method used for all classes
     * that extends SingleFragmentActivity.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        // Always good to use this check because sometimes the †arget fragment might not exist.
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}