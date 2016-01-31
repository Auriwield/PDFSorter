package main;

import SortMachine.FileListCreator;
import extclasses.ArgumentParser;
import output.ConsoleWriter;


import java.io.IOException;



/**
 * Created by Auriwield on 24.01.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String arg = reader.readLine();
        reader.close();
        if (arg.length() == 0)
            ConsoleWriter.withdrawNoParam();
        args = arg.split(" ");*/
        if (args.length == 0)
            ConsoleWriter.withdrawNoParam();
        else {
            FileListCreator creator = new FileListCreator(args[0]);
            creator.createFileList();
            ArgumentParser parser = ArgumentParser.getInstance();
            parser.parseArgs(args);
        }
    }
}

