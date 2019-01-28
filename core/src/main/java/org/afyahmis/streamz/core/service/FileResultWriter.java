package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultReader;
import org.afyahmis.streamz.core.interfaces.ResultWriter;
import org.afyahmis.streamz.core.model.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileResultWriter implements ResultWriter {
    private static final Logger LOGGER = LogManager.getLogger(FileResultWriter.class);
    private final String store = "/Users/koskedk/Projects/labz/store/labs.txt";
    private final String stage = "/Users/koskedk/Projects/labz/stage";
    private final ResultReader resultReader;

    public FileResultWriter(ResultReader resultReader) {
        this.resultReader = resultReader;
    }

    public void write() {

        List<Result> results = resultReader.read();

        try (FileWriter writer = new FileWriter(store); PrintWriter printWriter = new PrintWriter(writer)) {
            results.forEach(r ->printWriter.printf("%s\r\n", r.getPrintOut()));
        } catch (IOException e) {
            LOGGER.error(e);
        }

    }

    private void cleanUp() throws IOException {
        List<String> files = Files.list(Paths.get(stage))
                .map(f -> f.toString())
                .collect(Collectors.toList());

        for (String file : files)
            Files.delete(Paths.get(file));

    }
}
