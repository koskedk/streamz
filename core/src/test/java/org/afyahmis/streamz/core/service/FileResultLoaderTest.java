package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.afyahmis.streamz.core.model.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FileResultLoaderTest {

    private ResultLoader loader;
    private final String src = "/Users/koskedk/Documents/labz";
    private final String stg = "/Users/koskedk/Projects/labz/stage";
    private final String trs = "/Users/koskedk/Projects/labz/trash";

    @Before
    public void setUp() throws Exception {
        loader = new FileResultLoader();
    }

    @After
    public void tearDown() throws Exception {

        List<String> files= Files.list(Paths.get(stg))
                .map(f->f.toString())
                .collect(Collectors.toList());

        for(String file: files)
            Files.delete(Paths.get(file));
    }

    @Test
    public void load() throws IOException {
        loader.load(new String[]{src});

        List<String> files= Files.list(Paths.get(stg))
                .map(f->f.toString())
                .collect(Collectors.toList());

        Assert.assertTrue(files.size()>0);
        files.forEach(System.out::println);
    }
}