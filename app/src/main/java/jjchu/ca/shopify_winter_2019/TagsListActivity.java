package jjchu.ca.shopify_winter_2019;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import jjchu.ca.shopify_winter_2019.APIClient.APIClient;
import jjchu.ca.shopify_winter_2019.APIClient.ShopifyService;
import jjchu.ca.shopify_winter_2019.Models.ProductsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TagsListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags_list);

        recyclerView = findViewById(R.id.tags_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = APIClient.getClient();
        ShopifyService service = retrofit.create(ShopifyService.class);
        Call<ProductsModel> call = service.getProducts();

        call.enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                ProductsModel products = response.body();
            }

            @Override
            public void onFailure(Call<ProductsModel> call, Throwable t) {
                System.out.println("Error retrieving product data.");
            }
        });

        //TODO: Create adapter
        //adapter = new tagAdapter(tagData);
        //recyclerView.setAdapter(adapter);

    }
}
