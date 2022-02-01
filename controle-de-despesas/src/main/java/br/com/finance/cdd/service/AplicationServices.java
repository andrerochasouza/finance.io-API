package br.com.finance.cdd.service;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.finance.cdd.dto.AplicationDTO;
import br.com.finance.cdd.error.ResourceNotFoundException;
import br.com.finance.cdd.model.Aplication;
import br.com.finance.cdd.repository.AplicationRepository;

@Service
public class AplicationServices {

	@Autowired
	private AplicationRepository appRepository;

	// Retorna um Aplication pelo Id
		public Aplication findById(Long id) {
			return this.findByIdApp(id);
		}
	
	// Salva uma Aplciation
	public void addApp(Aplication app) {
		appRepository.save(app);
	}

	// Deleta o Aplication
	public void deleteApp(Long idApp) {
		Aplication app = findByIdApp(idApp);

		if (Objects.isNull(app.getDateDelete())) {
			app.setDateDelete(new Date());
			appRepository.save(app);
		} else {
			throw new ResourceNotFoundException("Aplication Not Found By ID: " + idApp);
		}
	}

	// Retorna um AplicationDTO pelo Id
	public AplicationDTO findByIdDTO(Long id) {
		Aplication app = findByIdApp(id);
		return new AplicationDTO(app);
	}

	// Otimizaação de código
	private Aplication findByIdApp(Long id) {
		return appRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Pay Not Found By ID: " + id));
	}

}
