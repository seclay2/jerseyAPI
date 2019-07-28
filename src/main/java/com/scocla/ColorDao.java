package com.scocla;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ColorDao {
    public List<Color> getAllColors(){

        List<Color> colorList = null;
        try {
            File file = new File("Users.dat");
            if (!file.exists()) {
                Color white = new Color(true, 16777215, 100);
                Color red = new Color(true, 16711680, 100);
                Color green = new Color(true, 65280, 100);
                Color blue = new Color(true, 255, 100);
                colorList = new ArrayList<Color>();
                colorList.add(white);
                colorList.add(red);
                colorList.add(green);
                colorList.add(blue);
                saveColorList(colorList);
            }
            else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                colorList = (List<Color>) ois.readObject();
                ois.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return colorList;
    }

    private void saveColorList(List<Color> colorList){
        try {
            File file = new File("Colors.dat");
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(colorList);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
