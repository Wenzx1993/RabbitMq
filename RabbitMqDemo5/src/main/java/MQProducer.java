import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 *
 *  @Tag 直接交换 Direct exchange
 */
public class MQProducer {

    public static void main(String[] args) {
        try {
            consumerMsg("direct.exchange","queue.route.*","这么耿直的人，当然是直接交换！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void consumerMsg(String exchange,String routeKey,String msg) throws IOException, TimeoutException {
        //创建工厂连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接地址
        connectionFactory.setHost("192.168.239.128");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT,false);
        //创建生产者，发送消息
        channel.basicPublish(exchange,routeKey,null,msg.getBytes());
        //关闭连接
        channel.close();
        connection.close();
    }
}
