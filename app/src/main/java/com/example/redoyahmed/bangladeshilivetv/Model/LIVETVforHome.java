package com.example.redoyahmed.bangladeshilivetv.Model;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class LIVETVforHome {

    private Slider_list[] slider_list;

    private Featured_channels[] featured_channels;

    private Latest_channels[] latest_channels;

    public Slider_list[] getSlider_list ()
    {
        return slider_list;
    }

    public void setSlider_list (Slider_list[] slider_list)
    {
        this.slider_list = slider_list;
    }

    public Featured_channels[] getFeatured_channels ()
    {
        return featured_channels;
    }

    public void setFeatured_channels (Featured_channels[] featured_channels)
    {
        this.featured_channels = featured_channels;
    }

    public Latest_channels[] getLatest_channels ()
    {
        return latest_channels;
    }

    public void setLatest_channels (Latest_channels[] latest_channels)
    {
        this.latest_channels = latest_channels;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [slider_list = "+slider_list+", featured_channels = "+featured_channels+", latest_channels = "+latest_channels+"]";
    }
}
