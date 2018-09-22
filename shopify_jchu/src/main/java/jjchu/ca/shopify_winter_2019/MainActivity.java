package jjchu.ca.shopify_winter_2019;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements TagsListFragment.OnTagFragmentInteractionListener, ProductsListFragment.OnProductFragmentInteractionListener {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.titleText);
        title.setText("Tags");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TagsListFragment tagsListFragment = new TagsListFragment();
        fragmentTransaction.add(R.id.fragment, tagsListFragment);
        fragmentTransaction.commit();
    }

    public void onTagInteraction(String product) {
        title.setText("Products");

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProductsListFragment productsListFragment = new ProductsListFragment();
        productsListFragment.setProduct(product);
        fragmentTransaction.add(R.id.fragment, productsListFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void fragmentExit() {
        title.setText("Tags");
    }
}
