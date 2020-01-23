package com.e.notebook_fingerprint;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class MainActivity extends AppCompatActivity {

    private KeyStore keyStore;
    private Cipher cipher;
    private final String KEY_NAME = "notebook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mParaLabel = (TextView) findViewById(R.id.paraLabel);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {
                mParaLabel.setText("Fingerprint Scanner not detected in Device");
            } else if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                mParaLabel.setText("Permission not granted to use Fingerprint Scanner");
            } else if (!keyguardManager.isKeyguardSecure()) {
                mParaLabel.setText("Add Lock to your Phone in Settings");
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                mParaLabel.setText("You should add at least 1 Fingerprint to use this Feature");
            } else {
                mParaLabel.setText("Place your Finger on Scanner to Access the App.");
                createKey();
                try {
                    if (cipherInit()) {

                        FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                        FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                        fingerprintHandler.startAuth(fingerprintManager, cryptoObject);

                    }
                } catch (NoSuchPaddingException | NoSuchAlgorithmException | CertificateException | IOException | InvalidKeyException | UnrecoverableKeyException | KeyStoreException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void createKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (KeyStoreException | IOException | CertificateException
                | NoSuchAlgorithmException | InvalidAlgorithmParameterException
                | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean cipherInit() throws NoSuchPaddingException, NoSuchAlgorithmException, CertificateException, IOException, InvalidKeyException, UnrecoverableKeyException, KeyStoreException {
        cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        keyStore.load(null);
        SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME, null);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return true;
    }

}