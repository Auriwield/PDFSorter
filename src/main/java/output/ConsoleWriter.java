package output;

import SortMachine.FileListCreator;
import extclasses.Pdf;


/**
 * Created by Auriwield on 25.01.2016.
 */
public class ConsoleWriter {

    public static void withdraw() {
        System.out.println(String.format("%-49s %-5s %-6s%-5s", "Name", "Kind", "Year", "Month"));
        for (Pdf pdf : FileListCreator.pdfFiles) {
            System.out.println(String.format("%-50s%-5s %-6s%-5s",
                    pdf.getName(),
                    pdf.isText() ? "text" : "scan",
                    pdf.getDate().split(" ")[1],
                    pdf.getDate().split(" ")[0]
                    ));
        }
    }

    public static void withdrawNoParam() {
        System.out.println("Determine text pdf files\n");
        System.out.println("PdfSorter [drive:][path][filename] [/T | /S] [/C dir_path]");
        System.out.println(String.format("  %-10s%s",
                "/T",
                "Show only text pdf's"));
        System.out.println(String.format("  %-10s%s",
                "/S",
                "Show only scan pdf's"));
        System.out.println(String.format("  %-10s%s",
                "/C",
                "Copy text pdf's to your directory"));

    }

    public static void withdrawWithParam(boolean text) {
        System.out.println(String.format("%-49s %-10s %-10s%-5s", "Name", "Kind", "Month", "Year"));
        for (Pdf pdf : FileListCreator.pdfFiles) {
            if (text && pdf.isText() || !text && !pdf.isText()) {
                System.out.println(String.format("%-50s%-10s %-10s%-5s",
                        pdf.getName(),
                        pdf.isText() ? "text" : "scan",
                        pdf.getDate().split(" ")[0],
                        pdf.getDate().split(" ")[1]));
            }
        }
    }

}