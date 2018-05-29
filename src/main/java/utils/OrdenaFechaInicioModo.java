package utils;

import java.time.LocalDateTime;
import java.util.Comparator;

import entities.Modo;

public class OrdenaFechaInicioModo implements Comparator<Object> {
	public int compare(Object obj1, Object obj2) {
		Modo p1 = (Modo) obj1;
		Modo p2 = (Modo) obj2;
		LocalDateTime desc1 = p1.getFechaHoraInicio();
		LocalDateTime desc2 = p2.getFechaHoraInicio();
		int valor = 0;
		if (desc1 != null && desc2 != null) {
			valor = desc1.compareTo(desc2);
		} else
			valor = 0;
		return valor;
	}

	public boolean equals(Object o) {
		return this == o;
	}
}
