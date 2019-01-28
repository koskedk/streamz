package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.afyahmis.streamz.core.interfaces.ResultReader;
import org.afyahmis.streamz.core.interfaces.ResultWriter;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileResultWriterTest {

    private ResultWriter resultWriter;

    private final String src = "/Users/koskedk/Documents/labz";
    private final String stage = "/Users/koskedk/Projects/labz/stage";
    private final String trash = "/Users/koskedk/Projects/labz/trash";
    private final String store = "/Users/koskedk/Projects/labz/store";


    @Before
    public void setUp() throws Exception {
        ResultLoader loader = new FileResultLoader();
        loader.load(new String[]{src});

        ResultReader resultReader = new FileResultReader();
        resultWriter = new FileResultWriter(resultReader);
    }


    @After
    public void tearDown() throws Exception {
        List<String> files = Files.list(Paths.get(store))
                .map(f -> f.toString())
                .collect(Collectors.toList());
        for (String file : files)
            Files.delete(Paths.get(file));
    }

    @Test
    public void write() throws IOException {
        resultWriter.write();
        List<String> contents = Files.readAllLines(Paths.get(String.format("%s/labs.txt",store)));
        Assert.assertTrue(contents.size() > 0);
        contents.forEach(c-> System.out.println(c));
    }
}