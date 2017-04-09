package com.vadim;

import com.vadim.exceptions.IncorrectCharException;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by vadim on 07.04.2017.
 */
public class WordReader implements Runnable {

    private final Reader reader;

    public WordReader(Reader reader) {
        this.reader = reader;
    }



    public String nextWord() throws  IncorrectCharException{
        String s = "";
        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (isAllowableChar(c))
                    s += (char)c;
                else if (isIgnoredChar(c)) {
                    if (s.length() > 0)
                        return s;
                    else
                        continue;
                }
                else throw new IncorrectCharException(c);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }
    private boolean isAllowableChar(int c){
        if ((c >= 0x410 && c < 0x450) || c == 0x451 || c == 0x401)//кириллица
            return true;
        if (c > 0x20 && c < 0x40)
            return true;
        return false;
    }

    private boolean isIgnoredChar(int c){
        if (c >= 0 && c <= 0x20)
            return true;
        else
            return false;
    }

    @Override
    public void run() {

    }
}
