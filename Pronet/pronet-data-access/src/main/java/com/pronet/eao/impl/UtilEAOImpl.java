package com.pronet.eao.impl;

import com.personal.util.Dictionary;
import com.pronet.eao.UtilEAO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by prith on 11-06-2017.
 */
@Service("utilEAO")
public class UtilEAOImpl implements UtilEAO {
    Dictionary dictionary = null;
    public List<String> getSuffixes(String prefix) {
        try {
            if(this.dictionary == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.getClass().getResourceAsStream("/words.txt"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                Dictionary dictionary = new Dictionary();
                while ((line = bufferedReader.readLine()) != null) {
                    dictionary.insert(line);
                }
                this.dictionary = dictionary;
            }
            return dictionary.getWordStartingFrom(prefix);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
