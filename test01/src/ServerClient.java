import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 先启动ClientMain，后启动ServerMain
 * @author LongZhen
 * @date Created in 2022/11/8 22:01
 */


public class ServerClient {


    /**
     * 服务端
     */
    public void server() {
        // 标记是否使用Nagle算法：Yes使用 No不使用
        byte isNagle = 0;
        Socket receiveSocket = null;
        Socket sendSocket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        ServerSocket ss = null;
        ByteArrayOutputStream baos = null;
        try {

            sendSocket = new Socket("127.0.0.1", 8090);
            ss = new ServerSocket(8080);
            // 从客户端接收消息
            outputStream = sendSocket.getOutputStream();
            // 告诉客户端是否使用Nagle算法
            outputStream.write(isNagle);
            outputStream.flush();

            byte[] buffer = new byte[1024];
            int len;
            baos = new ByteArrayOutputStream();

            receiveSocket = ss.accept();
            inputStream = receiveSocket.getInputStream();

            while(-1 != (len = inputStream.read(buffer))) {
                baos.write(buffer, 0, len);
            }

            System.out.println("服务端接收到客户端的消息：" + baos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close(ss, receiveSocket, sendSocket, inputStream, outputStream);
        }

    }

    /**
     * 客户端
     */
    public void client() {
        // 标记是否使用Nagle算法：Yes使用 No不使用
        Socket receiveSocket = null;
        Socket sendSocket = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8090);
            // 向服务端发送数据

            receiveSocket = ss.accept();
            inputStream = receiveSocket.getInputStream();

            // 客户端被告知是否使用Nagle算法
            int isNagle = inputStream.read();
            System.out.println("客户端从服务端处得知是否使用Nagle算法" + isNagle);
            // true表示禁用Nagle算法，false表示使用Nagle算法
            sendSocket = new Socket("127.0.0.1", 8080);
            sendSocket.setTcpNoDelay(isNagle == 0);
            outputStream = sendSocket.getOutputStream();
            String message = "message";
            outputStream.write(message.getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.close(ss, receiveSocket, sendSocket, inputStream, outputStream);
        }

    }

    /**
     * 关闭资源
     * @param ss
     * @param receiveSocket
     * @param sendSocket
     * @param inputStream
     * @param outputStream
     */
    public void close(ServerSocket ss,
                      Socket receiveSocket,
                      Socket sendSocket,
                      InputStream inputStream,
                      OutputStream outputStream) {

        if(null != ss) {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(null != receiveSocket) {
            try {
                receiveSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != sendSocket) {
            try {
                sendSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != outputStream) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(null != inputStream) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
