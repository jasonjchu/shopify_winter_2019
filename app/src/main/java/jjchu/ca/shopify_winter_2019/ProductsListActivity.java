package jjchu.ca.shopify_winter_2019;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import jjchu.ca.shopify_winter_2019.TagsTools.ProductsAdapter;

public class ProductsListActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DataCache dataCache;
    private View.OnClickListener tagClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        DataCache cache = DataCache.getInstance();

        recyclerView = findViewById(R.id.productsRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductsAdapter(cache.getProducts(getIntent().getStringExtra("Product")));
        recyclerView.setAdapter(adapter);
    }
}
