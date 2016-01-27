package SortMachine;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import extclasses.Pdf;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class Sorter {
    public static void sort() {
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

    public static void copy(String path) throws FileNotFoundException {
        //todo: implement this method
        throw new FileNotFoundException();
    }
}
