package currencyservice.rabbitmq.receiver;

import currencyservice.api.controller.CurrencyController;
import currencyservice.rabbitmq.config.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CurrencyReceiver {

    @Autowired
    CurrencyController currencyController;

    @RabbitListener(queues = Constant.GET_CURRENCY_QUEUE)
    public Double getCurrency(String currencies) {
        ResponseEntity<Double> entity;
        try {
            entity = currencyController.getExchangeRate(currencies);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        Double exchangeRate = entity.getBody();
        return exchangeRate;
    }
}