import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;
import ru.levelup.AppConfig;
import ru.levelup.api.entities.AuthPayload;
import ru.levelup.api.entities.RequestContainer;
import ru.levelup.api.WSHandler;

public class registrationTest {
    @Test
    public void registrationTest() {
        Gson gson = new Gson();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WSHandler wsHandler = (WSHandler)context.getBean("wsHandler");
        RequestContainer<AuthPayload> request = new RequestContainer<>();
        request.setRequestId("436436");
        request.setToken("");
        request.setMethod("registration");
        request.setPayload(new AuthPayload("smbody@mail.ru","password"));
        gson.toJson(request);
    }
}
