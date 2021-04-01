/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author PC
 */
public class UploadVideo {

    String s;

    public UploadVideo() {
    }

    public String upload(File f) {
       
       
            InputStream inStream = null;
            OutputStream outStream = null;
            try {
                String name = f.getName();

                File Copyfile = new File("C:\\wamp64\\www\\PIProjet\\" + name);

                inStream = new FileInputStream(f);
                outStream = new FileOutputStream(Copyfile);

                byte[] buffer = new byte[(int) f.length()];

                int length;
                //copy the file content in bytes
                while ((length = inStream.read(buffer)) > 0) {

                    outStream.write(buffer, 0, length);

                }

                inStream.close();
                outStream.close();

                s = Copyfile.toURI().getPath();

            } catch (FileNotFoundException ex) {
                System.out.println(ex);

            } catch (IOException ex) {
                System.out.println(ex);
            }
        
        return s;
    }
}
