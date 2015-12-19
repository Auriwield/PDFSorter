package SortMachine;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dinozavrik on 19.12.2015.
 */
public class GetFileList {
    public static File[] paths;
    static void createFileList(String a) throws IOException {
        File f = null;

        try{
            f = new File(a);
            paths = f.listFiles();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
