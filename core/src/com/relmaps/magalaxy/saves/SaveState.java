package com.relmaps.magalaxy.saves;

import com.relmaps.magalaxy.screen.GameScreen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveState {

    private File file;

    public SaveState() {
        file = new File("saves/planet.yml");
    }

    public void save() {
        HotbarState hot = new HotbarState();
        try {
            FileOutputStream f = new FileOutputStream(file);
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            o.writeObject(hot);

            o.close();
            f.close();
            System.out.printf("Inventario guardado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void load(GameScreen game) {
        try {
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream oi = new ObjectInputStream(fi);

            // Read objects
            HotbarState hot = (HotbarState) oi.readObject();

            oi.close();
            fi.close();
            hot.set(game);
            System.out.println("Hotbar cargado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean existConfig() {
        return file.exists();
    }
}
