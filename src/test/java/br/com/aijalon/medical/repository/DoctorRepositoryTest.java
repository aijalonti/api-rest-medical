package br.com.aijalon.medical.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.aijalon.medical.model.Doctor;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DoctorRepositoryTest {
	
	@Autowired
	private DoctorRepository repository;

	@Test
	public void deveriaCarregarumDoctorAoBucarPeloNome() {
		String nomeDoctor = "Ellen Holanda";
//		Doctor doctor = repository.findByNome(nomeDoctor);
//		Assert.assertNotNull(doctor);
//		Assert.assertEquals(nomeDoctor, doctor.getName());
	}
	
	@Test
	public void naodeveriaCarregarumDoctorAoBucarPeloNome() {
		String nomeDoctor = "Aijalon";
//		Doctor doctor = repository.findByNome(nomeDoctor);
//		Assert.assertNull(doctor);
	}

}
