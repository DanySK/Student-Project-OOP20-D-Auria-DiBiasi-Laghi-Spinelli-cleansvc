package model;

import java.time.*;


public class TimeToCleanValuation {
    
    private final static Duration LAVAGGIO = Duration.ofSeconds(60);
    private final static Duration DISINFEZIONE = Duration.ofSeconds(20);
    private final static Duration RISCIACQUO = Duration.ofSeconds(30);
    private Integer numFasi;
    private Integer numDipendenti;
    private Integer mqStanza;
    
    public TimeToCleanValuation(Integer numFasi, Integer numDipendeti, Integer mqStanza) {
        this.numFasi = numFasi;
        this.numDipendenti = numDipendeti;
        this.mqStanza = mqStanza;
        
    }
    
    public long coreValuation() {                     
        Duration timeToRoom =  TimeToCleanValuation.LAVAGGIO.multipliedBy(this.getMqStanza())
                                                                .dividedBy(this.getNumDipendenti());
        
        return timeToRoom.toMinutes();
    }
    
    public int getNumFasi() {
        return numFasi;
    }

    public void setNumFasi(int numFasi) {
        this.numFasi = numFasi;
    }

    public int getNumDipendenti() {
        return numDipendenti;
    }

    public void setNumDipendenti(int numDipendenti) {
        this.numDipendenti = numDipendenti;
    }

    public int getMqStanza() {
        return mqStanza;
    }

    public void setMqStanza(int mqStanza) {
        this.mqStanza = mqStanza;
    }
    
}
