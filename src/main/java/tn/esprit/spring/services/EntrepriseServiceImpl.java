package tn.esprit.spring.services;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.log4j.Logger;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	
	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	private static final Logger logger = Logger.getLogger(EntrepriseServiceImpl.class);	

	
	public int ajouterEntreprise(Entreprise entreprise) {
		logger.info("Sart add business");
		logger.debug("Starting add business");
	if(entreprise.getName()==null || entreprise.getRaisonSocial()==null)	
	{
		logger.debug("Add Failed");
		logger.info("Add ended");
	return 0;
	}
	else{
		entrepriseRepoistory.save(entreprise);
		logger.debug("Finishing business add");
		logger.info("Business add finished");
		return entreprise.getId();
		}	
	}

	public int ajouterDepartement(Departement dep) {
		logger.info("Sart add departement");
		logger.debug("Starting add departement");
		
		deptRepoistory.save(dep);
		
		logger.debug("Finishing departement add ");
		logger.info("departement add finished");
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		
		logger.info("Start affect departement to business");
		logger.debug("Starting Function");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if(value.isPresent())
		{Entreprise entrepriseManagedEntity = value.get();
				
		logger.debug("Business Found" + entrepriseManagedEntity);
		logger.debug("Start finding department by id ");
		Optional<Departement> value1 = deptRepoistory.findById(depId);
		if(value1.isPresent())
		{Departement depManagedEntity=value1.get();
			
			
		logger.debug("Department found" + depManagedEntity);
		logger.debug("Start updating business");	
		
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
				
				logger.debug("Update done");	
				logger.info("Affect department to business done");
		
	}}
		else{logger.debug("Department dosn't exist in this business");	
		logger.info("done");
			
		}
		}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		logger.info("lancer  la methode get all department names by entreprise");
		logger.debug("lancer  la recherche de l entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) 
		
		{Entreprise entrepriseManagedEntity= value.get();
			
		logger.debug("je viens de trouver l entreprise" +entrepriseManagedEntity);
		List<String> depNames = new ArrayList<>();
		logger.debug("je vais lancer  la boucle sur tous les departements et ajouter le nom du departementt au tableau depNames");
		
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		logger.debug("je viens de remplir le tableau depNames");
		logger.info("fin de   la methode get all department names by entreprise");
		return depNames;
		}
		else
		{logger.debug("l'entreprisee n'existe pas");
		logger.info("fin de   la methode get all department names by entreprise");
		
		return new ArrayList<>();
		}
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		logger.info("lancer  la methode delete entreprise by id");
		logger.debug("je vais lancer  la methode delete entreprise by id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent=value.get();
			entrepriseRepoistory.delete(ent);	
			
			logger.debug("je viens de finir la delete entreprise by id");
			logger.info("finb de   la methode delete entreprise by id");	
		}
		else {logger.debug("l'entreprise n'existe pass");
		logger.info("finb de   la methode delete entreprise by id");	
			
		}
		
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		logger.info("lancer  la methode delete department by id");
		logger.debug("je vais lancer  la methode delete departement by id");
		Optional<Departement> value = deptRepoistory.findById(depId);
		if (value.isPresent()) {
			Departement dep=value.get();
		deptRepoistory.delete(dep);
		
		logger.debug("je viens de finir la delete departement by id");
		logger.info("fin de  la methode delete department by id");
		}
		else {
			logger.debug("le departement n'existe pas");
			logger.info("fin de  la methode delete department by id");
		}
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		logger.info("lancer  la methode get entreprise by id");
		logger.debug("je vais lancer  la recherche de l'entreprise par id");
		Optional<Entreprise> value = entrepriseRepoistory.findById(entrepriseId);
		if (value.isPresent()) {
			Entreprise ent=value.get();
			
			logger.debug("je viens de trouver l'entreprise par id"+ent);
			logger.info("fin de   la methode get entreprise by id");
		 return ent;
		}
		else {logger.debug("l'entreprise n'existeee pas");
		return null;}
		
	}

}
