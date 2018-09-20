package jjchu.ca.shopify_winter_2019.Models;

import java.util.List;

public class ProductModel {
    private String title;
    private String tags;
    private ImageModel image;
    private List<VariantModel> variants;

    public List<VariantModel> getVariants() {
        return variants;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public ImageModel getImage() {
        return image;
    }
}
