package com.Yad2AutomationWithGUI.bouncer.Services;

import com.Yad2AutomationWithGUI.bouncer.Frames.DashboardJFrame;
import com.Yad2AutomationWithGUI.bouncer.Models.Ad;
import com.Yad2AutomationWithGUI.bouncer.Models.Token;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class FlowControl {
    private static FlowControl flowControlSingleton = null;
    private DashboardJFrame dashboardJFrame;
    private Token token;
    private Instant lastBounced;
    private boolean isAbleToBounce = false;
    protected String email;
    protected String password;
    private ArrayList<Ad> ads = new ArrayList<>();
    private boolean adsShown = false;

    public static FlowControl getInstance() {
        if (flowControlSingleton == null) {
            flowControlSingleton = new FlowControl();
        }
        return flowControlSingleton;
    }

    public boolean performLogin(String email, String password) throws IOException {
        boolean results = false;
        this.token = Services.getInstance().getAuthorized(email, password);
        if (this.token != null) {
            this.email = email;
            this.password = password;
            performGetAds();
            results = true;
        }
        return results;
    }

    public boolean performLogin() throws IOException {
        boolean results = false;
        this.token = Services.getInstance().getAuthorized(this.email, this.password);
        if (this.token != null) {
            performGetAds();
            results = true;
        }
        return results;
    }

    public boolean performGetAds() throws IOException {
        boolean results = false;
        this.ads = Services.getInstance().getAds(token.toString());
        if (this.ads != null) {
            performBounceAds();
            results = true;
        }
        return results;
    }

    public void performBounceAds() throws IOException {
        for (int i = 0; i < ads.size(); i++) {
            Services.getInstance().bounceAds(ads.get(i).getId(), this.token.toString());
        }
        this.lastBounced = Instant.now();
        this.isAbleToBounce = true;
    }

    public Token getToken() {
        return token;
    }


    public ArrayList<Ad> getAds() {
        return ads;
    }

    public DashboardJFrame getDashboardJFrame() {
        return dashboardJFrame;
    }

    public void setDashboardJFrame(DashboardJFrame dashboardJFrame) {
        this.dashboardJFrame = dashboardJFrame;
    }


    public boolean isAbleToBounce() {
        return isAbleToBounce;
    }

    public void setAbleToBounce(boolean ableToBounce) {
        isAbleToBounce = ableToBounce;
    }

    public Instant getLastBounced() {
        return lastBounced;
    }

    public void setLastBounced(Instant lastBounced) {
        this.lastBounced = lastBounced;
    }

    public boolean isAdsShown() {
        return adsShown;
    }

    public void setAdsShown(boolean adsShown) {
        this.adsShown = adsShown;
    }
}
