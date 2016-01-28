package SortMachine;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import extclasses.Pdf;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class Sorter {
    public static int count = 0;
    
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
        count = textPdfFiles.size();
        return textPdfFiles;
    }

    public static void copy(String dest) throws IOException {
        if(dest.charAt(dest.length()-1) != File.separatorChar)
            dest += (File.separator);
        File destFile = new File(dest);
        if(!destFile.exists() || !destFile.isDirectory())
            destFile.mkdir();
        for (Pdf pdf : getTextPdfs()) {
            Path pathSource = Paths.get(pdf.getAbsolutePath());
            Path pathDestination = Paths.get(destFile.getAbsolutePath() +"/"+ pdf.getName());
            try {
                Files.copy(pathSource, pathDestination);
                count--;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean isSuccessfulCopy() {
        return count == 0;
    }
}
