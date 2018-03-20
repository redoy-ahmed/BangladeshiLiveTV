package com.example.redoyahmed.bangladeshilivetv.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.redoyahmed.bangladeshilivetv.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by redoy.ahmed on 20-Mar-2018.
 */

public class IntroFragment extends Fragment {

    @BindView(R.id.image_intro)
    ImageView imageView;

    static final String LAYOUT_ID = "layoutid";
    Integer[] Images = new Integer[]{Integer.valueOf(R.drawable.mobile_intro_1), Integer.valueOf(R.drawable.mobile_intro_2), Integer.valueOf(R.drawable.mobile_intro_3), Integer.valueOf(R.drawable.mobile_intro_4)};

    public static IntroFragment newInstance(int layoutId) {
        IntroFragment pane = new IntroFragment();
        Bundle args = new Bundle();
        args.putInt(LAYOUT_ID, layoutId);
        pane.setArguments(args);
        return pane;
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intro, container, false);
        ButterKnife.bind(rootView);

        imageView.setImageResource(this.Images[getArguments().getInt(LAYOUT_ID, -1)].intValue());
        return rootView;
    }
}
