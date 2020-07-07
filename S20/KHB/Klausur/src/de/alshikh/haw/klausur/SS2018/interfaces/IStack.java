package de.alshikh.haw.klausur.SS2018.interfaces;

import java.util.Optional;

public interface IStack<E> {
  /**
   * Die default Anfangskapazität des Stacks von 11.
   */
  int INITIAL_CAPACITY = 11;
  /**
   * Lege ein neues Element auf den Stack.
   * @param element Das neue Element
   */
  void push(E element);

  /**
   * Liefert das oberste Element des Stacks zurück und entfernt es vom Stack.
   * @return Das oberste Element des Stacks
   */
  E pop();
  /**
   * Liefert das oberste Element des Stacks zurück und entfernt es vom Stack.
   * @return Das oberste Element des Stacks
   */
  Optional<E> peek();
  /**
   * Liefert die Anzahl Elemente im Stack.
   * @return Die Anzahl Elemente auf dem Stack
   */
  int size();
  /**
   * Liefert die aktuelle Kapazität des Stacks.
   * @return Die aktuelle Kapazität des Stacks
   */
  int capacity();
}