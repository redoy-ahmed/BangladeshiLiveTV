package com.example.redoyahmed.bangladeshilivetv.Model;

/**
 * Created by redoy.ahmed on 20-Mar-2018.
 */

public class RegisterResponse {
    private LIVETV[] LIVETV;

    public LIVETV[] getLIVETV() {
        return LIVETV;
    }

    public void setLIVETV(LIVETV[] LIVETV) {
        this.LIVETV = LIVETV;
    }

    @Override
    public String toString() {
        return "ClassPojo [LIVETV = " + LIVETV + "]";
    }
}
