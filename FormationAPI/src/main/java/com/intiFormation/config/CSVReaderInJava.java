package com.intiFormation.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.intiFormation.model.Personne;
import com.intiFormation.service.IPersonneService;
import com.intiFormation.service.PersonneService;

public class CSVReaderInJava {

	private static IPersonneService pservice = new PersonneService();
	
    public static void main(String... args) {
        readPersonnesFromCSV("src/main/java/com/intiFormation/config/personnes.txt");
    }

    private static void readPersonnesFromCSV(String fileName) {
        Path pathToFile = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Personne p = createPersonne(attributes);
                pservice.ajout(p);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static Personne createPersonne(String[] metadata) {
    	//int id = Integer.parseInt(metadata[0]);
    	String prenom = metadata[0];
    	String nom = metadata[1];
        int age = Integer.parseInt(metadata[2]);
        String email = metadata[3];
        String tel = metadata[4];
        //return new Personne(id, prenom, nom, age, email, tel);
        return new Personne(prenom, nom, age, email, tel);
    }

}
