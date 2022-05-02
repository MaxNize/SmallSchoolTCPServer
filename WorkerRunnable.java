package V2UPLOAD;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected int serverNUM;
    protected String root = "YOUR:\\ROOT\\DIR\\USERS\\";

    public WorkerRunnable(Socket clientSocket, int serverNUM) {
        this.clientSocket = clientSocket;
        this.serverNUM   = serverNUM;
    }

    public void write(BufferedWriter bufferedWriter, String text) throws IOException{
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.write("END");
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            while (true) {
                String msgFromClient = bufferedReader.readLine();
                String[] splittedMSG = msgFromClient.split(" ");
                String msgToClient = "MSG Recieved. ";
                String fileString;
                File file;
                Users user;

                System.out.print("Client");
                System.out.print(Integer.toString(serverNUM));
                System.out.print(": ");
                System.out.println(msgFromClient);
                
                if (splittedMSG[0].equals("CREATEACC")) {
                    fileString = root + splittedMSG[1] + ".txt";
                    file = new File(fileString);

                    if (!file.exists()) {
                        file.createNewFile();
                        
                        user = new Users();
                        user.PW = splittedMSG[2];
                        SNL2.save(user, fileString);
                        
                        
                        
                        msgToClient += "\nACC CREATED";
                    } else {
                        msgToClient += "\nACC ALREADY EXISTS";
                    }
                }
                if (splittedMSG[0].equals("ACCESSACC")){
                    fileString = root + splittedMSG[1] + ".txt";
                    file = new File(fileString);
                    
                    if (file.exists()){
                        user = (Users) SNL2.load(fileString);

                        if (splittedMSG[2].equals(user.PW)){
                            msgToClient += "\nACCESS GRANTED";
                            String[] questions = {"\nHund(0) oder Katze(1)?", "\nApple(0) oder Android(1)?", "\nKnoppers(0) oder Hanuta(1)?", "\nPizza(0) oder Burger(1)?", "\nMeer(0) oder Berge(1)?", "\nPepsi(0) oder Cola(1)?", "\nSchoko-(0) oder Vanilleeis(1)?", "\nZitronen-(0) oder Pfirsicheistee(1)?", "\nMcDonald's(0) oder Burger King(1)?", "\nsüßes(0) oder salziges(1) Popcorn?", "\nFriends(0) oder How I Met Your Mother(1)?"};
                            int[] vars = {user.hundOderKatze, user.appleOderAndroid, user.knoHan, user.piBU, user.meBe, user.peCo, user.schoVa, user.ziPfi, user.mcB, user.sueSa, user.friHimym};
                            for (int i = 0; i < questions.length; i++){
                                msgToClient += questions[i];

                                write(bufferedWriter, msgToClient);

                                msgFromClient = bufferedReader.readLine();
                                msgToClient = "MSG Recieved. ";

                                if (msgFromClient.equals("0")){
                                    vars[i] = 0;
                                } 
                                if (msgFromClient.equals("1")){
                                    vars[i] = 1;
                                } 
                            }
                            msgToClient += "\nFINISHED";
                            user.hundOderKatze = vars[0];
                            user.appleOderAndroid = vars[1];
                            user.knoHan = vars[2];
                            user.piBU = vars[3];
                            user.meBe = vars[4];
                            user.peCo = vars[5];
                            user.schoVa = vars[6];
                            user.ziPfi = vars[7];
                            user.mcB = vars[8];
                            user.sueSa = vars[9];
                            user.friHimym = vars[10];
                            SNL2.save(user, fileString);
                        }
                    }
                }

                write(bufferedWriter, msgToClient);

                if (msgFromClient.equalsIgnoreCase("BYE")){
                    break;
                }
            } 

            clientSocket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
