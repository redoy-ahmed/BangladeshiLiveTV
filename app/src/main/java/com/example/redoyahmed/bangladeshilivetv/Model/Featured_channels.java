package com.example.redoyahmed.bangladeshilivetv.Model;

/**
 * Created by redoy.ahmed on 22-Mar-2018.
 */

public class Featured_channels {

    private String cat_id;
    private String id;
    private String channel_url;
    private String channel_title;
    private String category_name;
    private String category_image;
    private String channel_thumbnail;
    private String cid;
    private String channel_desc;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel_url() {
        return channel_url;
    }

    public void setChannel_url(String channel_url) {
        this.channel_url = channel_url;
    }

    public String getChannel_title() {
        return channel_title;
    }

    public void setChannel_title(String channel_title) {
        this.channel_title = channel_title;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getChannel_thumbnail() {
        return channel_thumbnail;
    }

    public void setChannel_thumbnail(String channel_thumbnail) {
        this.channel_thumbnail = channel_thumbnail;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getChannel_desc() {
        return channel_desc;
    }

    public void setChannel_desc(String channel_desc) {
        this.channel_desc = channel_desc;
    }

    @Override
    public String toString() {
        return "ClassPojo [cat_id = " + cat_id + ", id = " + id + ", channel_url = " + channel_url + ", channel_title = " + channel_title + ", category_name = " + category_name + ", category_image = " + category_image + ", channel_thumbnail = " + channel_thumbnail + ", cid = " + cid + ", channel_desc = " + channel_desc + "]";
    }

}
