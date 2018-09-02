package com.example.miestro.sandwichclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miestro.sandwichclub.model.Sandwich;
import com.example.miestro.sandwichclub.utils.JsonUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView foodimage;
    private TextView placeOfOrigin;
    private TextView also_known_as_label;
    private TextView ingredients_label;
    private TextView description_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        foodimage = (ImageView) findViewById(R.id.image_iv);
        placeOfOrigin = (TextView)findViewById(R.id.origin_tv);
        also_known_as_label = (TextView)findViewById(R.id.also_known_as);
        ingredients_label = (TextView)findViewById(R.id.ingredients);
        description_tv = (TextView)findViewById(R.id.description_tv);






        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);

        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(foodimage);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        placeOfOrigin.setText(sandwich.getPlaceOfOrigin());
        also_known_as_label.setText(convert_to_string(sandwich.getAlsoKnownAs()));
        ingredients_label.setText(convert_to_string(sandwich.getIngredients()));
        description_tv.setText(sandwich.getDescription());

    }

    private String convert_to_string(List<String> list){


         String result = "";

         for(String x :list){

            result+=x+" ";

        }

        return result;



    }

}
