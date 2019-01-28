package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class FileResultLoaderTest {
    private int processors;
    private ResultLoader loader;
    private final String src = "/Users/koskedk/Documents/labz";
    private final String src2 = "/Users/koskedk/Documents/labz2";
    private final String src3 = "/Users/koskedk/Documents/labz3";
    private final String stg = "/Users/koskedk/Projects/labz/stage";
    private final String trs = "/Users/koskedk/Projects/labz/trash";

    @Before
    public void setUp() throws Exception {
        processors = Runtime.getRuntime().availableProcessors();
        loader = new FileResultLoader(new String[]{src});
        System.out.println(String.format(" Processers %d", processors));
    }

    @After
    public void tearDown() throws Exception {

        List<String> files = Files.list(Paths.get(stg))
                .map(f -> f.toString())
                .collect(Collectors.toList());

        for (String file : files)
            Files.delete(Paths.get(file));
    }

    @Test
    public void load() throws IOException {
        loader.load();

        List<String> files = Files.list(Paths.get(stg))
                .map(f -> f.toString())
                .collect(Collectors.toList());

        Assert.assertTrue(files.size() > 0);
        files.forEach(System.out::println);
    }

    @Test
    public void load_async() throws IOException {

        ExecutorService es = Executors.newFixedThreadPool(processors);

        String[] srcs = new String[]{src, src2, src3};
        for (String loc : srcs) {
            FileResultLoader resultLoader = new FileResultLoader(loc);
            es.submit(resultLoader);
        }

        try {
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> files = Files.list(Paths.get(stg))
                .map(f -> f.toString())
                .collect(Collectors.toList());

        Assert.assertTrue(files.size() > 0);
        files.forEach(System.out::println);
    }
}