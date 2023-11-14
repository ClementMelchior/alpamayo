package melchior.clement.back.entity.object;

import melchior.clement.utilities.Mathematiques;

public class MontainObject {
    private int id;
    private String name;
    private int solidity;
    private int food;
    private int heat;
    private int entertainment;
    private MontainObjectCategoryEnum category;
    private int number;

    public MontainObject(int id, String name, int solidity, int food, int heat, int entertainment, MontainObjectCategoryEnum category, int number) {
        this.id = id;
        this.name = name;
        this.solidity = solidity;
        this.food = food;
        this.heat = heat;
        this.entertainment = entertainment;
        this.category = category;
        this.number = number;
    }

    /*
     * Return false if object is broken, true otherwise
     */
    public boolean use() {
        if (Mathematiques.alea(10) > solidity) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

    public boolean equals (MontainObject o) {
        if (o.getId() == this.getId()) {
            return true;
        } else {
            return false;
        }
    }


    // getter and setter 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSolidity() {
        return solidity;
    }
    public void setSolidity(int solidity) {
        this.solidity = solidity;
    }
    public int getFood() {
        return food;
    }
    public void setFood(int food) {
        this.food = food;
    }
    public int getHeat() {
        return heat;
    }
    public void setHeat(int heat) {
        this.heat = heat;
    }
    public int getEntertainment() {
        return entertainment;
    }
    public void setEntertainment(int entertainment) {
        this.entertainment = entertainment;
    }
    public MontainObjectCategoryEnum getCategory() {
        return category;
    }
    public void setCategory(MontainObjectCategoryEnum category) {
        this.category = category;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
