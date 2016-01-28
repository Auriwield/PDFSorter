package output;

import SortMachine.FileListCreator;
import extclasses.Pdf;


/**
 * Created by Auriwield on 25.01.2016.
 */
public class ConsoleWriter {
    
    public static void withdraw() {
        if(FileListCreator.pdfFiles.size() != 0) {
            System.out.printf("%-49s %-5s %-6s%-5s\n", "Name", "Kind", "Year", "Month");
            for (Pdf pdf : FileListCreator.pdfFiles) {
                System.out.printf("%-50s%-5s %-6s%-5s\n",
                        pdf.getName().length() < 50 ? pdf.getName() : pdf.getName().substring(0, 49),
                        pdf.isText() ? "text" : "scan",
                        pdf.getDate().split(" ")[1],
                        pdf.getDate().split(" ")[0]
                );
            }
        }
    }

    public static void withdrawNoParam() {
        System.out.println("Determine text pdf files\n");
        System.out.println("PdfSorter [drive:][path][filename] [/T | /S] [/C dir_path]");
        System.out.printf("  %-10s%s\n",
                "/T",
                "Show only text pdf's");
        System.out.printf("  %-10s%s\n",
                "/S",
                "Show only scan pdf's");
        System.out.printf("  %-10s%s\n",
                "/C",
                "Copy text pdf's to your directory");

    }

    public static void withdrawWithParam(boolean text) {
        if(FileListCreator.pdfFiles.size() != 0) {
            System.out.printf("%-49s %-10s %-10s%-5s\n", "Name", "Kind", "Month", "Year");
            FileListCreator.pdfFiles.stream().filter((pdf -> (pdf.isText() && text || !pdf.isText() && !text))).forEach(pdf -> System.out.printf(
                            "%-50s%-10s %-10s%-5s\n",
                            pdf.getName().length() < 50 ? pdf.getName() : pdf.getName().substring(0, 49),
                            pdf.isText() ? "text" : "scan",
                            pdf.getDate().split(" ")[0],
                            pdf.getDate().split(" ")[1])
            );
        }
    }

}