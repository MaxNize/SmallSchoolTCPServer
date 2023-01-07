import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;
    protected int serverNUM;
    protected String root = ".\\Data\\";
    protected InputStreamReader inputStreamReader;
    protected OutputStreamWriter outputStreamWriter;
    protected BufferedReader bufferedReader;
    protected BufferedWriter bufferedWriter;
    protected String msgFromClient;
    protected String[] splittedMSG;
    protected String msgToClient;
    protected String fileString;
    protected File file;
    protected Users user;
    protected String[] questions = { "\nHund(0) oder Katze(1)?", "\nApple(0) oder Android(1)?",
            "\nKnoppers(0) oder Hanuta(1)?", "\nPizza(0) oder Burger(1)?",
            "\nMeer(0) oder Berge(1)?", "\nPepsi(0) oder Cola(1)?",
            "\nSchoko-(0) oder Vanilleeis(1)?", "\nZitronen-(0) oder Pfirsicheistee(1)?",
            "\nMcDonald's(0) oder Burger King(1)?", "\nsüßes(0) oder salziges(1) Popcorn?",
            "\nFriends(0) oder How I Met Your Mother(1)?" };
    protected boolean running = true;

    public WorkerRunnable(Socket clientSocket, int serverNUM) {
        this.clientSocket = clientSocket;
        this.serverNUM = serverNUM;
    }

    public void write(BufferedWriter bufferedWriter, String text) throws IOException {
        bufferedWriter.write(text);
        bufferedWriter.newLine();
        bufferedWriter.write("END");
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    private void init() {
        try {
            inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void createAcc(String name, String pw) {
        fileString = root + name + ".txt";
        file = new File(fileString);

        try {
            if (file.createNewFile()) {
                user = new Users();
                user.PW = pw;
                SNL2.save(user, fileString);

                msgToClient += "\nACC CREATED";
            } else {
                msgToClient += "\nACC ALREADY EXISTS";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void accessAcc(String name, String pw) {
        fileString = root + name + ".txt";

        file = new File(fileString);

        try {
            if (file.exists()) {
                user = (Users) SNL2.load(fileString);
                int[] vars = { user.hundOderKatze, user.appleOderAndroid, user.knoHan, user.piBU, user.meBe,
                        user.peCo, user.schoVa, user.ziPfi, user.mcB, user.sueSa, user.friHimym };

                if (pw.equals(user.PW)) {
                    msgToClient += "\nACCESS GRANTED";
                    for (int i = 0; i < questions.length; i++) {
                        msgToClient += questions[i];
                        write(bufferedWriter, msgToClient);

                        questionaire();

                        if (msgFromClient.equals("0")) {
                            vars[i] = 0;
                        }
                        if (msgFromClient.equals("1")) {
                            vars[i] = 1;
                        }
                    }

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
                    msgToClient += "\nFINISHED";

                    return;
                }

                msgToClient += "\nWRONG PW";
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        msgToClient += "\nACC NOT FOUND";
    }

    private void questionaire() {
        try {
            msgFromClient = bufferedReader.readLine();
            splittedMSG = msgFromClient.split(" ");
            msgToClient = "MSG Recieved. ";
            if (msgFromClient.equalsIgnoreCase("BYE")) {
                running = false;
            }

            System.out.print("Client" + serverNUM + ": " + msgFromClient + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() {
        init();
        try {
            while (running) {
                fileString = "";
                file = null;
                user = null;
                questionaire();

                if (splittedMSG[0].equals("CREATEACC")) {
                    createAcc(splittedMSG[1], splittedMSG[2]);
                }
                if (splittedMSG[0].equals("ACCESSACC")) {
                    accessAcc(splittedMSG[1], splittedMSG[2]);
                }

                write(bufferedWriter, msgToClient);
            }

            clientSocket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            // report exception somewhere.
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
