package melchior.clement.back.entity.event.list.malus;

import java.util.ArrayList;

import melchior.clement.back.entity.event.ChoiceObject;
import melchior.clement.back.entity.event.EventCategoryEnum;
import melchior.clement.back.entity.event.EventChoiceObject;
import melchior.clement.back.entity.event.EventResume;
import melchior.clement.back.entity.object.MontainObject;
import melchior.clement.back.entity.object.list.Fusil;
import melchior.clement.back.entity.object.list.LSD;
import melchior.clement.back.entity.object.list.Viande;
import melchior.clement.back.entity.player.Player;
import melchior.clement.back.entity.player.PlayerStateEnum;
import melchior.clement.back.manager.EventManager;
import melchior.clement.back.manager.GameManager;
import melchior.clement.back.manager.InventoryManager;
import melchior.clement.back.manager.PlayerManager;
import melchior.clement.utilities.Mathematiques;

public class LoupEvent extends EventChoiceObject {
    private ArrayList <ChoiceObject> choices;
    
    public LoupEvent () {
        super("Attaque de loup", EventCategoryEnum.MALUS, 
            "Tapi dans notre grotte, espérant simplement survivre à une journée de plus dans cette nature impitoyable tout semblait se dérouler comme d'habitude, jusqu'à ce que le destin décide de vous jouer un tour assez sauvage. Alors que nous pensions que notre grotte était un royaume sûr, une meute de loups décide de faire irruption dans votre humble demeure. Oh, ils n'ont pas pris la peine de frapper à la porte ou de demander la permission, non ! Ils ont simplement décidé que nous serions leur prochain repas. Que devrions nous faire ?", 
            null, 5);
        this.choices = new ArrayList<>();
        this.choices.add(new ChoiceObject(0, false, "On peut essayer de leur lancer une côtelette de notre provision personnelle pour qu’il ne nous mange pas ?", new Viande()));
        this.choices.add(new ChoiceObject(1, false, "Ils vont voir de quoi on se chauffe, ils ne connaissent pas la poudre mais nous oui !", new Fusil()));
        this.choices.add(new ChoiceObject(2, false, "Nous pourrions peut être leur donner quelque pillule de lsd ?", new LSD()));
        this.choices.add(new ChoiceObject(3, false, "Nous allons nous battre et prier très fort l’ensemble des dieux de cet univers.", null));
    }

    @Override
    public void update () {
        for (ChoiceObject c : choices) {
            if (c.getObject() != null && InventoryManager.getInstance().isInIventory(c.getObject())) {
                c.setPossible(true);
            } else {
                c.setPossible(false);
            }
        }
    }

