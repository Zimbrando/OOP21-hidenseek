package hidenseek.controller;

import java.util.Set;

public class GameWorldControllerImpl implements GameWorldController {

    private Gameloop loop;
    
    
    public GameWorldControllerImpl() {
        this.loop = new GameloopFXImpl() {
            @Override
            public void tick() {
                System.out.println("Start game loop");
                
                //Gestione degli input da tastiera e mouse
                //      Per ogni entity che ha InputComponent, aggiorniamo la lista di keys, pulsanti del mouse premuti e posizione del cursore
                //      Otteniamo direction e speed dalla entity
                
                
                //Gestione delle AI
                //      Per ogni entity che ha AIComponent, otteniamo direction e speed dalla AI
                
                //Gestione della posizione degli oggetti
                //      Calcolare la posizione al frame n+1 delle entity che hanno MovementComponent, in base alla loro direction e speed
                //      Controlla che la posizione calcolata sia possibile (ovvero non collide con nessun entity che non ha TriggerableComponent)
                //              Aggiornamento delle eventuale posizione di ogni entity con MovementComponent
                
                //Gestione di tutte le collisione eccetto i muri
                //Questa parte è effettuata logicamente nella sezione precedente, poichè viene eseguita solo per gli oggetti che si sono mossi.
                //      Trova tutti gli oggetti che si stanno intersecando e manda l'evento intersectionWith(entity) ad entrambi

                //
                
                System.out.println("End game loop");
            }  
        };
    }
}
