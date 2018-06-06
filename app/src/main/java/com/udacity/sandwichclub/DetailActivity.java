package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
   TextView MainName , Also_Known , Ingredant , Place_of ,Description ;
   Sandwich sandwich=null;
 /*  @BindView(R.id.image_iv) ImageView ingredientsIv;
   @BindView(R.id.main_name_tv) TextView MainName;
    @BindView(R.id.also_known_tv) TextView Also_Known;
    @BindView(R.id.ingredients_tv) TextView Ingredant;
    @BindView(R.id.origin_tv) TextView Place_of;
    @BindView(R.id.description_tv) TextView Description;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ImageView ingredientsIv=findViewById(R.id.image_iv);
        MainName=findViewById(R.id.main_name_tv);
        Also_Known=findViewById(R.id.also_known_tv);
        Ingredant=findViewById(R.id.ingredients_tv);
        Place_of=findViewById(R.id.origin_tv);
        Description=findViewById(R.id.description_tv);
        sandwich=new Sandwich();
       // ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null) {
            //closeOnError();
            Toast.makeText(this,"1 Activity , didn`t recieve intent",Toast.LENGTH_LONG).show();
        }

        int position = intent.getIntExtra("positionofdata", DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            //closeOnError();
            Toast.makeText(this,"1 Activity , didn`t know the place",Toast.LENGTH_LONG).show();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        //Sandwich sandwich = new Sandwich();
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
            if (sandwich == null) {
                // Sandwich data unavailable
                //closeOnError();
                Toast.makeText(this,"1 Activity , Sandwetch return is null",Toast.LENGTH_LONG).show();
                return;
            }

            populateUI();
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(ingredientsIv);

            setTitle(sandwich.getMainName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
     MainName.setText(sandwich.getMainName());
     Also_Known.setText(sandwich.getAlsoKnownAs().toString());
     Ingredant.setText(sandwich.getIngredients().toString());
     Place_of.setText(sandwich.getPlaceOfOrigin());
     Description.setText(sandwich.getDescription());


    }
}
