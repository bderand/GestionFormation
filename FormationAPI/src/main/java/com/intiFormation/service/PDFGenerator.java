package com.intiFormation.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;


import org.springframework.stereotype.Service;

import com.intiFormation.model.Formation;
import com.intiFormation.model.Participant;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PDFGenerator {
	
	public ByteArrayInputStream export(Participant participant, Formation formation) throws FileNotFoundException, DocumentException{
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, out);
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String date_debut = format.format(formation.getDebut());
		String date_fin = format.format(formation.getFin());
		
		String presentation =
				"Bonjour " + participant.getNom() + " " + participant.getPrenom()+",\n" +
				"Notre équipe vous accueille à la formation \"" + formation.getNom() + "\" qui va commencer le " + date_debut + " à 9h00 et se terminera le " + date_fin + " pendant une durée de 7h30 par jour."+
				"\n" + formation.getFormateur().getNom() + " " + formation.getFormateur().getPrenom() + " assurera votre formation. \n Veuillez trouver votre identifiant de connection : \n" + 
				participant.getUsername() + "\n Pour toutes questions vous pouvez contacter par mail votre formateur: " + formation.getFormateur().getEmail() + 
				" Enfin, nous vous demandons de signer le document ci-dessous précédé de la mention lu et approuvé";
		
		Chunk chunk = new Chunk(presentation, font);
		Paragraph paragraph1 = new Paragraph(chunk);
		
		document.add(paragraph1);
		document.close();
		
		return new ByteArrayInputStream(out.toByteArray());
		
		
		
	}

}
