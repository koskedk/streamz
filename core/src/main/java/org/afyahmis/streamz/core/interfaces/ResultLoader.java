package org.afyahmis.streamz.core.interfaces;

import java.io.IOException;

public interface ResultLoader {
    void load(String[] locations) throws IOException;
}
