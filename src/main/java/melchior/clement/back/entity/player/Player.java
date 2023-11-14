package melchior.clement.back.entity.player;

import melchior.clement.back.entity.expedition.Expedition;

public class Player {
    // constant
    public static int MAX_FOOD = 7;
    public static int MAX_ENTERTAINMENT = 8;
    public static int MAX_HEAT = 4;

    private int id;
    private int food;
    private int entertainment;
    private int heat;
    private double sick;
    private Expedition expedition;
    private int daySick;
    private String name;
    private PlayerStateEnum state;
    private String report;
    private boolean isEating = false;
    private boolean isHeating = false;
    private boolean isEntertain = false;

    public Player(int id, String name) {
        this.id = id;
        this.food = MAX_FOOD;
        this.entertainment = MAX_ENTERTAINMENT;
        this.heat = MAX_HEAT;
        this.name = name;
        this.state = PlayerStateEnum.ALIVE;
        this.report = "";
    }

    public void nextDay () {
        switch (this.state) {
            case ALIVE:
            case CRY:
                nextDayFood();
                nextDayEntertainment();
                nextDayHeat();
                break;
            case SICK:
                nextDayFood();
                nextDayEntertainment();
                nextDayHeat();
                nextDaySick();
            default:
                break;
        }
    }

    public boolean isAlive () {
        if (this.state == PlayerStateEnum.ALIVE ||
            this.state == PlayerStateEnum.CRY ||
            this.state == PlayerStateEnum.SICK) {
            return true;
        } else {
            return false;
        }
    }

    public void resetReport () {
        this.report = "";
    }

    // ------------------- FOLLOWING DAY -------------------

    private void nextDayFood () {
        this.food = this.food - 1;
        if (this.food < 0) {
            dead (this.name + " avait trop faim et n'a pas survecu. Au revoir " + this.name + ". \n", PlayerStateEnum.DEAD);
        } else if (this.food < 2) {
            this.report = this.report + this.name + " est affamé. Il faut faire quelque chose ou nous allons le perdre. \n";
        } else if (this.food < 5) {
            this.report = this.report + this.name + " a tres faim, il faut s'occuper de son cas. \n";
        } else {
            this.report = this.report + this.name + " n'a pas specialement faim, la survie se passe bien. \n";
        }
    }

    private void nextDayHeat () {
        this.heat = this.heat - 1;
        if (this.heat < 0) {
            dead ("Malheureusement, le froid a emporté notre cher" + this.name + ". \n", PlayerStateEnum.ICED);
        } else if (this.heat < 1) {
            this.report = this.report + this.name + " est vraiment congelé, il faut faire quelque chose avant qu'il ne soit trop tard. \n";
        } else if (this.heat < 2) {
            this.report = this.report + this.name + " a tres froid, il faut qu'il se réchauffe. \n";
        } else {
            this.report = this.report + this.name + " se sent plutot bien pour la situation. \n";
        }
    }

    private void nextDayEntertainment () {
        this.entertainment = this.entertainment - 1;
        if (this.entertainment < 0) {
            this.report = this.name + " a complétement pété un cable et a décidé de nous quitter pendant la nuit. Souhaitons lui bonne chance pour ses futures aventures ! \n";
            this.state = PlayerStateEnum.GONE;
        } else if (this.entertainment < 2) {
            this.report = this.report + this.name + " devient vraiment taré, il va falloir s'occuper de son cas. \n";
        } else if (this.entertainment < 4) {
            this.report = this.report + this.name + " ne supporte pas bien l'isolement. Un petit jeu ne lui ferait pas de mal. \n";
        } else {
            this.report = this.report + this.name + " est détendu. \n";
        }
    }

    private void nextDaySick() {
        this.sick = this.sick + Math.random();
        if (this.sick > 5.0) {
            dead (this.name + "a complétement pété un cable et a décidé de nous quitter pendant la nuit. Souhaitons lui bonne chance pour ses futures aventures ! \n", PlayerStateEnum.DEAD);
        } else if (this.sick > 3.0) {
            this.report = this.report + "La situation pour " + this.name + " se degrade, il faut le soigner, vite ! \n";
        } else {
            this.report = this.report + this.name + " est malade, il lui faut des soins. \n";
        }
    }

    public void fallSick () {
        this.sick = 0;
        this.state = PlayerStateEnum.SICK;
    }

    // ------------------- USE OBJECT -------------------


    public void dead (String msg, PlayerStateEnum state) {
        this.report = msg;
        this.state = state;
    }


    public void eat (int num) {
        if (this.state == PlayerStateEnum.ALIVE || this.state == PlayerStateEnum.SICK) {
            if (this.food+num <= MAX_FOOD) {
                this.food = this.food + num;
            } else {
                this.food = MAX_FOOD;
            }
        }
    }

    public void heat (int num) {
        if (this.state == PlayerStateEnum.ALIVE || this.state == PlayerStateEnum.SICK) {
            if (this.heat+num <= MAX_HEAT) {
                this.heat = this.heat + num;
            } else {
                this.heat = MAX_HEAT;
            }
        }
    }

    public void entertain (int num) {
        if (this.state == PlayerStateEnum.ALIVE || this.state == PlayerStateEnum.SICK) {
            if (this.entertainment+num <= MAX_ENTERTAINMENT) {
                this.entertainment = this.entertainment + num;
            } else {
                this.entertainment = MAX_ENTERTAINMENT;
            }
        }
    }

    public String toString () {
        return this.name;
    }


    // Getter and Setter 
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMAX_FOOD() {
        return MAX_FOOD;
    }
    public int getMAX_ENTERTAINMENT() {
        return MAX_ENTERTAINMENT;
    }
    public int getMAX_HEAT() {
        return MAX_HEAT;
    }
    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public int getEntertainment() {
        return entertainment;
    }
    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }
    public int getHeat() {
        return heat;
    }
    public void setHeat(int heat) {
        this.heat = heat;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public PlayerStateEnum getState() {
        return state;
    }
    public void setState(PlayerStateEnum state) {
        this.state = state;
    }
    public String getReport() {
        return report;
    }
    public void setReport(String report) {
        this.report = report;
    }
    public Expedition getExpedition() {
        return expedition;
    }
    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }
    public int getDaySick() {
        return daySick;
    }
    public void setDaySick(int daySick) {
        this.daySick = daySick;
    }
    public double getSick() {
        return sick;
    }
    public void setSick(double sick) {
        this.sick = sick;
    }
    public boolean isEating() {
        return isEating;
    }
    public void setEating(boolean isEating) {
        this.isEating = isEating;
    }
    public boolean isHeating() {
        return isHeating;
    }
    public void setHeating(boolean isHeating) {
        this.isHeating = isHeating;
    }
    public boolean isEntertain() {
        return isEntertain;
    }
    public void setEntertain(boolean isEntertain) {
        this.isEntertain = isEntertain;
    }
}
