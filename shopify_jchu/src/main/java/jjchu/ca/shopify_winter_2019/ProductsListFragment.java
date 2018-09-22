package jjchu.ca.shopify_winter_2019;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jjchu.ca.shopify_winter_2019.RecyclerAdapters.ProductsAdapter;

public class ProductsListFragment extends Fragment {

    private String product;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private OnProductFragmentInteractionListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnProductFragmentInteractionListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.productsRecyclerView);

        DataCache cache = DataCache.getInstance();

        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProductsAdapter(cache.getProducts(product));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        listener.fragmentExit();
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public interface OnProductFragmentInteractionListener {
        void fragmentExit();
    }
}
