import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class KeyGenerator {
    public byte[] generatedKey;
    public String genKey;

    KeyGenerator() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            generatedKey = secureRandom.generateSeed(16);
            secureRandom.nextBytes(generatedKey);
            StringBuilder sb = new StringBuilder();
            for(byte b: generatedKey){
                sb.append(String.format("%02X", b));
            }
            genKey = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("Failed to crate secret key", ex);
        }
    }

    public byte[] calcHmacSha256(byte[] key, byte[] message){
        byte[] hmacSha256 = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "HmacSHA256");
            mac.init(secretKeySpec);
            hmacSha256 = mac.doFinal(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate hmac-sha256", e);
        }
        return hmacSha256;
    }
}