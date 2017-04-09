package com.vadim;

import com.vadim.utils.TextGenerator;

import java.io.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        TextGenerator textGenerator = new TextGenerator(5, 6, 3000);
        for (int i = 0; i < 10; i++){
            String fileName = "files/file" + i;
            textGenerator.start(fileName);
        }

        if (args.length > 0){
            for (String arg :
                    args) {
                new ReadingThread(new FileReader(arg), arg).startRead();
            }
        } else {
            File folder = new File("files");
            for (File file :
                    folder.listFiles()) {
                new ReadingThread(new FileReader(file), file.getName()).startRead();
            }
        }
    }
}
