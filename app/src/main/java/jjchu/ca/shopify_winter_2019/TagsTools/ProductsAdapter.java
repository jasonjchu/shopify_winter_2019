package jjchu.ca.shopify_winter_2019.TagsTools;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import jjchu.ca.shopify_winter_2019.Models.ProductModel;
import jjchu.ca.shopify_winter_2019.Models.VariantModel;
import jjchu.ca.shopify_winter_2019.R;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<ProductModel> products;

    public static class ProductsViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout productView;
        public ProductsViewHolder(@NonNull LinearLayout v) {
            super(v);
            productView = v;
        }
    }

    public ProductsAdapter(List<ProductModel> productsSource) {
        products = productsSource;
    }

    @NonNull
    @Override
    public ProductsAdapter.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_products, viewGroup, false);

        ProductsViewHolder vh = new ProductsViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductsViewHolder productsViewHolder, final int i) {
        ((TextView) productsViewHolder.productView.findViewById(R.id.productName))
                .setText(products.get(i).getTitle());
        ((TextView) productsViewHolder.productView.findViewById(R.id.productInventory))
                .setText("Inventory: " + getInventory(products.get(i).getVariants()));
        //TODO: Add bitmap
//        ((ImageView) productsViewHolder.productView.findViewById(R.id.productImage))
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    private int getInventory(List<VariantModel> variants) {
        int sum = 0;
        for(VariantModel variant : variants) {
            sum += variant.getInventory_quantity();
        }
        return sum;
    }
}
