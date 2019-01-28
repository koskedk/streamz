package org.afyahmis.streamz;

import org.afyahmis.streamz.core.interfaces.ResultLoader;
import org.afyahmis.streamz.core.interfaces.ResultReader;
import org.afyahmis.streamz.core.model.Result;
import org.afyahmis.streamz.core.service.FileResultLoader;
import org.afyahmis.streamz.core.service.FileResultReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {

        LOGGER.debug("starting App");

        ResultLoader loader = new FileResultLoader();
        ResultReader reader=new FileResultReader();

        try {
            loader.load(
                    new String[]{"/Users/koskedk/Documents/labz"}
            );
        }catch (IOException e){
            LOGGER.error(e.getMessage());
        }

        try {
            List<Result> results= reader.read();
            results.forEach(System.out::println);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }

        LOGGER.debug("App completed !");
        
    }
}
