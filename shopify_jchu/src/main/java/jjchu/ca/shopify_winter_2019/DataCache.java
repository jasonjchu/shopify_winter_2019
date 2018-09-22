package jjchu.ca.shopify_winter_2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jjchu.ca.shopify_winter_2019.Models.ProductModel;

public class DataCache {
    private HashMap<String, List<ProductModel>> tagMap;

    private static final DataCache ourInstance = new DataCache();

    public static DataCache getInstance() {
        return ourInstance;
    }

    private DataCache() {
        tagMap = new HashMap<>();
    }

    public void clear() {
        tagMap.clear();
    }

    public void addProduct(String tag, ProductModel product) {
        if(!tagMap.containsKey(tag)) {
            tagMap.put(tag, new LinkedList<ProductModel>());
        }
        tagMap.get(tag).add(product);
    }

    public String[] getTagArray() {
        String[] tagArray = tagMap.keySet().toArray(new String[0]);
        Arrays.sort(tagArray);
        return tagArray;
    }

    public List<ProductModel> getProducts(String name) {
        return tagMap.get(name);
    }
}
