/**
 * 
 */
package main;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import util.FileUtil;

/**
 * @author we43mm
 *
 */
public class MainLauncher {

    /**
     * @param args
     */
    private static final FileUtil fileUtil = new FileUtil();
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        
        fileUtil.openScreen();
    }

}
