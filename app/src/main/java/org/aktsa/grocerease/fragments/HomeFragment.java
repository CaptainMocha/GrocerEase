package org.aktsa.grocerease.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.aktsa.grocerease.R;
import org.aktsa.grocerease.models.Product;

public class HomeFragment extends Fragment {

    private static final String ARG_PRODUCT = "product";

    private Product product;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Product product) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product = getArguments().getParcelable("product");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(view);
        return view;
    }

    private void initializeViews(View view) {
        TextView productNameView = (TextView) view.findViewById(R.id.product_name);
        TextView brandNameView = (TextView) view.findViewById(R.id.brand_name);
        productNameView.setText(product.getProductName());
        brandNameView.setText(product.getBrand());

        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(getActivity());
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
    }

    private class CustomPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        public CustomPagerAdapter(Context context) {
            this.context = context;
            layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return product.getImageUrls().size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = layoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view);
            Glide.with(context).load(product.getImageUrls().get(position)).into(imageView);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((FrameLayout) object);
        }
    }

}
