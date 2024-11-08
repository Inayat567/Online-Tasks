import java.io.*;
import java.net.*;

public class App {

    public static void main(String[] args) {
        // Declare variables to represent InputStream and OutputStream
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            // Read URL and file name from the user

            // example of url ** https://www.gutenberg.org/files/11/11-0.txt **
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the URL: ");
            String urlString = reader.readLine();
            System.out.print("Enter the file name with extension: ");
            String fileName = reader.readLine();

            // Create URL object
            URL url = new URL(urlString);

            // Get InputStream from the URL
            inputStream = url.openStream();

            // Get OutputStream for the file
            outputStream = new FileOutputStream(fileName);

            // Copy data from web to file
            copyStream(inputStream, outputStream);

            System.out.println("Download successful!");

        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading/writing data: " + e.getMessage());
        } finally {
            // Close streams in the finally block to ensure they are closed
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing streams: " + e.getMessage());
            }
        }
    }

    // Method to copy data from InputStream to OutputStream
    private static void copyStream(InputStream in, OutputStream out) throws IOException {
        int oneByte = in.read();
        while (oneByte >= 0) {
            out.write(oneByte);
            oneByte = in.read();
        }
    }
}
