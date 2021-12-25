package me.mstn.desktop.file.impl;

import me.mstn.desktop.MinidotCleaner;
import me.mstn.desktop.file.AbstractFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;

public class IconFile implements AbstractFile {

    private String fileName;

    public IconFile() {
        Map<String, String> resourcepack = MinidotCleaner.getConfiguration().getResourcepack();
        String icon = resourcepack.get("icon");

        if (!icon.endsWith(".png")) icon += ".png";

        fileName = icon;
    }

    @Override
    public String create() {
        MinidotCleaner.getLogger().info("pack.png icon generating");

        File file = new File("pack.png");
        boolean delete = true;

        if (!file.exists()) {
            try {
                copyFile(
                        new File(fileName),
                        file
                );
            } catch (IOException e) {
                file.delete();
                MinidotCleaner.getLogger().severe("Failed to copy " + fileName + " file. Error: " + e.getMessage());
                System.exit(-1);
            }

            fileName = "pack.png";
        } else delete = false;

        try {
            BufferedImage bufferedImage = ImageIO.read(file);

            if (bufferedImage.getHeight() != 64 || bufferedImage.getWidth() != 64) {
                if (delete) file.delete();
                MinidotCleaner.getLogger().severe("Failed to use icon, it must be 64x64!");
                System.exit(-1);

                return fileName;
            }
        } catch (IOException e) {
            if (delete) file.delete();
            MinidotCleaner.getLogger().severe("Failed to read " + fileName + " temp file. Error: " + e.getMessage());
            System.exit(-1);
        }

        file.deleteOnExit();

        return fileName;
    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }

}
