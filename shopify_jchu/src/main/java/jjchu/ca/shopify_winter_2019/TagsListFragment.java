package jjchu.ca.shopify_winter_2019;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import jjchu.ca.shopify_winter_2019.APIClient.APIClient;
import jjchu.ca.shopify_winter_2019.APIClient.ShopifyService;
import jjchu.ca.shopify_winter_2019.Models.ProductModel;
import jjchu.ca.shopify_winter_2019.Models.ProductsModel;
import jjchu.ca.shopify_winter_2019.RecyclerAdapters.TagsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TagsListFragment extends Fragment {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter<TagsAdapter.TagsViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DataCache dataCache;
    private View.OnClickListener tagClickListener;
    private OnTagFragmentInteractionListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnTagFragmentInteractionListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataCache = DataCache.getInstance();

        tagClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onTagInteraction(
                        ((TextView) v.findViewById(R.id.tagTextView)).getText().toString()
                );
            }
        };

        HashMap<String, Object> params = new HashMap<>();
        params.put("page", 1);
        params.put("access_token", "c32313df0d0ef512ca64d5b336a0d7c6");

        Retrofit retrofit = APIClient.getClient();
        ShopifyService service = retrofit.create(ShopifyService.class);
        Call<ProductsModel> call = service.getProducts(params);

        call.enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                if(response.code() == 200) {
                    if(response.body() != null) {
                        dataCache.clear();
                        ProductsModel products = response.body();
                        for (ProductModel product : products.products) {
                            String newTags[] = product.getTags().split(", ");
                            for (String addedTag : newTags) {
                                dataCache.addProduct(addedTag, product);
                            }
                        }
                    } else {
                        makeToast("No products available.");
                    }
                } else{
                    makeToast("Error: Unable to fetch tags.");
                }

                makeRecycler(dataCache.getTagArray());
                stopProgress();
            }

            @Override
            public void onFailure(Call<ProductsModel> call, Throwable t) {
                makeToast("Error: Unable to connect to server.");
                stopProgress();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tags_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.tagProgress);
        recyclerView = view.findViewById(R.id.tagsRecyclerView);
    }

    private void makeRecycler(String[] tags) {
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new TagsAdapter(tags, tagClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void stopProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    public void makeToast(String message) {
        Toast.makeText(getActivity(), message,
                Toast.LENGTH_LONG).show();
    }

    public interface OnTagFragmentInteractionListener {
        void onTagInteraction(String product);
    }
}
