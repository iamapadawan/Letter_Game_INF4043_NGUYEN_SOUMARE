package fr.esiea.unique.binome.nguyensoumare.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class Dictionary implements IDictionary {

    private HashMap<String, String> dictionary;

    public Dictionary() {

        dictionary = new HashMap<String, String>();

        String filepath = System.getProperty("user.dir");
        filepath += "\\src\\main\\resources\\dico.txt";
        File file = new File(filepath);

        if (file.exists() == true) {
            System.out.println("true");
        }

        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String ligne = null;
            while ((ligne = r.readLine()) != null) {
                if (ligne.length() > 1) {
                    String key = ligne;
                    key = key.replaceAll("�|�", "a");
                    key = key.replaceAll("�|�", "o");
                    key = key.replaceAll("��", "u");
                    key = key.replaceAll("�", "i");
                    key = key.replaceAll("�|�", "e");
                    key = key.replaceAll("-", "");
                    key = key.replaceAll(" ", "");

                    dictionary.put(key, ligne);
                }

            }
            r.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TOD1O Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public boolean isAword(String word) {

        if (dictionary.get(word) != null) {

            return true;

        }
        return false;

    }

    public HashMap<String, String> getDictionary() {
        return dictionary;
    }

}
