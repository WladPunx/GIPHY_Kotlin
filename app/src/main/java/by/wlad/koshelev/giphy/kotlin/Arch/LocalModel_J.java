package by.wlad.koshelev.giphy.kotlin.Arch;

import java.util.List;

import by.wlad.koshelev.giphy.kotlin.Giphy.GiphiDB;
import by.wlad.koshelev.giphy.kotlin.Giphy.GiphyClass;

public class LocalModel_J {


    Boolean isHaveGif(String id) {
        List<GiphyClass> a = GiphiDB.Companion.getDao().findGif(id);
        if (a.size() != 0) return true;
        else return false;
    }

    void saveGif(GiphyClass gif) {
        GiphiDB.Companion.getDao().addGif(gif);
    }


    void deleteGif(GiphyClass gif) {
        GiphiDB.Companion.getDao().deleteGif(gif);
    }


    List<GiphyClass> getAllGif() {
        return GiphiDB.Companion.getDao().getAllGif();
    }

}
