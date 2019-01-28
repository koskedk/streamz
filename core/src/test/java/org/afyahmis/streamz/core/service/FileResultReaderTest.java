package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.afyahmis.streamz.core.interfaces.ResultReader;
import org.afyahmis.streamz.core.model.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileResultReaderTest {

    private ResultReader resultReader;

    private final String src = "/Users/koskedk/Documents/labz";
    private final String stage = "/Users/koskedk/Projects/labz/stage";
    private final String trash = "/Users/koskedk/Projects/labz/trash";


    @Before
    public void setUp() throws Exception {
        ResultLoader loader = new FileResultLoader(new String[]{src});
        loader.load();

        resultReader = new FileResultReader();
    }


    @After
    public void tearDown() throws Exception {

        List<String> files = Files.list(Paths.get(stage))
                .map(f -> f.toString())
                .collect(Collectors.toList());

        for (String file : files)
            Files.delete(Paths.get(file));

    }

    @Test
    public void read() {
        List<Result> results = resultReader.read();
        Assert.assertTrue(results.size() > 0);
        results.forEach(System.out::println);
    }
}