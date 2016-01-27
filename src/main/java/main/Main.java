package main;

import SortMachine.FileListCreator;
import SortMachine.Sorter;
import output.ConsoleWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Auriwield on 24.01.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0)
            ConsoleWriter.withdrawNoParam();
        else {
            ArrayList<String> argsList = new ArrayList<>(5);
            for (String arg : args)
                argsList.add(arg.toLowerCase());
            FileListCreator creator = new FileListCreator(args[0]);
            creator.createFileList();
            Sorter.sort();
            if (argsList.contains("/C".toLowerCase())) {
                int count = -1;
                for (int i = 0; i < args.length; i++) {
                    if (args[i].equalsIgnoreCase("/C"))
                        count = i + 1;
                }
                try {
                    String path = args[count];
                    Sorter.copy(path);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Incorrect '/C' path");
                } catch (FileNotFoundException e) {
                    System.err.println("dir. path is incorrect");
                }
            }

            if (argsList.contains("/T".toLowerCase()))
                ConsoleWriter.withdrawWithParam(true);
            else if (argsList.contains("/S".toLowerCase()))
                ConsoleWriter.withdrawWithParam(false);
            else
                ConsoleWriter.withdraw();
        }
    }
//new File(".").getAbsolutePath();
}

