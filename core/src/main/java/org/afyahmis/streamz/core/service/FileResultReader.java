package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.afyahmis.streamz.core.interfaces.ResultReader;
import org.afyahmis.streamz.core.interfaces.ResultWriter;
import org.afyahmis.streamz.core.model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileResultReader implements ResultReader {

    private static final Logger LOGGER = LogManager.getLogger(FileResultReader.class);

    private final String stage = "/Users/koskedk/Projects/labz/stage";
    private final String trash = "/Users/koskedk/Projects/labz/trash";

    public List<Result> read() {
        List<Result> results=new ArrayList<>();

        List<String> resultFiles=new ArrayList<>();

        try (Stream<Path> files = Files.list(Paths.get(stage))) {
            resultFiles = files.map(f->f.toString()).collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        for (String file:resultFiles){
            try (Stream<String> lines = Files.lines(Paths.get(file))) {
                lines.forEach(f->{
                    results.add(Result.Create(f));
                });
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return results;
    }
}

