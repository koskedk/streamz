package org.afyahmis.streamz.core.interfaces;

import java.io.IOException;

public interface ResultLoader extends Runnable {
    void load() throws IOException;
}
