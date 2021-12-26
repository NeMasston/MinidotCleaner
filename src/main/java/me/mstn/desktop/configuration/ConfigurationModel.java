package me.mstn.desktop.configuration;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ConfigurationModel {

    private String path;
    private boolean inpath;
    private List<String> exclusions;
    private Map<String, String> resourcepack;

}
