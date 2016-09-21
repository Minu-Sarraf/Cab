package com.cab.alington.cab.splashscreen.FCM;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.ArrayList;

/**
 * Created by User on 6/27/2016.
 */
public class  MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
//Lets see now abc
        private static final String TAG = "MyFirebaseIIDService";
        @Override
        public void onTokenRefresh() {
            //Getting registration token
            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            //Displaying token on logcat
            Log.e("TOKEN", "Refreshed token: " + refreshedToken);

          //  sendRegistrationToServer(refreshedToken);
          /*  if(UsefulInfo.getImeiId()!=null){
                sendRegistrationToServer(refreshedToken);
                //eEnQdTiM19E:APA91bF23T_pz6YG0K6S7leAtFkm9tkQX8e941lRTmPPPmnYYi5FCIFesBzwcn6DFZ39KHfci5ZzDln-Y2NQOxD9J9zrZ8apu2Mf6OjTInbGO-2fpyuhbVvGF8LtjQqelA3PlHbss_cK
            }else{
                TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String deviceId = telephonyManager.getDeviceId();
                UsefulInfo.setImeiId(deviceId);
               sendRegistrationToServer(refreshedToken);
            }*/
        }
        private void sendRegistrationToServer(String token) {
           // HttpResponse.listener("fcm", Constants.fcm, UsefulInfo.addpostdata("none").add("regid",token).build(), this);
        }


}

