package SortMachine;
import java.io.*;

/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class Sorter extends GetFileList {

    public int imageCounter = 0;
    public  int textCounter = 0;
    String badPDF;
    String goodPDF;
  //  public  int objCounter = 0;

    public void sort(String dirPath) throws IOException{
        createFileList(dirPath);
        String r = "";
        createDirectory(dirPath);
        System.out.println(badPDF);
        System.out.println(goodPDF);
        int m;
        for(File file:paths) {
            if (file.isFile()) {
                finder(file);
                m = file.toString().lastIndexOf('\\');
                r = file.toString().substring(m + 1);
                textCounter -= 3;
                System.out.println("---------");
                System.out.println(r);
                System.out.println("images " + imageCounter);
                System.out.println("text " + textCounter);

                if(imageCounter > textCounter*3/2)
                    file.renameTo(new File(badPDF, file.getName()));
                else
                    file.renameTo(new File(goodPDF, file.getName()));
                // System.out.println("objects " + objCounter);
                imageCounter = 0;
                textCounter = 0; //objCounter = 0;
            }
        }

    }

     void finder(File path) throws IOException{
        BufferedReader inputStream = new BufferedReader(new FileReader(path));
        String a = "";
        while(inputStream.ready()) {
            a = inputStream.readLine();
            if (a.contains("/Image/" )) imageCounter++;
            else if (a.contains("/Image /")) imageCounter++;
            else if (a.contains("/Filter")) textCounter++;
            else if (a.contains("/FlateDecode")) textCounter++;
            else if (a.contains("/ASCIIHexDecode")) textCounter++;
           // if (a.contains("endobj")) objCounter++;
        }
        inputStream.close();
    }

     void createDirectory(String dirPath) {
        badPDF = dirPath.concat("\\BadPDF\\");
        goodPDF = dirPath.concat("\\GoodPdf\\");
        File badP = new File(badPDF);
        File goodP = new File(goodPDF);
        boolean l = badP.mkdir();
        boolean p = goodP.mkdir();
    }
}
