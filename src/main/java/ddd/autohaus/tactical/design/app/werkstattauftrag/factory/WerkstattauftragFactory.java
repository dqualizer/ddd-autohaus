package ddd.autohaus.tactical.design.app.werkstattauftrag.factory;

import ddd.autohaus.tactical.design.app.werkstattauftrag.model.Werkstattauftrag;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftraggeber.Auftraggeber;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftraggeber.AuftraggeberTypEnum;
import org.jmolecules.ddd.annotation.Factory;
import org.springframework.stereotype.Component;

@Factory
@Component
public class WerkstattauftragFactory {

    private int auftragsCounter = 3;

    public Werkstattauftrag create(AuftraggeberTypEnum auftraggeberTyp, Long auftraggeberId, String auftraggeberName, Long bearbeiterId) {
        Auftraggeber auftraggeber = createAuftraggeber(auftraggeberTyp, auftraggeberId, auftraggeberName);
        return new Werkstattauftrag(auftraggeber, bearbeiterId);
    }

    public Werkstattauftrag create(AuftraggeberTypEnum auftraggeberTyp, Long auftraggeberId, String auftraggeberName) {
        auftragsCounter++;
        return new Werkstattauftrag(auftragsCounter, createAuftraggeber(auftraggeberTyp, auftraggeberId, auftraggeberName));
    }

    private Auftraggeber createAuftraggeber(AuftraggeberTypEnum auftraggeberTyp, Long auftraggeberId, String auftraggeberName) {
        return new Auftraggeber(auftraggeberTyp, auftraggeberId, auftraggeberName);
    }
}