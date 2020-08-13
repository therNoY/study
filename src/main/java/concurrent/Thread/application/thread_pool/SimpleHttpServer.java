package concurrent.Thread.application.thread_pool;

import util.log.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {


    public static void main(String[] args) throws IOException {

        ThreadPool<HttpRequestHandle> threadPool = new DefaultThreadPool<>();

        ServerSocket serverSocket = new ServerSocket(8080);

        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("有请求过来");
            threadPool.execute(new HttpRequestHandle(socket));
        }

        serverSocket.close();

    }

    static class HttpRequestHandle implements Runnable {

        Socket socket = null;

        public HttpRequestHandle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            InputStream in = null;
            PrintWriter printWriter = null;
            try {

                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine(); // 由相对路径计算出绝对路径
                printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type: text/html; charset=UTF-8");
                printWriter.println("");
                printWriter.println("<h1>这是响应报文</h1>");
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
                printWriter.println("HTTP/1.1 500");
                printWriter.println("");
                printWriter.flush();
            } finally {
                Logger.info("处理结束");
                printWriter.close();
            }
        }
    }
}
