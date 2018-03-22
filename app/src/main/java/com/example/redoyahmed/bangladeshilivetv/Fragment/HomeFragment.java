package com.example.redoyahmed.bangladeshilivetv.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.redoyahmed.bangladeshilivetv.Activity.SignInActivity;
import com.example.redoyahmed.bangladeshilivetv.Adapter.ChannelAdapter;
import com.example.redoyahmed.bangladeshilivetv.Model.Featured_channels;
import com.example.redoyahmed.bangladeshilivetv.Model.HomeResponse;
import com.example.redoyahmed.bangladeshilivetv.Model.ItemOffsetDecoration;
import com.example.redoyahmed.bangladeshilivetv.Model.LIVETVforHome;
import com.example.redoyahmed.bangladeshilivetv.Model.Latest_channels;
import com.example.redoyahmed.bangladeshilivetv.Model.Slider_list;
import com.example.redoyahmed.bangladeshilivetv.R;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiClient;
import com.example.redoyahmed.bangladeshilivetv.Services.ApiInterface;
import com.example.redoyahmed.bangladeshilivetv.Utils.ConnectionStatus;
import com.example.redoyahmed.bangladeshilivetv.Utils.CustomSweetAlertDialog;
import com.example.redoyahmed.bangladeshilivetv.Utils.StatusCodes;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class HomeFragment extends Fragment {

    ChannelAdapter mFeaturedAdapter;
    ChannelAdapter mLatestAdapter;

    //@BindView(R.id.)
    RecyclerView mFeaturedView;

    RecyclerView mLatestView;

    ProgressBar mProgressBar;

    NestedScrollView mScrollView;

    SwipeRefreshLayout swipeRefresh;

    TextView txtFeatured;

    TextView txtLatest;

    View rootView;

    Button btnFeatured;
    Button btnLatest;

    private FragmentManager fragmentManager;
    ArrayList<Featured_channels> mFeaturedList;
    ArrayList<Latest_channels> mLatestList;
    ArrayList<Slider_list> mSliderList;

    private static final String TAG = SignInActivity.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefresh = rootView.findViewById(R.id.swipe_refresh);
        /*if (Build.VERSION.SDK_INT >= 14) {
            this.swipeRefresh.setColorSchemeResources(17170459, 17170452, 17170456, 17170454);
        }*/
        //swipeRefresh.setOnRefreshListener(new C04831());
        mScrollView = rootView.findViewById(R.id.scrollView);
        mProgressBar = rootView.findViewById(R.id.progressBar);
        btnLatest = rootView.findViewById(R.id.btn_latest);
        btnFeatured = rootView.findViewById(R.id.btn_featured);
        txtLatest = rootView.findViewById(R.id.txt_latest_home_size);
        txtFeatured = rootView.findViewById(R.id.txt_featured_home_size);
        mLatestView = rootView.findViewById(R.id.rv_latest);
        mFeaturedView = rootView.findViewById(R.id.rv_featured);
        //mLatestList = new ArrayList();
        //mFeaturedList = new ArrayList();
        //mSliderList = new ArrayList();
        fragmentManager = getActivity().getSupportFragmentManager();
        mLatestView.setHasFixedSize(false);
        mLatestView.setNestedScrollingEnabled(false);
        mLatestView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(getActivity(), R.dimen.item_offset);
        mLatestView.addItemDecoration(itemDecoration);
        mFeaturedView.setHasFixedSize(false);
        mFeaturedView.setNestedScrollingEnabled(false);
        mFeaturedView.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        mFeaturedView.addItemDecoration(itemDecoration);

        mFeaturedList = new ArrayList<>();
        mLatestList = new ArrayList<>();
        mSliderList = new ArrayList<>();

        if (ConnectionStatus.getInstance(rootView.getContext()).isOnline()) {
            getHomeData();
        } else {
            showToast(getString(R.string.conne_msg1));
        }

        /*btnLatest.setOnClickListener(new C04842());
        btnFeatured.setOnClickListener(new C04853());*/
        return rootView;
    }

    private void getHomeData() {

        CustomSweetAlertDialog customSweetAlertDialog = new CustomSweetAlertDialog();
        final SweetAlertDialog dialog = customSweetAlertDialog.getProgressDialog(rootView.getContext(), "Running...");
        dialog.show();

        ApiInterface apiService = ApiClient.getLiveTvClient().create(ApiInterface.class);

        Call<HomeResponse> call = apiService.homeOutput();
        call.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                int statusCode = response.code();

                if (statusCode == StatusCodes.OK) {

                    final HomeResponse homeResponse = response.body();

                    final Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (!homeResponse.equals(null)) {
                                dialog.dismiss();

                                LIVETVforHome livetVforHome = homeResponse.getLIVETVforHome();
                                Slider_list[] sliderLists = livetVforHome.getSlider_list();
                                Latest_channels[] latestChannels = livetVforHome.getLatest_channels();
                                Featured_channels[] featuredChannels = livetVforHome.getFeatured_channels();

                                for (int i = 0; i < sliderLists.length; i++) {
                                    mSliderList.add(sliderLists[i]);
                                }
                                for (int i = 0; i < latestChannels.length; i++) {
                                    mLatestList.add(latestChannels[i]);
                                }
                                for (int i = 0; i < featuredChannels.length; i++) {
                                    mFeaturedList.add(featuredChannels[i]);
                                }
                                setResult();
                                handler.removeCallbacksAndMessages(true);
                            } else

                            {
                                handler.postDelayed(this, 100);
                            }
                            setResult();
                        }
                    };
                    handler.postDelayed(runnable, 100);
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    private void setResult() {
        //showRefresh(false);
        //mLatestAdapter = new ChannelAdapter(getActivity(), mSliderList);
        //mLatestView.setAdapter(mLatestAdapter);
        //mFeaturedAdapter = new ChannelAdapter(getActivity(), mFeaturedList);
        //mFeaturedView.setAdapter(mFeaturedAdapter);
        txtLatest.setText(mLatestList.size() + " Channel");
        txtFeatured.setText(mFeaturedList.size() + " Channel");

        if (!mSliderList.isEmpty()) {
            fragmentManager.beginTransaction().replace(R.id.ContainerSlider, SliderFragment.newInstance(mSliderList)).commit();
        }
    }

    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
