package base_de_datos_ejemplo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.ModelHelper;

public class Example {
	private ModelHelper model;
	
	@Before
	public void init() {
		this.model = new ModelHelper();
	}
	
	@Test
	public void contextUp() {
		assertNotNull(this.model.entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		this.model.withTransaction(() -> {});
	}
}
