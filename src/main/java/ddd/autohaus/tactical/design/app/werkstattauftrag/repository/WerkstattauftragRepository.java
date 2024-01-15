package ddd.autohaus.tactical.design.app.werkstattauftrag.repository;

import ddd.autohaus.tactical.design.app.werkstattauftrag.model.Werkstattauftrag;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftraggeber.Auftraggeber;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftraggeber.AuftraggeberTypEnum;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftragsstatus.WerkstattauftragstatusEnum;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Repository
@Component
public class WerkstattauftragRepository {

    private final Map<Long, Werkstattauftrag> auftragsListe = new HashMap<>();

    public WerkstattauftragRepository() {
        Auftraggeber auftraggeber1 = new Auftraggeber(AuftraggeberTypEnum.INTERN_LAGER, 101L, "Thomas AG");
        Auftraggeber auftraggeber2 = new Auftraggeber(AuftraggeberTypEnum.KUNDE_PRIVAT, 102L,"Leonard");

        Werkstattauftrag auftrag1 = new Werkstattauftrag(1L, auftraggeber1, WerkstattauftragstatusEnum.ANGELEGT);
        Werkstattauftrag auftrag2 = new Werkstattauftrag(2L, auftraggeber1, WerkstattauftragstatusEnum.EINGEPLANT);
        Werkstattauftrag auftrag3 = new Werkstattauftrag(3L, auftraggeber2, WerkstattauftragstatusEnum.ANGELEGT);

        this.auftragsListe.put(auftrag1.getWerkstattauftragsnummer(), auftrag1);
        this.auftragsListe.put(auftrag2.getWerkstattauftragsnummer(), auftrag2);
        this.auftragsListe.put(auftrag3.getWerkstattauftragsnummer(), auftrag3);
    }

    public Werkstattauftrag findById(Long value) {
        return this.auftragsListe.get(value);
    }

    public Werkstattauftrag save(Werkstattauftrag werkstattauftrag) {
        long auftragsNummer = werkstattauftrag.getWerkstattauftragsnummer();
        this.auftragsListe.put(auftragsNummer, werkstattauftrag);

        return this.auftragsListe.get(auftragsNummer);
    }
}