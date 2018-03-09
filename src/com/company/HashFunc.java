package com.company;

import java.security.MessageDigest;

public class HashFunc {
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] byteBuffer = digest.digest(input.getBytes("UTF-8"));

            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < byteBuffer.length; i++) {
                String hexString = Integer.toHexString(0xff & byteBuffer[i]);
                if(hexString.length() == 1) stringBuffer.append('0');
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
