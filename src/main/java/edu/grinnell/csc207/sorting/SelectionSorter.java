package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Cade Johnston
 * @author Samuel A. Rebelsky
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
      select(values, i, values.length);
    } // for[i]
  } // sort(T[])

  /**
   * Find the lowest value between lb and ub. Swap that value
   * with the value at lb.
   *
   * @param values
   *   The array.
   * @param lb
   *   The lower bound of the subarray.
   * @param ub
   *   The upper bound of the subarray.
   */
  private void select(T[] values, int lb, int ub) {
    int lowest = lb;
    for (int i = lb + 1; i < ub; i++) {
      if (order.compare(values[lowest], values[i]) > 0) {
        lowest = i;
      } // if
    } // for [i]
    T temp = values[lb];
    values[lb] = values[lowest];
    values[lowest] = temp;
  } // select (T[], int, int)
} // class SelectionSorter
