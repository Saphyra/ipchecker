package org.github.saphyra.ipchecker.checker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class IpQueryService implements IpProvider {
    private static final String URL = "http://checkip.amazonaws.com";

    public String getIp() {
        URL url = createUrl();
        InputStreamReader reader = createReader(url);
        try (BufferedReader in = new BufferedReader(reader)) {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL createUrl() {
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private InputStreamReader createReader(URL url) {
        try {
            return new InputStreamReader(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
