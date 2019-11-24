package com.notebook.ui.login.Encryption;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

@RequiresApi(api = Build.VERSION_CODES.M)
public class Encryption {

    private static KeyGenerator keyGenerator;
    private static boolean firstUsing;
    public static byte[] iv;
    private static KeyStore keyStore;
    private static String key;

    public Encryption() throws NoSuchProviderException, NoSuchAlgorithmException {
    }

    private static KeyGenParameterSpec keyGenParameterSpec;

    @RequiresApi(api = Build.VERSION_CODES.P)
    private static void setKey() {

        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            keyGenParameterSpec = new KeyGenParameterSpec.Builder("notepadAlias",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setIsStrongBoxBacked(true)
                    .build();
            try {
                keyGenerator.init(keyGenParameterSpec);
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            }
            final SecretKey secretKey = keyGenerator.generateKey();

        } catch (Exception e) {
            keyGenParameterSpec = new KeyGenParameterSpec.Builder("notepadAlias",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setIsStrongBoxBacked(false)
                    .build();
            try {
                keyGenerator.init(keyGenParameterSpec);
            } catch (InvalidAlgorithmParameterException ee) {
                e.printStackTrace();
            }
            final SecretKey secretKey = keyGenerator.generateKey();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static SecretKey getKey() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException {
        keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("notepadAlias", null);
        return secretKeyEntry.getSecretKey();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static byte[] encrypt(String word) {
        if (!firstUsing) {
            setKey();
            firstUsing = true;
        }
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore.getEntry("notepadAlias", null);
            final SecretKey secretKey = secretKeyEntry.getSecretKey();
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            iv = cipher.getIV();
            return cipher.doFinal(word.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static String decrypt(byte[] word, byte[] iv) {
        if (!firstUsing) {
            setKey();
            firstUsing = true;
        }
        try {
            Key aesKey = getKey();
            final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            final GCMParameterSpec spec = new GCMParameterSpec(128, iv);
            cipher.init(Cipher.DECRYPT_MODE, aesKey, spec);
            final byte[] decodedData = cipher.doFinal(word);
            return new String(decodedData, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
