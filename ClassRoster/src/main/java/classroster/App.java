package classroster;

import controller.ClassRosterController;
import dao.ClassRosterAuditDao;
import dao.ClassRosterAuditDaoFileImpl;
import dao.ClassRosterDao;
import dao.ClassRosterDaoFileImpl;
import service.ClassRosterServiceLayer;
import service.ClassRosterServiceLayerImpl;
import ui.ClassRosterView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);

        controller.run();
    }
}
