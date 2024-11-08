import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private static final int LISTENING_PORT = 8080; // You can choose any available port

    public static void main(String[] args) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(LISTENING_PORT);
        } catch (IOException e) {
            System.out.println("Failed to create listening socket.");
            return;
        }
        System.out.println("Listening on port " + LISTENING_PORT);
        try {
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println("\nConnection from " + connection.getRemoteSocketAddress());
                handleConnection(connection);
            }
        } catch (IOException e) {
            System.out.println("Server socket shut down unexpectedly!");
            System.out.println("Error: " + e);
            System.out.println("Exiting.");
        }
    }

    private static void handleConnection(Socket connection) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            PrintWriter out = new PrintWriter(connection.getOutputStream());

            // Read the request line
            String requestLine = in.readLine();
            if (requestLine == null) {
                // No data received; close the connection
                connection.close();
                return;
            }

            // Split the request line into tokens
            String[] tokens = requestLine.split("\\s+");

            // Check if the request is a valid GET request
            if (tokens.length != 3 || !tokens[0].equals("GET")) {
                sendErrorResponse(400, out);
                connection.close();
                return;
            }

            // Extract the path to the requested file
            String pathToFile = tokens[1];

            // Resolve the requested file
            File file = new File("src/web/" + pathToFile);

            // Check if the file exists and is readable
            if (!file.exists() || !file.isFile() || !file.canRead()) {
                sendErrorResponse(404, out);
                connection.close();
                return;
            }

            // Send the file as a response
            sendFile(file, out);

        } catch (IOException e) {
            System.out.println("Error while communicating with client: " + e);
        } finally {
            try {
                connection.close();
            } catch (IOException e) {
                // Ignore
            }
            System.out.println("Connection closed.");
        }
    }

    private static void sendFile(File file, PrintWriter out) throws IOException {
        try (BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(file))) {
            // Send the HTTP headers
            out.println("HTTP/1.1 200 OK");
            out.println("Connection: close");
            out.println("Content-Length: " + file.length());
            out.println("Content-Type: " + getMimeType(file.getName()));
            out.println(); // Empty line to indicate the end of headers
            out.flush();
    
            // Send the file content
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInput.read(buffer)) != -1) {
                out.write(new String(buffer, 0, bytesRead));
            }
            out.flush();
        } catch (FileNotFoundException e) {
            sendErrorResponse(404, out);
        }
    }
    

    private static void sendErrorResponse(int errorCode, PrintWriter out) {
        String statusMessage;
        String errorMessage;

        switch (errorCode) {
            case 400:
                statusMessage = "Bad Request";
                errorMessage = "The request syntax is invalid.";
                break;
            case 404:
                statusMessage = "Not Found";
                errorMessage = "The requested resource could not be found on this server.";
                break;
            case 500:
                statusMessage = "Internal Server Error";
                errorMessage = "The server encountered an unexpected condition that prevented it from fulfilling the request.";
                break;
            default:
                statusMessage = "Internal Server Error";
                errorMessage = "An unknown error occurred.";
        }

        // Send the HTTP headers for the error response
        out.println("HTTP/1.1 " + errorCode + " " + statusMessage);
        out.println("Connection: close");
        out.println("Content-Type: text/html");
        out.println(); // Empty line to indicate the end of headers

        // Send the HTML content for the error message
        out.println("<html><head><title>Error</title></head><body>");
        out.println("<h2>Error: " + errorCode + " " + statusMessage + "</h2>");
        out.println("<p>" + errorMessage + "</p>");
        out.println("</body></html>");

        out.flush();
    }

    // ... (other methods)

    private static String getMimeType(String fileName) {
        int pos = fileName.lastIndexOf('.');
        if (pos < 0) {
            // No file extension found, default to binary data
            return "application/octet-stream";
        }
    
        String ext = fileName.substring(pos + 1).toLowerCase();
    
        switch (ext) {
            case "txt":
                return "text/plain";
            case "html":
            case "htm":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "text/javascript";
            case "java":
                return "text/x-java";
            case "jpeg":
            case "jpg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "ico":
                return "image/x-icon";
            case "class":
                return "application/java-vm";
            case "jar":
                return "application/java-archive";
            case "zip":
                return "application/zip";
            case "xml":
                return "application/xml";
            case "xhtml":
                return "application/xhtml+xml";
            default:
                // Unknown file extension, default to binary data
                return "application/octet-stream";
        }}
}
