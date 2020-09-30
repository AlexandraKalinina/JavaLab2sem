package direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import model.User;
import org.apache.commons.lang3.RandomStringUtils;
import utils.Pdf;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerDirectReference {

    private final static String REFERENCE_QUEUE = "reference_queue";

    private final static String packageName = "pdf_package№" + RandomStringUtils.random(5, false, true);

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        Pdf pdf = new Pdf();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.basicConsume(REFERENCE_QUEUE, false, (consumerTag, message) -> {
                String jsonUser = new String(message.getBody());
                //json в юзера , заполнение pdf
                ObjectMapper mapper = new ObjectMapper();
                try {
                    User user = mapper.readValue(jsonUser, User.class);
                    pdf.addPdf(user, "Reference.txt", packageName);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException | DocumentException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
