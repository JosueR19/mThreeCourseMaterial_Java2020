package VendingMachine;

import controller.VendingMachineController;
import dao.VendingMachineDao;
import dao.VendingMachineDaoFileImpl;
import dto.Change;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import view.UserIO;
import view.UserIOConsoleImpl;
import view.VendingMachineView;

public class App {
    public static void main(String[] args) {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        Change myChange = new Change();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myChange);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
}
