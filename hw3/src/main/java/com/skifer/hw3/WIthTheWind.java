package com.skifer.hw3;

import com.skifer.hw3.machines.Helicopter;
import com.skifer.hw3.machines.PeekScooter;
import com.skifer.hw3.states.State;

public class WIthTheWind {

    public static void main(String[] args) {

        Helicopter vertushka = new Helicopter(1, 150000, "Boeing", "Douglas", "125");
        System.out.println(vertushka.getState() + " " + vertushka.getRentPrice() +  " " + vertushka.getRent());
        vertushka.setTailState(State.SLIGHTLY_DAMAGED);
        vertushka.setWingState(State.ACCEPTABLE);
        vertushka.setWheelState(State.PERFECT);
        System.out.println(vertushka.getState() + " " + vertushka.getRentPrice() +  " " + vertushka.getRent());
        vertushka.repairWings();
        System.out.println(vertushka.getState() + " " + vertushka.getRentPrice() +  " " + vertushka.getRent());
    }

}
