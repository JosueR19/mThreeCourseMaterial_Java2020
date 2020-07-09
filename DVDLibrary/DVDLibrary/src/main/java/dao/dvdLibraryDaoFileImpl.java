package dao;

import dto.dvd;

import java.io.*;
import java.util.*;

public class dvdLibraryDaoFileImpl implements dvdLibraryDao {
    private Map<String, dvd> dvdCollection = new HashMap<>();
    public static final String DVDCOLLECTION_FILE = "DVDCollection.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public dvd addDVD(String title, dvd DVD) throws dvdLibraryDaoException {
        loadCollection();
        dvd prevDVD = dvdCollection.put(title, DVD);
        writeCollection();
        return prevDVD;
    }

    @Override
    public List<dvd> getAllDVDs() throws dvdLibraryDaoException {
        loadCollection();
        return new ArrayList<dvd>(dvdCollection.values());
    }

    @Override
    public dvd getDVDInfo(String title) throws dvdLibraryDaoException {
        loadCollection();
        return dvdCollection.get(title);
    }

    @Override
    public dvd removeDVD(String title) throws dvdLibraryDaoException {
        loadCollection();
        dvd removedDVD = dvdCollection.remove(title);
        writeCollection();
        return removedDVD;
    }

    @Override
    public dvd editDVDInfo(String title, dvd DVD) throws dvdLibraryDaoException {
        loadCollection();
        dvdCollection.put(title, DVD);
        writeCollection();
        return DVD;
    }

    private dvd unmarshallDVD(String dvdAsText){
        String[] dvdTokens = dvdAsText.split(DELIMITER);
        String dvdTitle = dvdTokens[0];
        dvd dvdFromFile = new dvd(dvdTitle);

        dvdFromFile.setReleaseDate(dvdTokens[1]);
        dvdFromFile.setMPAARating(dvdTokens[2]);
        dvdFromFile.setDirectorsName(dvdTokens[3]);
        dvdFromFile.setStudio(dvdTokens[4]);
        dvdFromFile.setUserRating(dvdTokens[5]);

        return dvdFromFile;
    }

    private void loadCollection() throws dvdLibraryDaoException{
        Scanner sc;

        try{
            sc = new Scanner(new BufferedReader(new FileReader(DVDCOLLECTION_FILE)));
        } catch (FileNotFoundException ex){
            throw new dvdLibraryDaoException("Could not load the DVD collection.");
        }

        String currentLine;
        dvd currentDVD;

        while(sc.hasNextLine()){
            currentLine = sc.nextLine();
            currentDVD = unmarshallDVD(currentLine);
            dvdCollection.put(currentDVD.getTitle(), currentDVD);
        }
        sc.close();
    }

    private String marshallDVD(dvd aDVD){
        String dvdAsText = aDVD.getTitle() + DELIMITER;

        dvdAsText += aDVD.getReleaseDate() + DELIMITER;
        dvdAsText += aDVD.getMPAARating() + DELIMITER;
        dvdAsText += aDVD.getDirectorsName() + DELIMITER;
        dvdAsText += aDVD.getStudio() + DELIMITER;
        dvdAsText += aDVD.getUserRating() + DELIMITER;

        return dvdAsText;
    }

    private void writeCollection() throws dvdLibraryDaoException{
        PrintWriter out;

        try{
            out = new PrintWriter(new FileWriter(DVDCOLLECTION_FILE));
        } catch(IOException ex){
            throw new dvdLibraryDaoException("Could not save the dvd collection data.");
        }

        String dvdAsText;
        List<dvd> dvdCollectionList = new ArrayList(dvdCollection.values());
        for(dvd currentDVD : dvdCollectionList){
            dvdAsText = marshallDVD(currentDVD);
            out.println(dvdAsText);
            out.flush();
        }
        out.close();
    }

}
