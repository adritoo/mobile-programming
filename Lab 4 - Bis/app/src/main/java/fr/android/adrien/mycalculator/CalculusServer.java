package fr.android.adrien.mycalculator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class MyCalculusRunnable implements Runnable {
    private Socket sock;

    public MyCalculusRunnable(Socket s) {
        sock = s;
    }

    @Override
    public void run() {

        try {
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            // read op1, op2 and the opreation to make
            Double op1 = dis.readDouble();
            char op = dis.readChar();
            Double op2 = dis.readDouble();

            Double res = CalculusServer.doOp(op1, op2, op);

            // send back result
            dos.writeDouble(res);

            dis.close();
            dos.close();
            sock.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

public class CalculusServer {


    public static double doOp(double op1, double op2, char op) throws Exception
    {
        switch (op) {

            case '+':
                return op1 + op2;

            case '-':
                return op1 - op2;

            case'*':
                return op1 * op2;

            case '/':
                if (op2 != 0)
                    return op1 / op2;
                else
                    throw new Exception();

            default:
                throw new Exception();
        }

    }



    public static void main(String[] args) throws Exception {

        // Example of a distant calculator
        System.out.println("run");
        ServerSocket ssock = new ServerSocket(43622);

        while (true) { // infinite loop
            Socket comm = ssock.accept();
            System.out.println("connection established");
            System.out.println(comm);
            new Thread(new MyCalculusRunnable(comm)).start();

        }

    }

}

