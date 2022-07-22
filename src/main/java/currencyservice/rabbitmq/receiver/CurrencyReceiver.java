package currencyservice.rabbitmq.receiver;

import com.rabbitmq.client.Channel;
import currencyservice.api.controller.CurrencyController;
import currencyservice.rabbitmq.MyAcknowledgement;
import currencyservice.rabbitmq.config.Constant;
import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CurrencyReceiver {

    @Autowired
    CurrencyController currencyController;

    @RabbitListener(queues = Constant.GET_CURRENCY_QUEUE)
    public Double getCurrency(@Payload String currencies, Channel channel) throws JSONException, IOException {
        MyAcknowledgement.setAcknowledgement(channel, 1L, true);
        ResponseEntity<Double> entity;
        entity = currencyController.getExchangeRate(currencies);
        Double exchangeRate = entity.getBody();
        return exchangeRate;
    }
}