package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Cade Johnston
 */

public class InsertionSorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public InsertionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using insertion sort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    for (int i = 0; i < values.length; i++) {
      insert(values, i);
    } // for[i]
  } // sort(T[])

  /**
   * For each value to the left of the value at index, see if
   * the value at index is lesser. As soon as it is not, shift
   * all value between the two to the right and insert it at
   * the open spot.
   *
   * @param values
   *   The array to sort a value of.
   * @param index
   *   The index to insert into the sorted portion of the list.
   */
  private void insert(T[] values, int index) {
    T temp;
    for (int i = index; i > 0; i--) {
      if (order.compare(values[i], values[i - 1]) < 0) {
        temp = values[i];
        values[i] = values[i - 1];
        values[i - 1] = temp;
      } // if
    } // for [i]s
  } // insert(T[], int, int)
} // class InsertionSorter
