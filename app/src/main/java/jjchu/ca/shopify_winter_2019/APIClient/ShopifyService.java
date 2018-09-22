package jjchu.ca.shopify_winter_2019.APIClient;

import java.util.Map;

import jjchu.ca.shopify_winter_2019.Models.ProductsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ShopifyService {
    @GET("admin/products.json")
    Call<ProductsModel> getProducts(@QueryMap Map<String, Object> params);
}