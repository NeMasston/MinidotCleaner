package me.mstn.desktop.util;

import lombok.experimental.UtilityClass;
import me.mstn.desktop.MinidotCleaner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*
*
* Мне стыдно за каждую написанную здесь строчку...
*
* */

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

            while((entry = zipInputStream.getNextEntry()) != null){
                String name = entry.getName();
                if (name.startsWith("assets/minidot/")) {
                    String[] subpaths = name.split("/");

                    if (subpaths.length == 4) {
                        if (whiteList.contains(subpaths[2]) && subpaths[3].endsWith(".png")) {
                           //subpaths[2] + "/" + subpaths[3].replace(".png", "")
                            modelsList.add(entry);
                        }
                    }
                }
            }

            zipInputStream.close();
        } catch (FileNotFoundException e) {
            MinidotCleaner.getLogger().severe("Failed to locate extra.jar file. Error: " + e.getMessage());
            System.exit(-1);
        } catch (IOException e) {
            MinidotCleaner.getLogger().severe("Failed to open extra.jar file. Error: " + e.getMessage());
            System.exit(-1);
        }

        return modelsList;
    }

}
