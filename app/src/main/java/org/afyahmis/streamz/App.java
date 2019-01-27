package org.afyahmis.streamz;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.afyahmis.streamz.core.service.FileResultLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.debug("starting App");

        ResultLoader loader = new FileResultLoader();
        try {
            loader.load(
                    new String[]{"/Users/koskedk/Documents/labz"}
            );
        }catch (IOException e){
            LOGGER.error(e.getMessage());
        }


        LOGGER.debug("App completed !");
    }
}
