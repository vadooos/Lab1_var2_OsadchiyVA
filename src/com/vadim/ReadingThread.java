package com.vadim;

import com.vadim.exceptions.DublicateWordException;
import com.vadim.exceptions.IncorrectCharException;

import java.io.Reader;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by vadim on 08.04.2017.
 */
public class ReadingThread implements Runnable {
    private final String resourceCaption;
    private Thread t = new Thread(this);
    private final Reader reader;
    static CopyOnWriteArraySet<String> wordsSet = new CopyOnWriteArraySet<>();
    static volatile boolean abortThreads = false;

    public ReadingThread(Reader reader, String resourceCaption) {
        this.reader = reader;
        this.resourceCaption = resourceCaption;
    }

    @Override
    public void run() {
        WordReader wordReader = null;
        t:
        try {
            wordReader = new WordReader(reader);
            String word = "";
            System.out.printf("Чтение ресурса: %s...\r\n", resourceCaption);
            while ((word = wordReader.nextWord2()) != "") {
                if (abortThreads){
                    System.out.printf("Чтение ресурса %s прервано\r\n", resourceCaption);
                    break t;
                }
                if (!wordsSet.contains(word))
                    wordsSet.add(word);
                else
                    throw new DublicateWordException(word);
            }

            System.out.printf("Ресурс %s прочитан до конца\r\n", resourceCaption);

        } catch (IncorrectCharException | DublicateWordException e){
            abortThreads = true;
            e.printStackTrace();
            System.out.printf("Чтение ресурса %s прервано\r\n", resourceCaption);
        }
    }

    public void startRead(){
        t.start();
    }
}
