package com.e.combo_fingerprint_and_loginpassword.fingerprint;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.e.combo_fingerprint_and_loginpassword.R;

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private static CancellationSignal cancellationSignal;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    public static void stopFingerAuth() {
        cancellationSignal.cancel();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update("There was an Auth Error. " + errString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Auth Failed. ", false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("Error: " + helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("You can now access the app.", true);
    }

    private void update(String message, boolean status) {
        TextView paraLabel = (TextView) ((Activity) context).findViewById(R.id.paraLabel);
        TextView paraLabel2 = (TextView) ((Activity) context).findViewById(R.id.paraLabel2);
        ImageView imageView = (ImageView) ((Activity) context).findViewById(R.id.fingerprintImage);

        paraLabel.setText(message);
        paraLabel2.setText("");

        if (status) {

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.ic_success_foreground);
            android.os.SystemClock.sleep(1300);
            Intent intent = new Intent(context, FingerprintNotebook.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);

        } else {
            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        }

    }

}