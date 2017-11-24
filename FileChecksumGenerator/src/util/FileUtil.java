package util;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileSystemView;

public class FileUtil {

    
    public void openScreen() throws NoSuchAlgorithmException, IOException{
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Choose the .csv file to generate checksum.");
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            String fileFullPath = selectedFile.getAbsolutePath();
            ChecksumUtil util = new ChecksumUtil();
            String salt = util.getSalt(fileFullPath);
            String checksum = util.getFileCheckSum(fileFullPath, salt);
            JLabel viewLabel = new JLabel("Generated Checksum is - ");
            JTextPane textPane = new JTextPane();
            textPane.setText(checksum);
            JPanel viewPanel = new JPanel(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.anchor = GridBagConstraints.WEST;
            constraints.insets = new Insets(10, 40, 40, 10);
            viewPanel.add(viewLabel,constraints);
            viewPanel.add(textPane,constraints);
            JFrame viewFrame = new JFrame();
            viewFrame.add(viewPanel);
            viewFrame.pack();
            viewFrame.setLocationRelativeTo(null);
            viewFrame.setVisible(true);
        }
    }
}
