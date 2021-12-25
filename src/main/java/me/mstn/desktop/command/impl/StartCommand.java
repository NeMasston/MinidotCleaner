package me.mstn.desktop.command.impl;

import me.mstn.desktop.MinidotCleaner;
import me.mstn.desktop.command.AbstractCommand;
import me.mstn.desktop.file.impl.IconFile;
import me.mstn.desktop.file.impl.OutputFile;
import me.mstn.desktop.file.impl.PackFile;

public class StartCommand implements AbstractCommand {

    @Override
    public void execute(String[] args) {
        MinidotCleaner.getLogger().info("Executing START command");

        new PackFile().create();
        new IconFile().create();
        new OutputFile().create();
    }

}
