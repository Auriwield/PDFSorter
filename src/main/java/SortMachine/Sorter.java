package SortMachine;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import extclasses.Pdf;
import static java.nio.file.StandardCopyOption.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class Sorter {
    public static void sort() {
        System.out.println("Found " + FileListCreator.pdfFiles.size() + " pdf's\n");
        for (Pdf file : FileListCreator.pdfFiles) {
            if (file.isFile()) {
                try {
                    long symbols = 0;
                    PdfReader pr = new PdfReader(file.getAbsolutePath());
                    String info = pr.getInfo().get("CreationDate");
                    file.setDate(info);
                    int pNum = pr.getNumberOfPages();
                    for (int page = 1; page <= pNum; page++) {
                        String text = PdfTextExtractor.getTextFromPage(pr, page);
                        symbols += text.length();
                    }
                    double average = symbols / (double) pNum;
                    if (average > 10)
                        file.setIsText(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static ArrayList<Pdf> getTextPdfs() {
        ArrayList<Pdf> textPdfFiles = new ArrayList<>();
        FileListCreator.pdfFiles.
                stream().
                filter(Pdf::isText).
                forEach(textPdfFiles::add);
        if(textPdfFiles.size() == 0)
            System.err.println("Not found text pdf to copy");
        return textPdfFiles;
    }

    public static void copy(String dest) throws IOException {
        //todo: rewrite this method(it doesn't work)
        File destFile = new File(dest);
        boolean b = true;
        if (!destFile.exists()) {
            try {
                b = destFile.mkdir();
                if (!b)
                    System.err.println("directory wasn't created");
            } catch (SecurityException se) {
                System.err.println("Haven't permission to create a dir");
            }
        }
        if(b && destFile.isDirectory()) {
            for (Pdf pdf : getTextPdfs())
                Files.copy(pdf.toPath(), destFile.toPath(), REPLACE_EXISTING);
        }
    }
}
