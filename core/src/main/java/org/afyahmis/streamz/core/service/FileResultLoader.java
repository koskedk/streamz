package org.afyahmis.streamz.core.service;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FileResultLoader implements ResultLoader {

    private static final Logger logger = LogManager.getLogger(FileResultLoader.class);

    public void load(String[] locations) {
        for (String location : locations) {
            logger.trace(String.format("reading from %s", location));
        }
    }
}
