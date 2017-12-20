package mvedua.msu.com.spartansafety;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mvedua.msu.com.spartansafety.Fragments.ReportFragment;
import mvedua.msu.com.spartansafety.Fragments.ResourcesFragment;
import mvedua.msu.com.spartansafety.Fragments.TwitterFragment;

public class MainActivity extends AppCompatActivity {

    ResourcesFragment resourcesFragment = new ResourcesFragment();
    ReportFragment reportFragment = new ReportFragment();
    TwitterFragment twitterFragment = new TwitterFragment();

    private void doFragmentTransaction(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_resources:
                    doFragmentTransaction(resourcesFragment);
                    return true;
                case R.id.navigation_report:
                    doFragmentTransaction(reportFragment);
                    return true;
                case R.id.navigation_twitter:
                    doFragmentTransaction(twitterFragment);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doFragmentTransaction(resourcesFragment);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
