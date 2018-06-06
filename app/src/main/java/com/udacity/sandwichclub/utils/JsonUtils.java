package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        Sandwich sandwich=new Sandwich();
        JSONObject ob1=new JSONObject(json);
        //get Name
        JSONObject ob2=ob1.getJSONObject("name");
        sandwich.setMainName(ob2.getString("mainName"));
        String data1= ob2.getString("alsoKnownAs");// Here Also Known
       List<String > flist1= Arrays.asList(data1.split(","));
        sandwich.setDescription(ob1.getString("description"));
        sandwich.setImage(ob1.getString("image"));
        sandwich.setPlaceOfOrigin(ob1.getString("placeOfOrigin"));
       String data2=ob1.getString("ingredients");
        List<String > flist2= Arrays.asList(data2.split(","));
        sandwich.setAlsoKnownAs(flist1);
        sandwich.setIngredients(flist2);
        return sandwich;
    }
}
