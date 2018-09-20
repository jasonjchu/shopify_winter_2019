package jjchu.ca.shopify_winter_2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import jjchu.ca.shopify_winter_2019.APIClient.APIClient;
import jjchu.ca.shopify_winter_2019.APIClient.ShopifyService;
import jjchu.ca.shopify_winter_2019.Models.ProductModel;
import jjchu.ca.shopify_winter_2019.Models.ProductsModel;
import jjchu.ca.shopify_winter_2019.TagsTools.TagsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TagsListActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DataCache dataCache;
    private View.OnClickListener tagClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_list);

        progressBar = findViewById(R.id.tagProgress);

        dataCache = DataCache.getInstance();

        tagClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TagsListActivity.this, ProductsListActivity.class);
                i.putExtra("Product", ((TextView) v.findViewById(R.id.tagTextView)).getText());
                startActivity(i);
            }
        };

        Retrofit retrofit = APIClient.getClient();
        ShopifyService service = retrofit.create(ShopifyService.class);
        Call<ProductsModel> call = service.getProducts();

        call.enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                if(response.code() == 200) {
                    if(response.body() != null) {
                        ProductsModel products = response.body();
                        for (ProductModel product : products.products) {
                            String newTags[] = product.getTags().split(", ");
                            for (String addedTag : newTags) {
                                dataCache.addProduct(addedTag, product);
                            }
                        }
                    } else {
                        //TODO: Maybe change to a toast?
                        AlertDialog alertDialog = new AlertDialog.Builder(TagsListActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("No products found.");
                        alertDialog.show();

                        stopProgress();
                    }
                } else{
                    //TODO: Maybe change to a toast?
                    AlertDialog alertDialog = new AlertDialog.Builder(TagsListActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("Got status code " + response.code() + ".");
                    alertDialog.show();

                    stopProgress();
                }

                makeRecycler(dataCache.getTagArray());
                stopProgress();
            }

            @Override
            public void onFailure(Call<ProductsModel> call, Throwable t) {
                //TODO: Maybe change to a toast?
                AlertDialog alertDialog = new AlertDialog.Builder(TagsListActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Cannot connect to server.");
                alertDialog.show();

                stopProgress();
            }
        });
    }

    private void makeRecycler(String[] tags) {
        recyclerView = findViewById(R.id.tagsRecyclerView);

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TagsAdapter(tags, tagClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void stopProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
