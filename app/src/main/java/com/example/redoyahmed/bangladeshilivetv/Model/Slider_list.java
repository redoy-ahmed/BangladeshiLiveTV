package com.example.redoyahmed.bangladeshilivetv.Model;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class Slider_list {

    private String home_banner;
    private String home_title;
    private String home_url;

    public String getHome_banner() {
        return home_banner;
    }

    public void setHome_banner(String home_banner) {
        this.home_banner = home_banner;
    }

    public String getHome_title() {
        return home_title;
    }

    public void setHome_title(String home_title) {
        this.home_title = home_title;
    }

    public String getHome_url() {
        return home_url;
    }

    public void setHome_url(String home_url) {
        this.home_url = home_url;
    }

    @Override
    public String toString() {
        return "ClassPojo [home_banner = " + home_banner + ", home_title = " + home_title + ", home_url = " + home_url + "]";
    }
}
