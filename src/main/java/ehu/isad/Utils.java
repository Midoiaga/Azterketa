package ehu.isad;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.Properties;

public class Utils {

    public static Properties lortuEzarpenak()  {
        Properties properties = null;

        try (InputStream in = Utils.class.getResourceAsStream("/setup.properties")) {
            properties = new Properties();
            properties.load(in);

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
    public static String checkHashURL(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream is = new URL(input).openStream();

            try {
                is = new DigestInputStream(is, md);

                while (is.read() != -1) {

                }
            } finally {
                is.close();
            }
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < digest.length; i++) {
                sb.append(
                        Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(
                                1));
            }
            return sb.toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
