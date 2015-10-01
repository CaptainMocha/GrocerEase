package org.aktsa.grocerease.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.aktsa.grocerease.R;
import org.aktsa.grocerease.fragments.AdditionalInformationFragment;
import org.aktsa.grocerease.fragments.AllergensFragment;
import org.aktsa.grocerease.fragments.HomeFragment;
import org.aktsa.grocerease.fragments.IngredientsFragment;
import org.aktsa.grocerease.fragments.InstructionsFragment;
import org.aktsa.grocerease.fragments.NutritionFragment;
import org.aktsa.grocerease.fragments.SettingsFragment;
import org.aktsa.grocerease.models.Product;

/**
 * Shows the product information after scanning.
 *
 * @author cheek
 * @version 1, 9/25/2015
 */
public class ProductActivity extends AppCompatActivity implements NutritionFragment.OnFragmentInteractionListener {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initializeViews();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        product = bundle.getParcelable("product");

        replaceFragment(0);

        Snackbar.make(findViewById(android.R.id.content), product.getBrand(), Snackbar.LENGTH_SHORT).show();
    }

    private void initializeViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawer(toolbar);
    }

    private void setupDrawer(Toolbar toolbar) {
        PrimaryDrawerItem homeItem = new PrimaryDrawerItem()
                .withName("Home")
                .withIcon(GoogleMaterial.Icon.gmd_home);
        PrimaryDrawerItem nutritionItem = new PrimaryDrawerItem()
                .withName("Nutrition")
                .withIcon(GoogleMaterial.Icon.gmd_restaurant_menu);
        PrimaryDrawerItem allergensItem = new PrimaryDrawerItem()
                .withName("Allergens")
                .withIcon(GoogleMaterial.Icon.gmd_local_florist);
        PrimaryDrawerItem ingredientsItem = new PrimaryDrawerItem()
                .withName("Ingredients")
                .withIcon(GoogleMaterial.Icon.gmd_cake);
        PrimaryDrawerItem instructionsItem = new PrimaryDrawerItem()
                .withName("Instructions")
                .withIcon(GoogleMaterial.Icon.gmd_list);
        PrimaryDrawerItem additionalInformationItem = new PrimaryDrawerItem()
                .withName("Additional Information")
                .withIcon(GoogleMaterial.Icon.gmd_expand_more);
        PrimaryDrawerItem settingsItem = new PrimaryDrawerItem()
                .withName("Settings")
                .withIcon(GoogleMaterial.Icon.gmd_settings);

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        homeItem,
                        nutritionItem,
                        allergensItem,
                        ingredientsItem,
                        instructionsItem,
                        additionalInformationItem)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem iDrawerItem) {
                        replaceFragment(position);
                        return false;
                    }
                })
                .build();
        drawer.addStickyFooterItem(settingsItem);
    }

    private void replaceFragment(int position) {
        Fragment fragment;
        String title;
        switch (position) {
            case 0:
                fragment = HomeFragment.newInstance(product);
                title = "Home";
                break;
            case 1:
                fragment = HomeFragment.newInstance(product);
                title = "Nutrition";
                break;
            case 2:
                fragment = HomeFragment.newInstance(product);
                title = "Allergens";
                break;
            case 3:
                fragment = HomeFragment.newInstance(product);
                title = "Ingredients";
                break;
            case 4:
                fragment = HomeFragment.newInstance(product);
                title = "Instructions";
                break;
            case 5:
                fragment = HomeFragment.newInstance(product);
                title = "Additional Information";
                break;
            case 6:
                fragment = HomeFragment.newInstance(product);
                title = "Settings";
                break;
            default:
                fragment = HomeFragment.newInstance(product);
                title = "Home";
                break;
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
