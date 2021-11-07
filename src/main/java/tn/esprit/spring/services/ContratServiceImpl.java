package tn.esprit.spring.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.repository.ContratRepository;

@Service
public class ContratServiceImpl implements IContratService {


	@Autowired
	ContratRepository contratRepository;
	private static final Logger logger = Logger.getLogger(ContratServiceImpl.class);
	public List<Contrat> getAllContrats() {
		logger.info("Start getting all contracts");
		logger.debug("Starting Function");
		logger.debug("finished Function");
		logger.info(" getting all contracts done");
		return (List<Contrat>) contratRepository.findAll();
	}

}
