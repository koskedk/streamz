package org.afyahmis.streamz;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {

    private static final Logger logger= LogManager.getLogger(App.class);

    public  static void main(String[] args){
        logger.debug( "starting App");
        logger.debug("App completed !");
    }
}
