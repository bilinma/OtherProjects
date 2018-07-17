package com.bilin.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainBioServer {

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);     //1
        try {
            while(true) {
                final Socket clientSocket = socket.accept();    //2
                System.out.println("Accepted connection from " + clientSocket);

                new Thread(new Runnable() {                        //3
                    @Override
                    public void run() {
                    	InputStream is = null;
                        OutputStream out = null;
                        try {
                        	is = clientSocket.getInputStream();
                        	BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                        	StringBuffer str = new StringBuffer();
							while (br.ready()) {
								 str.append(br.readLine() + "\n");
							}
                            System.out.println(str.toString());
                        	
                            out = clientSocket.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));                            //4
                            out.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                        	
                        	try {
                        		is.close();
                        		out.close();
                        		clientSocket.close();
                        	} catch (IOException ex) {
                        		ex.printStackTrace();
                        	}
						}
                    }
                }).start();                                        //6
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
    	PlainBioServer server = new PlainBioServer();
    	server.serve(8888);
    }
    
    
}
