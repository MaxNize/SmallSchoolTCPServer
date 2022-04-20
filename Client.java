package V2UPLOAD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Client {
    public static void main(String[] args) {
        
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                String msgToSend = scanner.nextLine();
                String line;
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                line = bufferedReader.readLine();
                while (!line.equals("END")){
                    System.out.print("Server: ");
                    System.out.println(line);
                    line = bufferedReader.readLine();
                }
                if (msgToSend.equalsIgnoreCase("BYE")){
                    break;
                }
                
            }} catch (UnknownHostException e){
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    if (socket != null){
                        socket.close();
                    }
                    if (inputStreamReader != null){
                        inputStreamReader.close();
                    }
                    if (outputStreamWriter != null){
                        outputStreamWriter.close();
                    }
                    if (bufferedReader != null){
                        bufferedReader.close();
                    }
                    if (bufferedWriter != null){
                        bufferedWriter.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    

