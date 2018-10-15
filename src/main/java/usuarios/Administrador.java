package usuarios;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="administrador")
@DiscriminatorValue("administrador")
public class Administrador extends Usuario {
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name="id")
	private Usuario usuario;

	@Column(name="fechaAltaSistema")
	private Date fechaAltaSistema;
	@Column(name="identificadorSistema")
	private int identificadorSistema;

	public Administrador() {

	}
	
	public long cantidadMesesComoAdmin(){
		return getDiffDates(fechaAltaSistema,new Date(),1);
	}


	public int getIdentificadorSistema() {
		return identificadorSistema;
	}

	public void setIdentificadorSistema(int identificadorSistema) {
		this.identificadorSistema = identificadorSistema;
	}
	
	private synchronized long getDiffDates(Date fechaInicio, Date fechaFin, int tipo) {
		// Fecha inicio
		Calendar calendarInicio = Calendar.getInstance();
		calendarInicio.setTime(fechaInicio);
		int diaInicio = calendarInicio.get(Calendar.DAY_OF_MONTH);
		int mesInicio = calendarInicio.get(Calendar.MONTH) + 1; // 0 Enero, 11 Diciembre
		int anioInicio = calendarInicio.get(Calendar.YEAR);	 
		// Fecha fin
		Calendar calendarFin = Calendar.getInstance();
		calendarFin.setTime(fechaFin);
		int diaFin = calendarFin.get(Calendar.DAY_OF_MONTH);
		int mesFin = calendarFin.get(Calendar.MONTH) + 1; // 0 Enero, 11 Diciembre
		int anioFin = calendarFin.get(Calendar.YEAR);
	 
		int anios = 0;
		int mesesPorAnio = 0;
		int diasPorMes = 0;
		int diasTipoMes = 0;
		// Calculo de dias del mes
		if (mesInicio == 2) {
			// Febrero
			if ((anioFin % 4 == 0) && ((anioFin % 100 != 0) || (anioFin % 400 == 0))) {
				// Bisiesto
				diasTipoMes = 29;
			} else {
				// No bisiesto
				diasTipoMes = 28;
			}
		} else if (mesInicio <= 7) {
			// De Enero a Julio los meses pares tienen 30 y los impares 31
			if (mesInicio % 2 == 0) {
				diasTipoMes = 30;
			} else {
				diasTipoMes = 31;
			}
		} else if (mesInicio > 7) {
			// De Julio a Diciembre los meses pares tienen 31 y los impares 30
			if (mesInicio % 2 == 0) {
				diasTipoMes = 31;
			} else {
				diasTipoMes = 30;
			}
		}
		// Calculo de diferencia de ano, mes y dia
		//
		if ((anioInicio > anioFin) || (anioInicio == anioFin && mesInicio > mesFin)
				|| (anioInicio == anioFin && mesInicio == mesFin && diaInicio > diaFin)) {
			// La fecha de inicio es posterior a la fecha fin
			 System.out.println("La fecha de inicio ha de ser anterior a la fecha fin");
			return -1;
		} else {
			if (mesInicio <= mesFin) {
				anios = anioFin - anioInicio;
				if (diaInicio <= diaFin) {
					mesesPorAnio = mesFin - mesInicio;
					diasPorMes = diaFin - diaInicio;
				} else {
					if (mesFin == mesInicio) {
						anios = anios - 1;
					}
					mesesPorAnio = (mesFin - mesInicio - 1 + 12) % 12;
					diasPorMes = diasTipoMes - (diaInicio - diaFin);
				}
			} else {
				anios = anioFin - anioInicio - 1;
				System.out.println(anios);
				if (diaInicio > diaFin) {
					mesesPorAnio = mesFin - mesInicio - 1 + 12;
					diasPorMes = diasTipoMes - (diaInicio - diaFin);
				} else {
					mesesPorAnio = mesFin - mesInicio + 12;
					diasPorMes = diaFin - diaInicio;
				}
			}
		}
		
		long returnValue = -1;
	 
		switch (tipo) {
			case 0:
				// Total Anos
				returnValue = anios;
			    System.out.println("Total a�os: " + returnValue + " A�os.");
				break;
	 
			case 1:
				// Total Meses
				returnValue = anios * 12 + mesesPorAnio;
				System.out.println("Total meses: " + returnValue + " Meses.");
				break;
	 
			case 2:
				// Total Dias (se calcula a partir de los milisegundos por dia)
				long millsecsPerDay = 86400000; // Milisegundos al dia
				returnValue = (fechaFin.getTime() - fechaInicio.getTime()) / millsecsPerDay;
				System.out.println("Total d�as: " + returnValue + " D�as.");
				break;
	 
			case 3:
				// Meses del ano
				returnValue =mesesPorAnio;
				System.out.println("Meses del a�o: " + returnValue);
				break;
	 
			default:
				break;
		}	 
		return returnValue;
	}
	
	
}