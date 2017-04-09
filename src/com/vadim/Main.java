package com.vadim;

import com.vadim.utils.TextGenerator;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        TextGenerator textGenerator = new TextGenerator();
        for (int i = 0; i < 10; i++){
            String fileName = "files/file" + i;
            textGenerator.start(fileName);
        }

        if (args.length > 0){
            for (String arg :
                    args) {
                FileReader fileReader = new FileReader(arg);
                new ReadingThread(fileReader, arg).startRead();
            }
        } else {
            File folder = new File("files");
            for (File file :
                    folder.listFiles()) {
                FileReader fileReader = new FileReader(file);
                new ReadingThread(fileReader, file.getName()).startRead();
            }
        }
        
    }
}
