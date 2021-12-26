package me.mstn.desktop.file.impl;

import me.mstn.desktop.MinidotCleaner;
import me.mstn.desktop.file.AbstractFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.logging.Level;

public class PackFile implements AbstractFile {

    private final String fileName = "pack.mcmeta";
    private final String text;

    public PackFile() {
        Map<String, String> resourcepack = MinidotCleaner.getConfiguration().getResourcepack();
        text = "{\"pack\":{\"pack_format\":1,\"description\":\"" + resourcepack.get("description") + "\"}}";
    }

    public String create() {
        MinidotCleaner.getLogger().info("Generating pack.mcmeta");

        PrintWriter writer = null;
        File file = new File(fileName);

        try {
            writer = new PrintWriter(fileName, "UTF-8");
            writer.println(text);
            writer.close();
        } catch (FileNotFoundException e) {
            file.delete();
            MinidotCleaner.stop(Level.SEVERE, "Failed to locate " + fileName + " file. Error: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            file.delete();
            MinidotCleaner.stop(Level.SEVERE, fileName + " using unsupported encoding. Error: " + e.getMessage());
        }

        file.deleteOnExit();

        return fileName;
    }

}