    @Override
    public void passEvent (ChoiceObject choice) {
        EventResume eventResume;
        switch (choice.getId()) {
            // Viande
            case 0:
                eventResume = passEventViande(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            // Fusil
            case 1:
                eventResume = passEventFusil(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            // LSD
            case 2:
                eventResume = passEventLSD(choice);
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
            default:
                eventResume = passEventNull();
                GameManager.getInstance().getNextDay().setEventResume(eventResume);
                break;
        }
        setLock(50);
        EventManager.getInstance().addEvent(this);
    }

    private EventResume passEventNull() {
        EventResume eventResume;

        String resume = "";
        int alea = Mathematiques.alea(2);

        // Three possibility
        if (alea==0) {
            resume = "Heureusement, nos cours de krav maga nous ont bien servi. Apres avoir mis un coup de pied retourné dans le chef de meute, les autres loups se sont enfuis. Le chef lui n’a pas eu de choix de s’enfuir à son tour face à leur incompétence et leur trahison.";     
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        } else if (alea == 1) {
            ArrayList <Player> playersLost = new ArrayList<>();
            Player p = PlayerManager.getInstance().getRandomPlayer(PlayerStateEnum.ALIVE, PlayerStateEnum.CRY);
            p.dead(p.getName() + " s'est battu pour sa vie et celle de son groupe. Malheureusement il n'a pas fait le poids fasse a une meute de loup...", PlayerStateEnum.GONE);
            playersLost.add(p);
            resume = "Évidemment ce qui devait arriver, arriva. Dans une lutte acharnée, les loups ont emporté " + p.getName() + " avec eux. Son cris de détresse à raisonné pendant plusieurs seconde dans la montagne avant qu’il ne disparaisse dans la pénombre de la nuit. C’est tragique.";   
            eventResume = new EventResume(this.getName(), resume, null, null, playersLost);
        } else {
            ArrayList <Player> playersLost = new ArrayList<>();
            Player p = PlayerManager.getInstance().getRandomPlayer(PlayerStateEnum.ALIVE, PlayerStateEnum.CRY);
            p.fallSick();
            resume = "La lutte était rude mais nous avons tout donné. Les loups ont fini par nous laisser mais ce n’est pas sans blessure que nous ressortons de ce combat.";   
            eventResume = new EventResume(this.getName(), resume, null, null, playersLost);  
        }

        return eventResume;
    }

    private EventResume passEventFusil(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "";
        int alea = Mathematiques.alea(1);

        // Two possibility
        if (alea==0) {
            resume = "Nous avons sorti le fusil, posé le doigt sur la gâchette puis abattu ce qui semblait etre le chef de meute. Avec le bruit et leur chef à terre, les autres loup ont décidé de repartir, complètement paniqué.";     
        } else {
            resume = "Ce maudit fusil nous a fait faux bon. Il s’est enrayé au mauvais moment. Le coup a quand même fini par partir et les a fait fuir mais un des loup a eu le temps de nous griffer et de nous blessé. Il faut soigner cette plaie.";     
            PlayerManager.getInstance().randomSick();
        }
        
        // Broke object
        if (Mathematiques.alea(10) > object.getSolidity()) {
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
            eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);
        } else {
            eventResume = new EventResume(this.getName(), resume, null, null, null);
        }

        return eventResume;
    }

    private EventResume passEventViande(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "";
        // Two possibility
        if (Mathematiques.alea(1)==0) {
            resume = "Merveilleux, les loups sont repartis avec notre bout de viande, je crois même qu’un d’eux a fait un léger mouvement de tête pour nous remercier.";     
            objectsLost.add(new Viande());
            InventoryManager.getInstance().remove(object);
        } else {
            resume = "Malheureusement, les loups avaient l’air d’avoir très faim et ces petits morceaux de viande ne leur ont pas suffit. Ils ont décidé de nous attaquer pour finir leur repas. Apres un combat acharné ils ont décidé de nous laisser tranquille mais l’un de nous a été blessé, il faut le soigner.";     
            PlayerManager.getInstance().randomSick();
            objectsLost.add(new Viande());
            InventoryManager.getInstance().remove(object);        
        }
        eventResume = new EventResume(this.getName(), resume, null, null, null);
        return eventResume;
    }

    private EventResume passEventLSD(ChoiceObject choice) {
        EventResume eventResume;
        ArrayList <MontainObject> objectsLost = new ArrayList<>();
        MontainObject object = choice.getObject();
        String resume = "";
        int alea = Mathematiques.alea(1);

        // Two possibility
        if (alea==0) {
            resume = "Apres avoir délicieusement avalé nos douces pillules de lsd, les loups sont d’un coup devenu très calins et amicaux, presque comme des chiens. Apres 1h de papouille animalière, ils sont repartis d’ou ils venaient. Ahhh la drogue.";     
            PlayerManager.getInstance().entertain(4);
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
        } else {
            resume = "Apres avoir délicieusement avalé nos douce pillule de lsd, les loups sont subitement devenu nerveux. Ils ont commencé à attaquer des endroits insoupçonnés de la grotte, comme s’ils avaient vu des fantômes. Pris de panique, ils ont finalement décidé de repartir la queue entre les jambes.";     
            InventoryManager.getInstance().remove(object);
            objectsLost.add(object);
        }
        eventResume = new EventResume(this.getName(), resume, objectsLost, null, null);

        return eventResume;
    }

    @Override
    public ArrayList<ChoiceObject> getChoices() {
        if (choices != null) {
            return choices;
        } else {
            return new ArrayList<ChoiceObject>();
        }
    }
}
