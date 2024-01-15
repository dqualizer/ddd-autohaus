package ddd.autohaus.tactical.design.app.werkstattauftrag.api;

import ddd.autohaus.tactical.design.app.werkstattauftrag.model.Werkstattauftrag;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftraggeber.Auftraggeber;
import ddd.autohaus.tactical.design.app.werkstattauftrag.model.auftragsstatus.Auftragsstatus;
import ddd.autohaus.tactical.design.app.werkstattauftrag.service.WerkstattauftragService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auftrag")
public class WerkstattauftragController {

    @Autowired
    private WerkstattauftragService service;

    @GetMapping(path = "/{auftragsnummer}")
    public ResponseEntity<Werkstattauftrag> readAuftrag(@PathVariable(value = "auftragsnummer") long auftragsNummer) {
        Werkstattauftrag auftrag = service.readWerkstattauftrag(auftragsNummer);

        if(auftrag != null) return new ResponseEntity<>(auftrag, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/new", produces = "application/json")
    public ResponseEntity<Werkstattauftrag> createAuftrag(@RequestBody Auftraggeber auftraggeber) {
        String typ = auftraggeber.getTyp().getValue();
        long auftraggeberID = auftraggeber.getId();
        String auftraggeberName = auftraggeber.getName();

        Werkstattauftrag auftrag = service.createWerkstattauftrag(typ, auftraggeberID, auftraggeberName);
        return new ResponseEntity<>(auftrag, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{auftragsnummer}", produces = "application/json")
    public ResponseEntity<String> updateStatus(@RequestBody Auftragsstatus status, @PathVariable(value = "auftragsnummer") long auftragsNummer) {
        String neuerStatus = status.getStatus();
        service.auftragsstatusAendern(auftragsNummer, neuerStatus);

        String response = "Auftrag wurde angepasst. Neuer Status: " + neuerStatus;
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}