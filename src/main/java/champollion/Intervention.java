package champollion;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mario
 */
public class Intervention {
    private Enseignant enseignant;
    private Date debut;
    private int duree;
    private boolean annulee = false;
    private int heureDebut ;
    private UE ue;
    private TypeIntervention type;
    
    public Intervention(Enseignant e, Date debut, int duree, int heureDebut, UE ue, TypeIntervention type) {
        this.debut = debut;
        this.duree = duree;
        this.heureDebut = heureDebut;
        this.ue=ue;
        this.type=type;
        this.enseignant = e;
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public UE getUe() {
        return ue;
    }

    public TypeIntervention getType() {
        return type;
    }
    
    
}
