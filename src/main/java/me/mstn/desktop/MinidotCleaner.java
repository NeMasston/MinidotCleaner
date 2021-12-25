package me.mstn.desktop;

import lombok.Getter;
import lombok.Setter;
import me.mstn.desktop.configuration.ConfigurationModel;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;

public class MinidotCleaner {

    @Getter
    @Setter
    private static ConfigurationModel configuration;
    @Getter
    @Setter
    private static String extraJarPath;

    @Getter
    private static final Logger logger = Logger.getLogger("MinidotCleaner");
    @Getter
    private static final List<ZipEntry> modelsList = new ArrayList<>();

}
