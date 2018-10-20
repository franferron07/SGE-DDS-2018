package testEntrega3;

import org.junit.Before;
import org.junit.Test;

import models.ModelHelper;
import reglasYActuadores.Regla;
import reglasYActuadores.ReglaCompuesta;
import reglasYActuadores.ReglaSimple;

public class ReglaDbTest3 {
	ModelHelper model;
	ReglaCompuesta regla1;
	ReglaCompuesta regla2;
	ReglaSimple regla3;
	
	
	@Before
	public void init() {
		this.model= new ModelHelper();
		this.regla1= new ReglaCompuesta("regla1");
		this.regla2= new ReglaCompuesta("regla2");
		this.regla3= new ReglaSimple("regla1");
		
		this.regla2.addRegla(regla3);
		this.regla1.addRegla(regla2);
		
	}
	
	@Test
	public void persistReglas() {
		this.model.agregar(regla1);
		this.model.agregar(regla2);
		this.model.agregar(regla3);
	}
}
