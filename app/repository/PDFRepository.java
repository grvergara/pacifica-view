package repository;

import models.PDF;
import io.ebean.Ebean;
import java.util.List;

public class PDFRepository {

    public List<PDF> findAll() {
        return Ebean.find(PDF.class).findList();
    }

    public PDF findById(Long id) {
        return Ebean.find(PDF.class, id);
    }

    public void save(PDF pdf) {
        Ebean.save(pdf);
    }

    public void delete(PDF pdf) {
        Ebean.delete(pdf);
    }
}
