/* 
 *File info : Encrypt the user password.
 *File History
 *----------------------------------------------------
 *date		  index	      name	       info
 *----------------------------------------------------
 *20150608   13208221	 Ishantha	  created.
 *----------------------------------------------------
 */ 
package com.xcoders.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.persistence.Entity;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity
  
public class EncryptPassword {
    private static final int ITERATIONS = 1000;
    private static final int KEY_LENGTH = 192;
  
    public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException{
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();
        
        PBEKeySpec spec = new PBEKeySpec(passwordChars,saltBytes,ITERATIONS,KEY_LENGTH);
        SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = key.generateSecret(spec).getEncoded();
        return String.format("%x", new BigInteger(hashedPassword));
    }
  
    public static void main(String[] args) throws Exception{    
        //System.out.println(hashPassword("password", "salt"));             
    }
}