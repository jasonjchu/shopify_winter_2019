package jjchu.ca.shopify_winter_2019;

import java.util.Arrays;
import java.util.HashMap;

public class TagCache {
    private HashMap<String, Boolean> tagMap;

    private static final TagCache ourInstance = new TagCache();

    public static TagCache getInstance() {
        return ourInstance;
    }

    private TagCache() {
        tagMap = new HashMap<>();
    }

    public void addTag(String tag) {
        tagMap.put(tag, true);
    }

    public void clear() {
        tagMap.clear();
    }

    public String[] getTagArray() {
        String[] tagArray = tagMap.keySet().toArray(new String[0]);
        Arrays.sort(tagArray);
        return tagArray;
    }
}
