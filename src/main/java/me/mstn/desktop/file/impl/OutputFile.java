package me.mstn.desktop.file.impl;

import me.mstn.desktop.MinidotCleaner;
import me.mstn.desktop.file.AbstractFile;
import sun.misc.IOUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class OutputFile implements AbstractFile {

    private final String fileName;

    public OutputFile() {
        Map<String, String> resourcepack = MinidotCleaner.getConfiguration().getResourcepack();
        String output = resourcepack.get("output");

        if (!output.endsWith(".zip")) output += ".zip";

        fileName = output;
    }

    public String create() {
        MinidotCleaner.getLogger().info("Creating resourcepack");

        try {
            List<String> srcFiles = Arrays.asList("pack.mcmeta", "pack.png");
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);;
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

            File fileToZip = null;
            for (String srcFile : srcFiles) {
                fileToZip = new File(srcFile);
                FileInputStream fileInputStream = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                zipOutputStream.putNextEntry(zipEntry);

                byte[] bytes = new byte[1024];
                int length;
                while((length = fileInputStream.read(bytes)) >= 0) {
                    zipOutputStream.write(bytes, 0, length);
                }
                fileInputStream.close();
            }

            Iterator entries = new HashSet<>(MinidotCleaner.getModelsList()).iterator();

            while (entries.hasNext()) {
                ZipEntry entry = (ZipEntry)entries.next();
                ZipEntry zipEntry = new ZipEntry(entry.getName());

                if (!MinidotCleaner.getConfiguration().getExclusions().contains(entry.getName())) {
                    zipOutputStream.putNextEntry(zipEntry);
                    InputStream nullImage = getClass().getClassLoader().getResourceAsStream("null.png");
                    byte[] data = IOUtils.readNBytes(nullImage, nullImage.available());
                    zipOutputStream.write(data, 0, data.length);
                    zipOutputStream.closeEntry();
                }
            }

            zipOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            MinidotCleaner.getLogger().severe("Failed to create " + fileName + " file. Error: " + e.getMessage());
            System.exit(-1);
        }

        return fileName;
    }

}
