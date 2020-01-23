package com.e.notebook_fingerprint;

import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

@RequiresApi(api = Build.VERSION_CODES.M)
public class Encryption {

    private static KeyGenerator keyGenerator;
    private static KeyGenParameterSpec keyGenParameterSpec;
    private static byte[] iv;

    public static byte[] getIv() {
        return iv;
    }

    public static void setIv(byte[] iv) {
        Encryption.iv = iv;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private static void createKey() throws NoSuchProviderException, NoSuchAlgorithmException {
        keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        try {
            keyGenParameterSpec = new KeyGenParameterSpec.Builder("notepad",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build();
        } catch (Exception e) {
            keyGenParameterSpec = new KeyGenParameterSpec.Builder("notepadAlias",
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setIsStrongBoxBacked(false)
                    .build();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static String crypt(final String textToEncrypt) throws InvalidAlgorithmParameterException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException {

        if (keyGenerator == null) {
            createKey();
        }

        keyGenerator.init(keyGenParameterSpec);
        final SecretKey secretKey = keyGenerator.generateKey();

        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        iv = cipher.getIV();

        return Arrays.toString(cipher.doFinal(textToEncrypt.getBytes("UTF-8")));
    }

    public static String encrypt(final String noteToDecrypt) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        final KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) keyStore
                .getEntry("notepad", null);
        if (secretKeyEntry == null) {
            return "";
        }

        if ((iv == null) || (noteToDecrypt.equals(""))) {
            return "";
        }

        final SecretKey secretKey = secretKeyEntry.getSecretKey();
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        final GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, spec);

        String[] split = noteToDecrypt.substring(1, noteToDecrypt.length() - 1).split(", ");
        byte[] array = new byte[split.length];
        for (int i = 0; i < split.length; i++) {
            array[i] = Byte.parseByte(split[i]);
        }

        final byte[] decodedData = cipher.doFinal(array);
        final String unencryptedString = new String(decodedData, "UTF-8");
        return unencryptedString;
    }

}