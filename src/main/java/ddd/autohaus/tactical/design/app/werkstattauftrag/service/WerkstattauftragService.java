package ddd.autohaus.tactical.design.app.werkstattauftrag.service;

import ddd.autohaus.tactical.design.app.werkstattauftrag.factory.WerkstattauftragFactory;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.Werkstattauftrag;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftraggeber.AuftraggeberTypEnum;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftragsstatus.WerkstattauftragstatusEnum;
import ddd.autohaus.tactical.design.app.werkstattauftrag.repository.WerkstattauftragRepository;
import org.springframework.stereotype.Service;

@Service
public class WerkstattauftragService {

    private final WerkstattauftragFactory factory;
    private final WerkstattauftragRepository repository;

    public WerkstattauftragService(WerkstattauftragFactory factory, WerkstattauftragRepository repository) {
        this.factory = factory;
        this.repository = repository;
    }

    public Werkstattauftrag readWerkstattauftrag(Long werkstattauftragsnummer) {
        return this.repository.findById(werkstattauftragsnummer);
    }

    public Werkstattauftrag createWerkstattauftrag(String typ, Long auftraggeberId, String auftraggeberName, Long bearbeiterId) {
        Werkstattauftrag werkstattauftrag = factory.create(AuftraggeberTypEnum.valueOf(typ), auftraggeberId, auftraggeberName, bearbeiterId);
        return this.repository.save(werkstattauftrag);
    }

    public Werkstattauftrag createWerkstattauftrag(String typ, Long auftraggeberId, String auftraggeberName) {
        Werkstattauftrag werkstattauftrag = factory.create(AuftraggeberTypEnum.valueOf(typ), auftraggeberId, auftraggeberName);
        return this.repository.save(werkstattauftrag);
    }

    public void auftragsstatusAendern(Long auftragsnummer, String newWerkstattauftragsstatus) {
        Werkstattauftrag werkstattauftrag = this.repository.findById(auftragsnummer);
        werkstattauftrag.auftragsstatusAendern(WerkstattauftragstatusEnum.valueOf(newWerkstattauftragsstatus));
    }
}