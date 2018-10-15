package ipchecker.checker;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class IpQueryService {
    private final String URL = "http://checkip.amazonaws.com";

    public String getIp(){
        URL url = createUrl();
        InputStreamReader reader = createReader(url);
        try(BufferedReader in = new BufferedReader(reader)){
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL createUrl(){
        try {
            return new URL(URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private InputStreamReader createReader(java.net.URL url) {
        try {
            return new InputStreamReader(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
