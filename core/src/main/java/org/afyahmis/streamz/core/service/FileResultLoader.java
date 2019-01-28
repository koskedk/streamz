package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;


public class FileResultLoader implements ResultLoader {

    private static final Logger LOGGER = LogManager.getLogger(FileResultLoader.class);
    private final String stage = "/Users/koskedk/Projects/labz/stage";
    private final String trash = "/Users/koskedk/Projects/labz/trash";
    private final String[] locations;

    public FileResultLoader(String location) {
        this.locations = new String[]{location};
    }

    public FileResultLoader(String[] locations) {
        this.locations = locations;
    }

    public void load() throws IOException {
        for (String location : locations) {
            LOGGER.trace(String.format("reading from %s", location));
            //valid files
            List<String> files = Files
                    .list(Paths.get(location))
                    .map(f -> f.toString())
                    .collect(Collectors.toList());

            files.forEach(f -> {
                String fileName = Paths.get(f).getFileName().toString();
                String dest = String.format("%s/%s", stage, fileName);
                LOGGER.debug(String.format("Copying %s to %s",fileName,dest));
                try {
                    Files.copy(Paths.get(f), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);

                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }

            });

        }
    }

    @Override
    public void run() {
        try {
            load();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
