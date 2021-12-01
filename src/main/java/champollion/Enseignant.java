package champollion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Enseignant extends Personne {

    private Set<ServicePrevu> sp = new HashSet<ServicePrevu>();
    protected List<Intervention> lesInterventions = new ArrayList<Intervention>();

    public Enseignant(String nom, String email) {
        super(nom, email);

    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        int total = 0;
        for (ServicePrevu sp : sp) {
            total = (int) (total + sp.getVolumeCM() * 1.5 + sp.getVolumeTD() + sp.getVolumeTP() * 0.75);
        }
        return total;
    }

    public boolean enSousService() {
        boolean ret = false;
        if (this.heuresPrevues() < 192) {
            ret = true;
        }
        return ret;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE
     * spécifiée en "heures équivalent TD" Pour le calcul : 1 heure de cours
     * magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent
     * TD" 1 heure de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        int total = 0;
        for (ServicePrevu sp : sp) {
            if (sp.ue.equals(ue)) {
                total = (int) (total + sp.getVolumeCM() * 1.5 + sp.getVolumeTD() + sp.getVolumeTP() * 0.75);
            }
        }
        return total;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        ServicePrevu s = new ServicePrevu(ue, volumeCM, volumeTD, volumeTP);
        boolean ueExistant = false;
        for (ServicePrevu sp : sp) {
            if (sp.ue.equals(s.getUe())) {
                ueExistant = true;
            }
        }
        if (!ueExistant) {
            sp.add(s);
        }
        if(ueExistant){
            for (ServicePrevu sp : sp) {
                if (sp.ue.equals(s.getUe())) {
                    sp.setVolumeCM(volumeCM + sp.getVolumeCM());
                    sp.setVolumeTD(volumeTD + sp.getVolumeTD());
                    sp.setVolumeTP(volumeTP + sp.getVolumeTP());
            }
            }
            
        }

    }

    public void ajouterIntervention(Intervention i) {
        lesInterventions.add(i);
    }

    public int resteAPlanifier(UE ue, TypeIntervention type) {
        int heureAPlanifier = 0;
        int heurePlanifier = 0;
        for (Intervention lesInterventions : lesInterventions) {
            if (lesInterventions.getUe() == ue && lesInterventions.getType() == type) {
                heureAPlanifier = heureAPlanifier + lesInterventions.getDuree();
            }
        }
        for (ServicePrevu sp : sp) {
            if (sp.getUe() == ue) {
                if (type == TypeIntervention.CM) {
                    heurePlanifier = heurePlanifier + sp.getVolumeCM();
                }
                if (type == TypeIntervention.TD) {
                    heurePlanifier = heurePlanifier + sp.getVolumeTD();
                }
                if (type == TypeIntervention.TP) {
                    heurePlanifier = heurePlanifier + sp.getVolumeTP();
                }
            }
        }
        return heurePlanifier - heureAPlanifier;
    }
}
