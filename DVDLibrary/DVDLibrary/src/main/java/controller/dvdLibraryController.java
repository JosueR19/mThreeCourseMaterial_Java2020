package controller;

import dao.dvdLibraryDao;
import dao.dvdLibraryDaoException;
import dto.dvd;
import ui.dvdLibraryView;

import java.util.List;

public class dvdLibraryController {

    private dvdLibraryView view;
    private dvdLibraryDao dao;

    public dvdLibraryController(dvdLibraryView view, dvdLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run(){
        boolean keepRunning = true;
        int menuSelection = 0;

        try{
            while(keepRunning) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        listDVDCollection();
                        break;
                    case 2:
                        createDVD();
                        break;
                    case 3:
                        removeDVD();
                        break;
                    case 4:
                        editDVD();
                        break;
                    case 5:
                        viewDVD();
                        break;
                    case 6:
                        keepRunning = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        }catch(dvdLibraryDaoException ex){
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private int getMenuSelection(){
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws dvdLibraryDaoException{
        view.displayCreateDVDBanner();
        dvd newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }

    private void listDVDCollection() throws dvdLibraryDaoException{
        view.displayDisplayAllBanner();
        List<dvd> DVDCollection = dao.getAllDVDs();
        view.displayDVDCollection(DVDCollection);
    }

    private void viewDVD() throws dvdLibraryDaoException{
        view.displayDisplayDVDBanner();
        String title = view.getDVDTitleChoice();
        dvd DVD = dao.getDVDInfo(title);
        view.displayDVD(DVD);
    }

    private void removeDVD() throws dvdLibraryDaoException{
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        dvd removeDVD = dao.removeDVD(title);
        view.displayRemoveResult(removeDVD);
    }

    private void editDVD() throws dvdLibraryDaoException{
        view.displayEditDVDBanner();
        String title = view.getDVDTitleChoice();
        dvd prevDVD = dao.getDVDInfo(title);
        dvd updatedDVD = view.editDVD(prevDVD);
        dao.editDVDInfo(updatedDVD.getTitle(), updatedDVD);
        view.displayEditSuccessBanner();

    }

    private void unknownCommand(){
        view.displayUnknownCommandBanner();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }

}
