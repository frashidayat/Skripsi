/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author LENOVO
 */
import java.io.File;
import javax.swing.filechooser.*;

public class FileTypeFilter extends FileFilter{
    
    private String extension;
    private String description;
    
    public FileTypeFilter(String ext, String desc){
        extension = ext;
        description = desc;
    }
    
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }else{
            return f.getName().endsWith(extension);
        }
    }

    @Override
    public String getDescription() {
        return description + String.format(" (*%s)", extension);
    }

    public String getExtension() {
        return extension;
    }
    
    
}
