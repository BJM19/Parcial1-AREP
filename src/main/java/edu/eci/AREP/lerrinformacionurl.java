package edu.eci.AREP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class lerrinformacionurl {
    public static void main(String[] args) throws Exception {
    }

    public static String consultar(String pagina) throws MalformedURLException {
        URL site = new URL(pagina);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(site.openStream()))) {
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                pagina = pagina + inputLine;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return pagina;
    }
}

