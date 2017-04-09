package com.vadim.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by vadim on 09.04.2017.
 */
public class TextGenerator {
    private int minChars;
    private int maxChars;
    private int wordsCount;
    private String fileName;
    private Random random = new Random();
    private FileWriter fileWriter;
    private BufferedWriter bw;

    public TextGenerator() {
        this(4,8, 10000);
    }

    public TextGenerator(int minChars, int maxChars, int wordsCount){
        this.minChars = minChars;
        this.maxChars = maxChars;
        this.wordsCount = wordsCount;
    }

    public void start(String fileName){
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < wordsCount; i++)
                bw.write(generateWord(minChars, maxChars) + "  ");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                System.out.printf("Сгенерирован набор слов в файл %s \r\n", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    String generateWord(int minChars, int maxChars){
        String s = "";
        int n = random.nextInt(maxChars - minChars + 1) + minChars;
        for (int i = 0; i < n; i++){
            s += generateChar();
        }
        return s;
    }

    char generateChar(){
        int n = random.nextInt(0x440 - 0x410) + 0x410;
        return (char)n;
    }

}
