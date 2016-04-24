package ru.medyannikov.homebank.data.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 21.04.16.
 */
public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegistrationIntentService";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String GCM_TOKEN = "gcmToken";
    private static final String[] TOPICS = {"global"};
    public static final String REGISTRATION_COMPLETE = "registrationComplete";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences preferences = AndroidApplication.getSharedPreferences();

        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
        try {
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);

            preferences.edit().putString(GCM_TOKEN, token).apply();
            Log.d(TAG, "GCM Registration token:" + token);
            sendRegistrationToServer(token);
        }
        catch (IOException e){
            preferences.edit().putBoolean(SENT_TOKEN_TO_SERVER, false).apply();
            e.printStackTrace();
        }
        Intent registrationComplete = new Intent(REGISTRATION_COMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendRegistrationToServer(String token){
        AndroidApplication.getSharedPreferences().edit().putBoolean(SENT_TOKEN_TO_SERVER, true).apply();
    }

    private void subscripteTopics(String token) throws IOException{
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS){
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
