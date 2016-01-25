package output;

import SortMachine.FileListCreator;
import extclasses.Pdf;


/**
 * Created by Auriwield on 25.01.2016.
 */
public class ConsoleWriter {

    public static void withdraw() {
        System.out.println(String.format("%-49s %-5s %-11s%-5s", "Name", "Kind", "Month", "Year"));
        for (Pdf pdf : FileListCreator.pdfFiles) {
            System.out.println(String.format("%-50s%-5s %-11s%-5s",
                    pdf.getName(),
                    pdf.isText() ? "text" : "scan",
                    pdf.getDate().split(" ")[0],
                    pdf.getDate().split(" ")[1]));
        }
        /*
        try {
            for (Pdf pdf : FileListCreator.pdfFiles) {
                PdfReader pr = new PdfReader(pdf.getAbsolutePath());
                for (Map.Entry<String, String> pair : pr.getInfo().entrySet()) {
                    System.out.println(pair.getKey()+ "\t" + pair.getValue());
                }
                System.out.println("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

}
