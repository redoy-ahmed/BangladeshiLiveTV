package com.example.redoyahmed.bangladeshilivetv.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.redoyahmed.bangladeshilivetv.Activity.TVPlayActivity;
import com.example.redoyahmed.bangladeshilivetv.Model.ItemSlider;
import com.example.redoyahmed.bangladeshilivetv.Model.Slider_list;
import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiClient;

import java.util.ArrayList;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class SliderFragment extends Fragment implements BaseSliderView.OnSliderClickListener {
    static ArrayList<Slider_list> objects;
    private SliderLayout mDemoSlider;

    public static SliderFragment newInstance(ArrayList<Slider_list> categoryId) {
        SliderFragment f = new SliderFragment();
        objects = categoryId;
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_slider, container, false);
        mDemoSlider = rootView.findViewById(R.id.slider);
        for (int i = 0; i < objects.size(); i++) {
            ItemSlider itemSlider = new ItemSlider();
            itemSlider.setImage(objects.get(i).getHome_banner());
            itemSlider.setLink(objects.get(i).getHome_url());
            itemSlider.setName(objects.get(i).getHome_title());

            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView.description(itemSlider.getName());
            textSliderView.image(ApiClient.IMAGE_PATH + itemSlider.getImage());
            //textSliderView.setScaleType(ImageView.ScaleType.FIT_XY);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", itemSlider.getLink());
            textSliderView.setOnSliderClickListener(this);
            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        return rootView;
    }

    public void onSliderClick(BaseSliderView slider) {
        Intent intent = new Intent(getActivity(), TVPlayActivity.class);
        intent.putExtra("url", slider.getBundle().getString("extra"));
        startActivity(intent);
    }
}
