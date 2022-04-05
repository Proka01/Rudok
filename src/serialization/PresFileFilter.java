package serialization;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class PresFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return (f.isDirectory() ||
                f.getName().toLowerCase().endsWith(".rd_pres"));
    }

    @Override
    public String getDescription() {
        return "RuDok Project Files (*.rd_pres)";
    }
}
