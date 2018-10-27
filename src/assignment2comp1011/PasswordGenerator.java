package assignment2comp1011;
// Jaret's password generator
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author user
 */
public class PasswordGenerator {
    
    /**
     * This will show a random salt consisting of 16 bytes 
     * @return 
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException{

            SecureRandom rng=SecureRandom.getInstanceStrong();
            byte[] salt = new byte[16];
            rng.nextBytes(salt);
            return salt;
        
    }
    /**
     * This will hash a password with a given salt return it as a String
     */
    public static String getPW(String pw, byte[] salt){
      
        String hashedPwd=null;
        try {
              
            MessageDigest md= MessageDigest.getInstance("SHA-512");
            md.update(salt);
            
            byte[] hashed=md.digest(pw.getBytes());
            
            StringBuilder sb = new StringBuilder();
            
            for(int i =0;i<hashed.length; i++){
                
               sb.append(Integer.toString((hashed[i] & 0xff)+ 0x100,16).substring(1));
            }
            hashedPwd=sb.toString();
 
        }
        catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
        }
        return hashedPwd;
    }
    
}
