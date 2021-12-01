package champollion;

import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {
	Enseignant untel;
	UE uml, java;
		
	@BeforeEach
	public void setUp() {
		untel = new Enseignant("untel", "untel@gmail.com");
		uml = new UE("UML");
		java = new UE("Programmation en java");		
	}
	

	@Test
	public void testNouvelEnseignantSansService() {
		assertEquals(0, untel.heuresPrevues(),
                        "Un nouvel enseignant doit avoir 0 heures prévues");
	}
	
	@Test
	public void testAjouteHeures() {
                // 10h TD pour UML
		untel.ajouteEnseignement(uml, 0, 10, 0);

		assertEquals(10, untel.heuresPrevuesPourUE(uml),
                        "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

                // 20h TD pour UML
                untel.ajouteEnseignement(uml, 0, 20, 0);
                
		assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                         "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");		
		
	}
        
        @Test
        public void testHeuresPrevues(){
            untel.ajouteEnseignement(uml, 10, 10, 0);
            
            assertEquals(25, untel.heuresPrevues(),
                    "Les heures prevues sont incorrectes");
        }
        
        @Test
        public void testEnSousServiceTrue(){
            untel.ajouteEnseignement(uml, 10, 10, 0);

            assertEquals(true, untel.enSousService(),
                        "L'enseigant doit être en sous service");
            
        }
        
        public void testEnSousServiceFalse(){
            untel.ajouteEnseignement(uml,193 , 0, 0);
            
            assertEquals(false, untel.enSousService(),
                        "L'enseigant ne doit pas être en sous service");
            
        }
        
        @Test
        public void testAjouterIntervention(){

            Intervention i =  new  Intervention(untel, new Date(), 2, 2, uml, TypeIntervention.CM);

            untel.ajouterIntervention(i);
            
            assertEquals(i, untel.lesInterventions.get(0),
                    "L'intervention n'a pas correctement été ajoutée");            
        }
        
        @Test
        public void testResteAPlanifier(){
 
            Intervention i =  new  Intervention(untel, new Date(), 2, 2, uml, TypeIntervention.CM);
            untel.ajouterIntervention(i);
            
            Intervention j =  new  Intervention(untel, new Date(), 2, 2, uml, TypeIntervention.TD);
            untel.ajouterIntervention(j);
            
            Intervention k =  new  Intervention(untel, new Date(), 2, 2, uml, TypeIntervention.TP);
            untel.ajouterIntervention(k);
            
            
            untel.ajouteEnseignement(uml, 4, 4, 4);
            
            
            assertEquals(2, untel.resteAPlanifier(uml, TypeIntervention.CM),
                    "Le nombre d'heures de CM restantes à planifier n'est pas juste");
            
            assertEquals(2, untel.resteAPlanifier(uml, TypeIntervention.TD),
                    "Le nombre d'heures de TD restantes à planifier n'est pas juste");
            
            assertEquals(2, untel.resteAPlanifier(uml, TypeIntervention.TP),
                    "Le nombre d'heures de TP restantes à planifier n'est pas juste");
        }
	
}
