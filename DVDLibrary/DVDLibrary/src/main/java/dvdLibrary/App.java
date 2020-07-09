package dvdLibrary;

import controller.dvdLibraryController;
import dao.dvdLibraryDao;
import dao.dvdLibraryDaoFileImpl;
import ui.UserIO;
import ui.UserIOConsoleImpl;
import ui.dvdLibraryView;

public class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        dvdLibraryView myView = new dvdLibraryView(myIO);
        dvdLibraryDao myDao = new dvdLibraryDaoFileImpl();
        dvdLibraryController controller = new dvdLibraryController(myView, myDao);

        controller.run();
    }
}
