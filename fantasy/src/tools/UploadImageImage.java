/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author mhamdi iheb
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author PC
 */
public class UploadImageImage {
   String a;

    public UploadImageImage() {
    }

    public void saveFile(File f) {
        InputStream inStream = null;
        OutputStream outStream = null;
        String s = f.getName();
//            tfLogo.setText(s);

        File Copyfile = new File("C:\\wamp64\\www\\PIProjet\\" + s);
        try {

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
           

        } catch (IOException e) {
            System.err.println(e);

        }
        
    }

}
