package Service;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by ilmaz on 07.10.16.
 */
public class Encryptor {
    public static String getHash(String password, String email){
        String emailHash = DigestUtils.md5Hex(email);
        String passwordHash = DigestUtils.md5Hex(password+emailHash);
        return passwordHash;
    }
}
