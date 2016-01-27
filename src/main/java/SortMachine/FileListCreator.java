package SortMachine;

import extclasses.Pdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class FileListCreator {
    public static ArrayList<Pdf> pdfFiles = new ArrayList<>();
    private String path;

    public FileListCreator(String path) {
        this.path = path;
    }

    public void createFileList() throws IOException {
        Pdf pdfFile = new Pdf(path);
        if (!pdfFile.exists())
            throw new FileNotFoundException("File's path is incorrect");
        if (pdfFile.isFile()) {
            if (isPdfFile(pdfFile))
                pdfFiles.add(pdfFile);
        }
        else {
            File[] dir = pdfFile.listFiles();
            addFiles(dir);
        }
    }

    private static void addFiles(File[] files) {
        for (File file : files) {
            try {
                if (file.isDirectory() && !file.isHidden())
                addFiles(file.listFiles());
            if (isPdfFile(file))
                pdfFiles.add(new Pdf(file.getAbsolutePath()));
            } catch (NullPointerException ignore) {
        }
    }

    }

    private static boolean isPdfFile(File pdfFile) {
        String path = pdfFile.getPath();
        if (path.lastIndexOf('.') > 0) {
            int lastIndex = path.lastIndexOf('.');
            String type = path.substring(lastIndex);
            if (type.equals(".pdf"))
                return true;
        }
        return false;
    }
}
