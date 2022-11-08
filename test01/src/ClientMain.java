/**
 * 先启动ClientMain，后启动ServerMain
 * @author LongZhen
 * @date Created in 2022/11/8 22:37
 */


public class ClientMain {

    public static void main(String[] args) {

        ServerClient client = new ServerClient();
        client.client();
    }
}
