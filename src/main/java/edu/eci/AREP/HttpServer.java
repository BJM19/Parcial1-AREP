package edu.eci.AREP;
import java.net.*;
import java.io.*;

public class HttpServer {


    /**
     * Clase Servidor que conecta al cliente
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String linea,outputLine = null;
        while ((linea = in.readLine()) != null) {
            System.out.println(clima(linea));
            if (linea.equals("")) {
                System.out.println("Terminado");
                break;
            } else {
                System.out.println("Entrada:" + linea);
            }

        }
        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Clima</h1>\n"
                + "</body>\n"
                + "</html>\n" + linea;
        out.println(outputLine);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }

    /**
     * Metodo que recibe la ciudad y obtinene el JSON con las caracterisiticas
     *
     * @param linea ingreso del cliente
     * @return JSON de la ciudad
     */
    private static String clima(String linea) throws Exception {
        String api_key = "9aef024db157bbe617995ff3ce911c8c";
        String base_url = "http://api.openweathermap.org/data/2.5/weather?";
        String city_name = linea;
        String complete_url = base_url + "appid=" + api_key + "&q=" + city_name;
        String pagina = lerrinformacionurl.consultar(complete_url);
        return pagina;
    }
}