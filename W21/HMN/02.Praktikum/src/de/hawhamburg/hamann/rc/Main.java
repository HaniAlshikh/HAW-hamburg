package de.hawhamburg.hamann.rc;

import de.hawhamburg.hamann.collections.HAWList;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    int numMonsterStart  = 3;
        int numChildrenStart = 3;
        int seatsInBoat = 2;

        int[] numMonster  = new int[RiverSide.values().length];
        int[] numChildren = new int[RiverSide.values().length];

        numMonster[RiverSide.LEFT.ordinal()] = numMonsterStart;
        numChildren[RiverSide.LEFT.ordinal()] = numChildrenStart;

        // Der Startzustand des Spiels
        RcState startState = new RcState(
                seatsInBoat,
                numChildren,
                numMonster,
                numChildrenStart,
                numMonsterStart,
                RiverSide.LEFT);

        Stack<RcState> pastMoves = new Stack<>();
        pastMoves.push(startState);
        evaluateMoves(startState, pastMoves);
    }

    private static void evaluateMoves(RcState currentState, Stack<RcState> pastMoves) {

        HAWList<RcState> moves = currentState.getPossibleMoves();
        // Wir sortieren hier absteigend, um die Gewinner am Anfang zu haben
        moves.sort((RcState m1, RcState m2) -> Integer.compare(m2.score(), m1.score()));

        for (RcState state : moves) {
            if (state.score() == RcState.STATE_SUCCESS) {
                System.out.println("Found a solution:");
                System.out.println(state.toString());
                Stack<RcState> copyStack = new Stack<>();
                copyStack.addAll(pastMoves);
                while (!copyStack.empty()) {
                    System.out.println(copyStack.pop());
                }
                System.out.println();

                continue;
            }

            if (state.score() > RcState.STATE_INVALID) {
                if (!pastMoves.contains(state)) {
                    pastMoves.push(state);
                    evaluateMoves(state, pastMoves);
                    pastMoves.pop();
                }
            }
        }
    }
}
