package ui;

import dto.dvd;

import java.util.List;

public class dvdLibraryView {

    private UserIO io;

    public dvdLibraryView(UserIO io){
        this.io = io;
    }

    public int printMenuAndGetSelection(){
        io.print("MAIN MENU");
        io.print("1. List DVD Collection");
        io.print("2. Add a DVD to the Collection");
        io.print("3. Remove a DVD from the Collection");
        io.print("4. Edit DVD information");
        io.print("5. View a DVD from the collection");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public dvd getNewDVDInfo(){
        String title = io.readString("Please enter the DVD's title");
        String releaseDate = io.readString("Please enter the DVD's release date");
        String MPAARating = io.readString("Please enter the DVD's MPAA Rating");
        String directorsName = io.readString("Please enter the Director's name");
        String studio = io.readString("Please enter the DVD's studio name");
        String userRating = io.readString("Please enter your rating");
        dvd currentDVD = new dvd(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMPAARating(MPAARating);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        return currentDVD;
    }

    public void displayCreateDVDBanner(){
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.print("DVD successfully created. Please hit enter to continue.");
    }

    public void displayDVDCollection(List<dvd> DVDCollection){
        for(dvd currentDVD : DVDCollection){
            String DVDInfo = String.format("#%s : %s %s", currentDVD.getTitle(), currentDVD.getReleaseDate(), currentDVD.getDirectorsName());
            io.print(DVDInfo);
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner(){
        io.print("=== Display DVD Collection ===");
    }

    public void displayDisplayDVDBanner(){
        io.print("=== Display DVD ===");
    }

    public String getDVDTitleChoice(){
        return io.readString("Please enter the DVD's Title");
    }

    public void displayDVD(dvd DVD){
        if(DVD != null){
            io.print(DVD.getTitle());
            io.print(DVD.getReleaseDate());
            io.print(DVD.getMPAARating());
            io.print(DVD.getDirectorsName());
            io.print(DVD.getStudio());
            io.print(DVD.getUserRating());
            io.print("");
        }
        else{
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner(){
        io.print("=== Remove DVD From Collection ===");
    }

    public void displayRemoveResult(dvd DVD) {
        if (DVD != null) {
            io.print("DVD successfully removed.");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayEditDVDBanner(){
        io.print("=== EDIT DVD'S INFO ===");
    }

    public dvd editDVD(dvd DVD){
        String releaseDate = io.readString("Please enter the updated release date");
        String MPAARating = io.readString("Please enter the updated MPAA Rating");
        String directorsName = io.readString("Please enter the updated Director's name");
        String studio = io.readString("Please enter the updated Studio name");
        String userRating = io.readString("Please enter your updated rating");

        DVD.setReleaseDate(releaseDate);
        DVD.setMPAARating(MPAARating);
        DVD.setDirectorsName(directorsName);
        DVD.setStudio(studio);
        DVD.setUserRating(userRating);

        return DVD;

    }

    public void displayEditSuccessBanner(){
        io.print("=== EDIT SUCCESSFUL ===");
    }
    public void displayExitBanner(){
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner(){
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg){
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }





}
