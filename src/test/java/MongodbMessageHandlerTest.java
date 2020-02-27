//import com.example.javaConsoleApp.JavaConsoleApplication;
//import com.example.javaConsoleApp.MongodbMessageHandler;
//import com.example.javaConsoleApp.mongodb.model.Data;
//import com.example.javaConsoleApp.mongodb.repository.DataRepository;
//import com.mongodb.MongoSocketOpenException;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.jms.listener.DefaultMessageListenerContainer;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.net.ConnectException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = JavaConsoleApplication.class)
//public class MongodbMessageHandlerTest {
//    @MockBean
//    private MongoOperations mongoOperations;
//
//    @MockBean
//    private GridFsTemplate gridFsTemplate;
//
//    @MockBean
//    private DataRepository dataRepository;
//
//    @MockBean
//    private JmsTemplate jmsTemplate;
//
//    @MockBean
//    private JmsListener jmsListener;
//
//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();
//
//
//    private MongodbMessageHandler mongodbMessageHandler;
//    private Data data;
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//    @Before
//    public void init(){
//        mongodbMessageHandler = new MongodbMessageHandler(mongoOperations);
//        data = new Data();
//        data.setCurrrentDate(dtf.format(LocalDateTime.now()));
//    }
//
////    @Test
////    public void shouldProcessErrorWhenNoConnectionToDb(){
////        expectedException.expect(RuntimeException.class);
////        mongodbMessageHandler.receive(data.toString());
////    }
//
//}
