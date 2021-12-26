package me.mstn.desktop.util;

import lombok.experimental.UtilityClass;
import me.mstn.desktop.MinidotCleaner;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@UtilityClass
public class ModelUtility {

    private final List<String> whiteList = new ArrayList<String>() {{
        add("pets");
        add("head");
        add("masks");
        add("body");
    }};

    public List<ZipEntry> getModelsList(String fullPath) {
        MinidotCleaner.getLogger().info("Loading Minidot models");
        List<ZipEntry> modelsList = new ArrayList<>();

        FileInputStream fileInputStream = null;
        ZipInputStream zipInputStream = null;
        ZipEntry entry = null;

        try {
            fileInputStream = new FileInputStream(fullPath);
            zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));

            while ((entry = zipInputStream.getNextEntry()) != null) {
                String name = entry.getName();
                if (name.startsWith("assets/minidot/")) {
                    String[] subpaths = name.split("/");

                    if (subpaths.length == 4) {
                        if (whiteList.contains(subpaths[2]) && subpaths[3].endsWith(".png")) {
                            modelsList.add(entry);
                        }
                    }
                }
            }

            zipInputStream.close();
        } catch (FileNotFoundException e) {
            MinidotCleaner.stop(Level.SEVERE, "Failed to locate extra.jar file. Error: " + e.getMessage());
        } catch (IOException e) {
            MinidotCleaner.stop(Level.SEVERE, "Failed to open extra.jar file. Error: " + e.getMessage());
        }

        return modelsList;
    }

}
