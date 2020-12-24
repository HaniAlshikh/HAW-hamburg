package de.hawhamburg.hamann.rc;

import java.util.*;
import java.util.Map.Entry;

public class Main {

    private static int numberOfChecksDFS = 0;
    private static int solvesDFS = 0;
    private static int solvesBFS = 0;

    public static void main(String[] args) {
        int numMonsterStart = 5;
        int numChildrenStart = 5;
        int seatsInBoat = 3;

        int[] numMonster = new int[RiverSide.values().length];
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
        numberOfChecksDFS = 0;
        evaluateMovesDFS(startState, pastMoves);
        System.out.println("Number of Checks (DFS) " + numberOfChecksDFS);
        evaluateMovesBFS(startState);
        System.out.println("Solves BFS / DFS: " + solvesBFS + "  |  " + solvesDFS);
    }

    private static void evaluateMovesDFS(RcState currentState, Stack<RcState> pastMoves) {
        ArrayList<RcState> moves = currentState.getPossibleMoves();
        // Wir sortieren hier absteigend, um die Gewinner am Anfang zu haben
        moves.sort((RcState m1, RcState m2) -> Integer.compare(m2.score(), m1.score()));

        for (RcState state : moves) {
            numberOfChecksDFS++;
            if (state.score() == RcState.STATE_SUCCESS) {
                solvesDFS++;
                continue;
            }

            if (state.score() > RcState.STATE_INVALID) {
                if (!pastMoves.contains(state)) {
                    pastMoves.push(state);
                    evaluateMovesDFS(state, pastMoves);
                    pastMoves.pop();
                }
            }
        }
    }

    private static void evaluateMovesBFS(RcState startState) {

        Queue<Entry<RcState, Stack<RcState>>> stateQueue = new ArrayDeque<>();
        stateQueue.add(Map.entry(startState, new Stack<>()));

        int numberOfChecks = 0;

        while (!stateQueue.isEmpty()) {
            numberOfChecks++;
            Entry<RcState, Stack<RcState>> currentState = stateQueue.poll();
            Stack<RcState> pastMoves = currentState.getValue();

            if (currentState.getKey().score() == RcState.STATE_SUCCESS) {
                solvesBFS++;
                continue;
            }

            if (currentState.getKey().score() > RcState.STATE_INVALID) {
                // Wir sortieren hier absteigend, um die Gewinner am Anfang zu haben
                List<RcState> moves = currentState.getKey().getPossibleMoves();
                moves.sort((RcState m1, RcState m2) -> Integer.compare(m2.score(), m1.score()));

                for (RcState childState : moves) {
                    if (!pastMoves.contains(childState)) {
                        Stack<RcState> copyStack = new Stack<>();
                        copyStack.addAll(pastMoves);
                        copyStack.push(currentState.getKey());
                        stateQueue.add(Map.entry(childState, copyStack));
                    }
                }
            }
        }
        System.out.println("Number of checks (BFS): " + numberOfChecks);
    }
}
