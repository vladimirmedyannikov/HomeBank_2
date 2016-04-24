package ru.medyannikov.homebank.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 21.04.16.
 */
public class PlayService {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public static boolean checkPlayServices(Context context) {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
               apiAvailability.getErrorDialog((Activity)context, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();

                Log.i("services", String.valueOf(resultCode));
            } else {
                Log.i("services", "This device is not supported.");
                return false;
            }
            return false;
        }
        return true;
    }
}
