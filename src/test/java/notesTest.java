import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;
import ru.levelup.AppConfig;
import ru.levelup.api.entities.*;
import ru.levelup.api.WSHandler;
import ru.levelup.enttities.Note;

import java.util.List;

public class notesTest {

    //@Test
    public void getNotesTest() {
        Gson gson = new Gson();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WSHandler wsHandler = (WSHandler)context.getBean("wsHandler");
        RequestContainer<List<Note>> request = new RequestContainer<>();
        request.setRequestId("getNotes");
        request.setToken("0000");
        request.setMethod("getNotes");
    }

    //@Test
    public void addNoteTest() {
        Gson gson = new Gson();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WSHandler wsHandler = (WSHandler) context.getBean("wsHandler");
        RequestContainer<AddNotePayload> request = new RequestContainer<>();
        request.setRequestId("addNote");
        request.setMethod("addNote");
        request.setToken("0000");
        request.setPayload(new AddNotePayload("Note new constructor","Message///"));
    }

    //@Test
    public void deleteNoteTest() {
        Gson gson = new Gson();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WSHandler wsHandler = (WSHandler) context.getBean("wsHandler");
        RequestContainer<DeleteNotePayload> request = new RequestContainer<>();
        request.setRequestId("deleteNote");
        request.setMethod("deleteNote");
        request.setToken("0000");
        request.setPayload(new DeleteNotePayload("585133524828f51ef4d49d96"));
    }

    //@Test
    public void updateNoteTest() {
        Gson gson = new Gson();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WSHandler wsHandler = (WSHandler) context.getBean("wsHandler");
        RequestContainer<EditNotePayload> request = new RequestContainer<>();
        request.setRequestId("updateNote");
        request.setMethod("editNote");
        request.setToken("0000");
        request.setPayload(new EditNotePayload("585137484828f516d48896bc","New_tittle","new_body"));

    }

    @Test
    public void addAccessTest() {
        Gson gson = new Gson();
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WSHandler wsHandler = (WSHandler) context.getBean("wsHandler");
        RequestContainer<AddAccessRightPayload> request = new RequestContainer<>();
        request.setRequestId("addAccRight");
        request.setMethod("addAccRight");
        request.setToken("1111");
        request.setPayload(new AddAccessRightPayload("585137484828f516d48896bc","584ea9ca4828f51f88804dd5",0));

    }
}
