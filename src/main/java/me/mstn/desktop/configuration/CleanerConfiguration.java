package me.mstn.desktop.configuration;

import lombok.Getter;
import lombok.Setter;
import me.mstn.desktop.Direction;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class CleanerConfiguration {

    private String path;
    private boolean inpath;
    private Direction direction;
    private List<String> exclusions;
    private Map<String, String> resourcepack;

}
