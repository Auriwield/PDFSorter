package SortMachine;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import extclasses.Pdf;
import output.ConsoleWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class Sorter {
    static boolean a = true;
    private static final ArrayList<Pdf> textPdfFiles = new ArrayList<>();
    private static final ArrayList<Pdf> scanPdfFiles = new ArrayList<>();

    public static void parse() {
        System.out.println("Found " + FileListCreator.pdfFiles.size() + " pdf's\n");
        int i = 0;
        for (Pdf file : FileListCreator.pdfFiles) {
            System.out.printf("%d/%d files were processed", i, FileListCreator.pdfFiles.size());
            if (file.isFile()) {
                try {
                    long symbols = 0;
                    PdfReader pr = new PdfReader(file.getAbsolutePath());
                    String info = pr.getInfo().get("CreationDate");
                    file.setDate(info);
                    int pNum = pr.getNumberOfPages();
                    if(pNum < 10) {
                        for (int page = 1; page <= pNum; page++) {
                            String text = PdfTextExtractor.getTextFromPage(pr, page);
                            symbols += text.length();
                        }
                    } else {
                        for (int page = 1; page <= pNum; page+= pNum / 10) {
                            String text = PdfTextExtractor.getTextFromPage(pr, page);
                            symbols += text.length();
                        }
                    }
                    pr.close();
                    double average = symbols / (double) pNum;
                    if (average > 10)
                        file.setIsText(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("\r");
            i++;
        }
    }

    public static void find(String query) {
        query = query.replaceAll("_", " ");
        HashMap<String, HashSet<Integer>> result = new HashMap<>();
        System.out.println("Found " + FileListCreator.pdfFiles.size() + " pdf's\n");
        int i = 0;
        for (Pdf file : FileListCreator.pdfFiles) {
            System.out.printf("%d/%d files were processed", i, FileListCreator.pdfFiles.size());
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
                        if(text.contains(query)) {
                            if (result.containsKey(file.getName()))
                                    result.get(file.getName()).add(page);
                            else {
                                HashSet<Integer> list = new HashSet<>();
                                list.add(page);
                                result.put(file.getName(), list);
                            }
                        }
                    }
                    pr.close();
                    double average = symbols / (double) pNum;
                    if (average > 10)
                        file.setIsText(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("\r");
            i++;
        }
        ConsoleWriter.withdrawMapResult(result);
    }

    public static ArrayList<Pdf> getTextPdfs() {
        if(textPdfFiles.size() == 0) {
            FileListCreator.pdfFiles
                    .stream()
                    .filter(Pdf::isText)
                    .forEach(textPdfFiles::add);
        }
        return textPdfFiles;
    }

    public static ArrayList<Pdf> getScanPdfs() {
        if(scanPdfFiles.size() == 0) {
            FileListCreator.pdfFiles
                    .stream()
                    .filter(pdf -> !pdf.isText())
                    .forEach(scanPdfFiles::add);
        }
        return scanPdfFiles;
    }


    public static void copy(String dest) throws IOException {
        if (dest.charAt(dest.length() - 1) != File.separatorChar)
            dest += (File.separator);

        File destFile = new File(dest);
        if (!destFile.exists() || !destFile.isDirectory())
            a = a && destFile.mkdir();
        for (Pdf pdf : getTextPdfs()) {
            Path pathSource = Paths.get(pdf.getAbsolutePath());
            Path pathDestination = Paths.get(destFile.getAbsolutePath() + "/" + pdf.getName());
            try {
                Files.copy(pathSource, pathDestination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isSuccessfulCopy() {
        return a;
    }
}
