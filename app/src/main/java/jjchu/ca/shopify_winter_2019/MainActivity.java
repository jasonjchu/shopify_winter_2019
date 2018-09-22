package jjchu.ca.shopify_winter_2019;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements TagsListFragment.OnTagFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TagsListFragment tagsListFragment = new TagsListFragment();
        fragmentTransaction.add(R.id.tagFragment, tagsListFragment);
        fragmentTransaction.commit();
    }

    public void onFragmentInteraction(String product) {

    }
}
