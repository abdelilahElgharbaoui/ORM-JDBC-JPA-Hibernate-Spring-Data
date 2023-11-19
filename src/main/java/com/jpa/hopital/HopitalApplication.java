package com.jpa.hopital;

import com.jpa.hopital.entities.Patient;
import com.jpa.hopital.repositories.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //test
        patientRepository.save(new Patient(null,"hassan",new Date(),false,56) );
        patientRepository.save(new Patient(null,"abdelilah",new Date(),false,100) );
        patientRepository.save(new Patient(null,"Imane",new Date(),false,200) );

//        inserer par boucle
        for(int i = 0; i<100;i++){
            patientRepository.save(new Patient(null,"Abdelilah",new Date(),Math.random()>0.5?true:false, (int) (Math.random() * 100)) );

        }

        //        get all patients

//        List<Patient> patients= patientRepository.findAll();

        //get patient by pagination
        Page<Patient> patients= patientRepository.findAll(PageRequest.of(0,10));


        System.out.println("total pages:"+patients.getTotalPages());
        System.out.println("total elements"+patients.getTotalElements());
        System.out.println("Number Page"+patients.getNumber());

        List<Patient> content = patients.getContent();
        Page<Patient> byMalade = patientRepository.findByMalade(true,PageRequest.of(0,10));

        byMalade.forEach(
                p->{
                    System.out.println("======================================================");
                    System.out.println("id: "+p.getId()+" nom: "+p.getNom()+" score"+p.getScore()+" Naissance: "+p.getDateNaissance()+" malade:"+p.isMalade());
                }
        );
        content.forEach(
                p->{
                    System.out.println("======================================================");
                    System.out.println("id: "+p.getId()+" nom: "+p.getNom()+" score"+p.getScore()+" Naissance: "+p.getDateNaissance()+" malade:"+p.isMalade());
                }
        );
        System.out.println("*********************Find By Id*************************");
        Patient patient = patientRepository.findById(1L).orElse(null); //get to get patient . orElseThrow
        if(patient != null){
            System.out.println("id: "+patient.getId()+" nom: "+patient.getNom()+" score"+patient.getScore()+" Naissance: "+patient.getDateNaissance()+" maled:"+patient.isMalade());

        }
        System.out.println("**********************Update************************");
        patient.setScore(870);
        patientRepository.save(patient);
        System.out.println("**********************Delete by ID ************************");
        patientRepository.deleteById(1L);


        List<Patient> patientList = patientRepository.chercherPatients("Abdelilah",40);

        patientList.forEach(
                p->{
                    System.out.println("===========================chercher Patient query===========================");
                    System.out.println("id: "+p.getId()+" nom: "+p.getNom()+" score"+p.getScore()+" Naissance: "+p.getDateNaissance()+" malade:"+p.isMalade());
                }
        );

    }
}
