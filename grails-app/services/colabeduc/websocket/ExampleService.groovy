package colabeduc.websocket

import org.springframework.messaging.simp.SimpMessageSendingOperations

class ExampleService {

    SimpMessageSendingOperations brokerMessagingTemplate

    void hello() {
        brokerMessagingTemplate.convertAndSend "/topic/hello", "hello from service!"
    }

}