package br.com.jsf.tutorial.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class UploadBean implements Serializable {
	private static final long serialVersionUID = 7566887376063178107L;
	
	public void upload(FileUploadEvent event) {
	    try {
		    UploadedFile uploadedFile = event.getFile();
		    String documentosPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("documentos");
	        File file = new File(documentosPath, uploadedFile.getFileName());
	     
	        OutputStream out = new FileOutputStream(file);
	        out.write(uploadedFile.getContents());
	        out.close();
	     
	        addMessage("Upload completo!", FacesMessage.SEVERITY_INFO);
	    } catch(IOException e) {
	    	addMessage("Erro: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
	    }
	    
    	/*
	    FileOutputStream fileOuputStream = null;
		
	    try {
		    UploadedFile uploadedFile = event.getFile();
		    
		    String documentosPath= FacesContext.getCurrentInstance().getExternalContext().getInitParameter("documentos");
            fileOuputStream = new FileOutputStream(new File(documentosPath+"/teste.pdf"));
            fileOuputStream.write(IOUtils.toByteArray(uploadedFile.getInputstream()));
	     
	        addMessage("Upload completo!", FacesMessage.SEVERITY_INFO);
	    } catch(IOException e) {
	    	addMessage("Erro: " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
	    }
	    */
	}
	
	public void addMessage(String summary, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
