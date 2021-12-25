package me.mstn.desktop.command.impl;

import me.mstn.desktop.MinidotCleaner;
import me.mstn.desktop.command.AbstractCommand;

public class ListCommand implements AbstractCommand {

    @Override
    public void execute(String[] args) {
        MinidotCleaner.getModelsList().forEach(entry -> {
            MinidotCleaner.getLogger().info(entry.getName());
        });
    }

}
