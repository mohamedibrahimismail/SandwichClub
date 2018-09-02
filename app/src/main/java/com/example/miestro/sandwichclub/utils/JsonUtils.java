package com.example.miestro.sandwichclub.utils;


import android.util.Log;

import com.example.miestro.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static String mainName;
    private static List<String> alsoKnownAs = null;
    private static String placeOfOrigin;
    private static String description;
    private static String image;
    private static List<String> ingredients = null;

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONObject nameobject = jsonObject.getJSONObject("name");
            mainName = nameobject.getString("mainName");
            alsoKnownAs = convert_to_list(nameobject.getJSONArray("alsoKnownAs"));
            placeOfOrigin = jsonObject.getString("placeOfOrigin");
            description = jsonObject.getString("description");
            image = jsonObject.getString("image");
            ingredients = convert_to_list(jsonObject.getJSONArray("ingredients"));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("JsonUtils",e.getMessage());
        }

        Sandwich sandwich = new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);

        return sandwich;
    }

    private static List<String> convert_to_list(JSONArray jsonArray){

        List<String> listdata = new ArrayList<>();

        if (jsonArray != null) {
            for (int i=0;i<jsonArray.length();i++){
                try {
                    listdata.add(jsonArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        return listdata;

    }
}
