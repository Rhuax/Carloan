package business.entity.Manutenzione;

import java.util.Date;

public class ManutenzioneStraordinaria extends Manutenzione{
	public ManutenzioneStraordinaria(String iDManutenzione, Date datainizio,
			Date dataFine, String note) {
		super(iDManutenzione, datainizio, dataFine, note);
	}
}
