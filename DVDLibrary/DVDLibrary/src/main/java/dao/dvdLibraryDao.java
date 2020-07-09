package dao;

import dto.dvd;

import java.util.List;

public interface dvdLibraryDao {

    dvd addDVD(String title, dvd DVD)
        throws dvdLibraryDaoException;

    List<dvd> getAllDVDs()
        throws dvdLibraryDaoException;

    dvd getDVDInfo(String title)
        throws dvdLibraryDaoException;

    dvd removeDVD(String title)
        throws dvdLibraryDaoException;

    dvd editDVDInfo(String title, dvd DVD)
        throws dvdLibraryDaoException;


}
