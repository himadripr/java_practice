package org.himadri.practice.java_practice.vending_machine;
/**
  * Items or products supported by Vending Machine.
  * @author Javin Paul
  */
public enum Item{
    COKE("Coke", 25), PEPSI("Pepsi", 35), SODA("Soda", 45);
   
    private String name;
    private int price;
   
    private Item(String name, int price){
        this.name = name;
        this.price = price;
    }
   
    public String getName(){
        return name;
    }
   
    public long getPrice(){
        return price;
    }
}



//Read more: https://javarevisited.blogspot.com/2016/06/design-vending-machine-in-java.html#ixzz5nQjwRnXV