package org.aktsa.grocerease.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.factual.driver.Factual;
import com.factual.driver.Query;
import com.factual.driver.ReadResponse;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.aktsa.grocerease.R;
import org.aktsa.grocerease.models.Constants;
import org.aktsa.grocerease.models.Product;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The first screen presented to the user.
 * Contains buttons for scanning, language, and history.
 *
 * @author cheek
 * @version 1, 9/25/2015
 */
public class MainActivity extends AppCompatActivity {

    private static final int SCAN_REQUEST = 0;
    private Factual factual = new Factual(Constants.FACTUAL_OAUTH_KEY, Constants.FACTUAL_OAUTH_SECRET);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton buttonScan = (ImageButton) findViewById(R.id.button_scan);
        ImageButton buttonHistory = (ImageButton) findViewById(R.id.button_history);
        ImageButton buttonLanguage = (ImageButton) findViewById(R.id.button_language);
        buttonScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchScannerActivity();
            }
        });
    }

    private void launchScannerActivity() {
//        Intent intent = new Intent(this, ScannerActivity.class);
//        startActivityForResult(intent, SCAN_REQUEST);
        Map<String, Object> map = Constants.createMap();
        showProduct(map);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCAN_REQUEST) {
            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("Contents");
                String format = data.getStringExtra("Format");
                Snackbar.make(findViewById(android.R.id.content),
                        "Contents = " + contents + ", Format = " + format,
                        Snackbar.LENGTH_SHORT).show();
                searchBarcode(contents, format);
            }
        }
    }

    private void searchBarcode(String barcode, String format) {
        FactualRetrievalTask task = new FactualRetrievalTask();
        Query query = new Query();
        switch (format) {
            case "UPCA":
                query = query.field("upc").isEqual(barcode);
                break;
            case "UPCE":
                query = query.field("upc_e").isEqual(barcode);
                break;
            case "EAN13":
                query = query.field("ean13").isEqual(barcode);
                break;
            default:
                Toast.makeText(this, "Barcode format unsupported", Toast.LENGTH_SHORT).show();
                break;
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle("Searching...");
        progressDialog.setMessage("Searching over 700,000 products...");
        task.setProgressDialog(progressDialog);
        task.execute(query);
    }

    private void showProduct(Map<String, Object> product) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();

        Intent intent = new Intent(MainActivity.this, ProductActivity.class);

        Double avgPrice = (Double) product.get("avg_price");
        String brand = (String) product.get("brand");
        Double calcium = toDouble(product.get("calcium"));
        Double calories = toDouble(product.get("calories"));
        String category = (String) product.get("category");
        Double cholesterol = toDouble(product.get("cholesterol"));
        Double dietaryFiber = toDouble(product.get("dietary_fiber"));
        Double fatCalories = toDouble(product.get("fat_calories"));
        JSONArray imageUrlsJson = (JSONArray) product.get("image_urls");
        ArrayList<String> imageUrlsList = new Gson().fromJson(imageUrlsJson.toString(), listType);
        JSONArray ingredientsJson = (JSONArray) product.get("ingredients");
        ArrayList<String> ingredientsList = new Gson().fromJson(ingredientsJson.toString(), listType);
        Double iron = toDouble(product.get("iron"));
        String manufacturer = (String) product.get("manufacturer");
        Double potassium = toDouble(product.get("potassium"));
        String productName = (String) product.get("product_name");
        Double protein = toDouble(product.get("protein"));
        Double satFat = toDouble(product.get("sat_fat"));
        String servingSize = (String) product.get("serving_size");
        Double servings = toDouble(product.get("servings"));
        JSONArray sizeJson = (JSONArray) product.get("size");
        String size = null;
        try {
            size = sizeJson.get(0).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Double sodium = toDouble(product.get("sodium"));
        Double sugars = toDouble(product.get("sugars"));
        Double totalCarb = toDouble(product.get("total_carb"));
        Double totalFat = toDouble(product.get("total_fat"));
        Double transFat = toDouble(product.get("trans_fat"));
        Double vitaminA = toDouble(product.get("vitamin_a"));
        Double vitaminC = toDouble(product.get("vitamin_c"));

        Product productSerializable = new Product(avgPrice, brand, calcium, calories, category, cholesterol, dietaryFiber,
                fatCalories, imageUrlsList, ingredientsList, iron, manufacturer, potassium, productName, protein,
                satFat, servingSize, servings, size, sodium, sugars, totalCarb, totalFat, transFat,
                vitaminA, vitaminC);

        intent.putExtra("product", productSerializable);

        startActivity(intent);
    }

    private Double toDouble(Object o) {
        if (o == null) {
            return null;
        } else {
            if (o.getClass().equals(Integer.class)) {
                return (double) (int) o;
            } else {
                return (double) o;
            }
        }
    }

    protected class FactualRetrievalTask extends AsyncTask<Query, Integer, List<ReadResponse>> {

        private ProgressDialog progressDialog;

        public void setProgressDialog(ProgressDialog progressDialog) {
            this.progressDialog = progressDialog;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected List<ReadResponse> doInBackground(Query... params) {
            List<ReadResponse> results = Lists.newArrayList();
            for (Query q : params) {
                results.add(factual.fetch("products-cpg-nutrition", q));
            }
            return results;
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
        }

        @Override
        protected void onPostExecute(List<ReadResponse> responses) {
            ReadResponse response = responses.get(0);
            Map<String, Object> product = response.getData().get(0);
            showProduct(product);
            progressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
