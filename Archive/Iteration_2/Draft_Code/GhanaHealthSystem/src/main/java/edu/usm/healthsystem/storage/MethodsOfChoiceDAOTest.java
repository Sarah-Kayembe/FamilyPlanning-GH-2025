import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MethodsOfChoiceDAOTest {

    private static MethodsOfChoiceDAO dao;

    @BeforeAll
    public static void setup() {
        dao = new MethodsOfChoiceDAO();
    }

    @Test
    public void testAddAndGetMethod() {
        MethodsOfChoice method = new MethodsOfChoice("Pills");
        dao.addMethod(method);

        List<MethodsOfChoice> methods = dao.getAllMethods();
        assertFalse(methods.isEmpty());

        MethodsOfChoice last = methods.get(methods.size() - 1);
        assertEquals("Pills", last.getMethodName());
    }

    @Test
    public void testUpdateMethod() {
        MethodsOfChoice method = new MethodsOfChoice("Injection");
        dao.addMethod(method);

        List<MethodsOfChoice> methods = dao.getAllMethods();
        MethodsOfChoice last = methods.get(methods.size() - 1);

        last.setMethodName("Injectables");
        dao.updateMethod(last);

        MethodsOfChoice updated = dao.getMethodById(last.getId());
        assertEquals("Injectables", updated.getMethodName());
    }

    @Test
    public void testDeleteMethod() {
        MethodsOfChoice method = new MethodsOfChoice("Condom");
        dao.addMethod(method);

        List<MethodsOfChoice> methods = dao.getAllMethods();
        MethodsOfChoice last = methods.get(methods.size() - 1);

        dao.deleteMethod(last.getId());
        MethodsOfChoice deleted = dao.getMethodById(last.getId());

        assertNull(deleted);
    }
}

