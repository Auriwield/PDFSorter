package output;

import SortMachine.FileListCreator;
import SortMachine.Sorter;
import extclasses.Pdf;
import main.Main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;


/**
 * Created by Auriwield on 25.01.2016.
 */
public class ConsoleWriter {
    
    public static void withdraw() {
        if(FileListCreator.pdfFiles.size() != 0) {
            System.out.printf("%-49s %-5s %-5s\n", "Name", "Kind", "Year");
            for (Pdf pdf : Sorter.getScanPdfs()) {
                System.out.printf("%-50s%-5s %-5s\n",
                        getName(pdf.getAbsolutePath()),
                        pdf.isText() ? "text" : "scan",
                        pdf.getDate()
                );
            }
        }
    }

    private static String getName(String path) {
        String subs = path.substring(Main.LENGTH);
        if (subs.length() < 50)
            return subs;
        StringBuilder sb = new StringBuilder(subs);
        int chars = subs.length() - 49;
        sb.replace(0, chars+3,"...");
        return sb.toString();
    }

    public static void withdrawNoParam() {
        System.out.println("Determine text pdf files\n");
        System.out.println("PdfSorter [drive:][path][filename] [/T | /A] [/C dir_path] [/R] [/F query]");
        System.out.printf("  %-10s%s\n",
                "/T",
                "Show only text pdf's");
        System.out.printf("  %-10s%s\n",
                "/A",
                "Show all pdf's");
        System.out.printf("  %-10s%s\n",
                "/C",
                "Copy text pdf's to your directory");
        System.out.printf("  %-10s%s\n",
                "/R",
                "rename \"file.pdf\" to \"!file.pdf\"");
        System.out.printf("  %-10s%s\n",
                "/F",
                "find pdf's with your query");

    }

    public static void withdrawTextPdfs() {
        if(FileListCreator.pdfFiles.size() != 0) {
            System.out.printf("%-49s %-10s %-5s\n", "Name", "Kind", "Year");
            Sorter.getTextPdfs()
                    .stream()
                    .forEach(pdf -> System.out.printf(
                            "%-50s%-10s %-5s\n",
                            getName(pdf.getAbsolutePath()),
                            pdf.isText() ? "text" : "scan",
                            pdf.getDate())
            );
        }
    }

public static void withdrawAll() {
        if(FileListCreator.pdfFiles.size() != 0) {
            System.out.printf("%-49s %-10s %-5s\n", "Name", "Kind", "Year");
            FileListCreator.pdfFiles
                    .stream()
                    .forEach(pdf -> System.out.printf(
                            "%-50s%-10s %-5s\n",
                            getName(pdf.getAbsolutePath()),
                            pdf.isText() ? "text" : "scan",
                            pdf.getDate())
            );
        }
    }

public static void withdrawMapResult(Map<String, HashSet<Integer>> result) {
    if(result.size() == 0)
        System.out.println("No matches found");
    for (Map.Entry<String, HashSet<Integer>> pair : result.entrySet()) {
        System.out.println(pair.getKey() + " " + pair.getValue());
    }
}

}