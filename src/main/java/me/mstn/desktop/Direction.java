package me.mstn.desktop;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Direction {

    MINIGAMES("\\1.8.8\\extra.jar", "\\minigames\\resourcepacks"),
    EXPLORE("\\explore\\mods\\minidot-1.0.jar", "\\explore\\resourcepacks"),
    DISCOVER("\\discover\\mods\\minidot-1.0.jar", "\\discover\\resourcepacks"),
    FLAIR("\\flair\\mods\\minidot-1.0.jar", "\\flair\\resourcepacks"),
    EMPIRE("\\empire\\mods\\minidot-1.0.jar", "\\empire\\resourcepacks"),
    WURST("\\wurst\\mods\\minidot-1.0.jar", "\\wurst\\resourcepacks"),
    HODEN("\\hoden\\mods\\minidot-1.0.jar", "\\hoden\\resourcepacks");

    private final String minidotPath;
    private final String resourcepacksPath;

}
