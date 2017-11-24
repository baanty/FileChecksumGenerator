package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * This class will have file utilities of the Bulk Agent.
 */
public class ChecksumUtil {

    public String getCheckSum(String inputCsvFile) throws IOException, NoSuchAlgorithmException {
        String salt = getSalt(inputCsvFile);
        return getFileCheckSum(inputCsvFile, salt);
    }

    
    public String getSalt(String fileName) throws IOException, NoSuchAlgorithmException {
        File inputCsvFile = new File(fileName);
        String fileSizeInString = String.valueOf(Files.size(Paths.get(inputCsvFile.getPath())));
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(fileSizeInString.getBytes());
        byte[] digestedBytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< digestedBytes.length ;i++)
        {
            sb.append(Integer.toString((digestedBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
       return sb.toString();

    }

    public String getFileCheckSum(String fileName, String salt) throws IOException, NoSuchAlgorithmException {
        File inputCsvFile = new File(fileName);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(Files.readAllBytes(Paths.get(inputCsvFile.getPath())));
        md.update(salt.getBytes());
        byte[] digestedBytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< digestedBytes.length ;i++)
        {
            sb.append(Integer.toString((digestedBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
       return sb.toString();
    }
}
