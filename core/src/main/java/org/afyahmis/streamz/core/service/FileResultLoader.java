package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class FileResultLoader implements ResultLoader {

    private static final Logger LOGGER = LogManager.getLogger(FileResultLoader.class);
    private final String stage = "/Users/koskedk/Projects/labz/stage";
    private final String trash = "/Users/koskedk/Projects/labz/trash";

    public void load(String[] locations) throws IOException {
        for (String location : locations) {
            LOGGER.trace(String.format("reading from %s", location));
            //valid files
            List<String> files = Files
                    .list(Paths.get(location))
                    .map(f -> f.toString())
                    .collect(Collectors.toList());

            files.forEach(f->{
                String fileName=Paths.get(f).getFileName().toString();
                String dest=String.format("%s/%s",stage,fileName);
                try {
                    Files.copy(Paths.get(f),Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
                }catch (Exception e)
                {
                    LOGGER.error(e.getMessage());
                }

            });

        }
    }
}
