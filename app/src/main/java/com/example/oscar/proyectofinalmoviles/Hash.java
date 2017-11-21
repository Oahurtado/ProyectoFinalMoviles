package com.example.oscar.proyectofinalmoviles;

/**
 * Created by Edwin on 21/11/2017.
 */

public class Hash {


    public static String getHash(String txt, String hashType) {
        String cifrado;
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            cifrado=e.getMessage();
            System.out.println(cifrado);
        }
        return cifrado;
    }

    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return Hash.getHash(txt, "MD5");
    }
}
