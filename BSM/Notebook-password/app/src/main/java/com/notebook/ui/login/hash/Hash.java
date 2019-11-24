package com.notebook.ui.login.hash;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String getHash(final String text)  {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance( "SHA-256" );
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update( text.getBytes( StandardCharsets.UTF_8 ) );
        byte[] digest = md.digest();
        String hex = String.format( "%064x", new BigInteger( 1, digest ) );
        return hex;
    }

}
