package dev._2lstudios.prismatrade.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class Configuration extends YamlConfiguration {
    private String raw;
    private File file;
    private Configuration failover;

    public Configuration(String raw) {
        this.raw = raw;
    }
    
    public Configuration(File file) {
        this.file = file;
    }

    public Configuration(File file, Configuration failover) {
        this.file = file;
        this.failover = failover;
    }

    /* Utils methods */
    public void load() throws FileNotFoundException, IOException, InvalidConfigurationException {
        if (this.raw != null) {
            this.load(this.raw);
        } else {
            this.load(this.file);
        }
    }

    public void save() throws IOException {
        this.save(this.file);
    }

    public void safeSave() {
        try {
            this.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Fail-proof methods */
    public void setIfNotExist(String path) {
        if (!this.contains(path) && this.failover != null) {
            Object candidate = this.failover.get(path);
            if (candidate != null) {
                this.set(path, candidate);
                this.safeSave();
            }
        }
    }

    /* Primitive object get */
    public int getSafeInt(final String path) {
        this.setIfNotExist(path);
        return this.getInt(path);
    }

    public int getSafeString(final String path) {
        this.setIfNotExist(path);
        return this.getInt(path);
    }

    public int getSafeBoolean(final String path) {
        this.setIfNotExist(path);
        return this.getInt(path);
    }

    public List<String> getStringList(final String path) {
        this.setIfNotExist(path);
        return super.getStringList(path);
    }
}