package controllers;

import play.mvc.Controller;
import play.mvc.Result;


import play.mvc.*;
import repository.PDFRepository;
import models.PDF;
import play.data.FormFactory;
import javax.inject.Inject;
import java.util.List;

public class PDFController extends Controller {

    private final PDFRepository pdfRepository;
    private final FormFactory formFactory;

    @Inject
    public PDFController(PDFRepository pdfRepository, FormFactory formFactory) {
        this.pdfRepository = pdfRepository;
        this.formFactory = formFactory;
    }
    
    public Result listPDFs() {
        List<PDF> pdfs = pdfRepository.findAll();   
        return ok(views.html.pdfList.render(pdfs));
    }

    public Result pdfList() {
        return listPDFs();
    }

    public Result getPDF(Long id) {
        PDF pdf = pdfRepository.findById(id);
        return ok(views.html.pdfView.render(pdf));
    }

    public Result uploadPDF(Http.Request request) {
        // Handle file upload logic
        // Save PDF metadata to the database
        return redirect(routes.PDFController.pdfList());
    }

    public Result downloadPDF(Long id) {
        PDF pdf = pdfRepository.findById(id);
        if (pdf == null) {
            return notFound("PDF not found");
        }
    return ok(new java.io.File(pdf.getFilePath())).as("application/pdf");
    }

    public Result deletePDF(Long id) {
        PDF pdf = pdfRepository.findById(id);
        if (pdf != null) {
            pdfRepository.delete(pdf);
        }
    return redirect(routes.PDFController.pdfList());
}
}