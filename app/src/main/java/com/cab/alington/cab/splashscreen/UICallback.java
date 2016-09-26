package com.cab.alington.cab.splashscreen;



import java.util.ArrayList;

/**
 * Created by User on 7/7/2016.
 */
public interface UICallback {
    void update(String urltype, ArrayList<String> uri);
    void refresh_interface(String b, int a);

  //  void updatefcm(String urltype, ArrayList<Notification_history.ResponseBean> responseBeans);
}
