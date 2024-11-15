package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Cade Johnston
 * @author Samuel A. Rebelsky
 */

public class Quicksorter<T> implements Sorter<T> {
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
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
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
    partition(values, 0, values.length - 1);
  } // sort(T[])

  /**
   * Find a partition and split the subarray by it.
   * All values to the left of the partition are less.
   * All values to the right of the partition are greater.
   *
   * @param values
   *   The array (which contains the subarray).
   * @param lb
   *   The lower bound of the subarray.
   * @param ub
   *   The upper bound of the subarray.
   */
  private void partition(T[] values, int lb, int ub) {
    if (ub > lb) {
      int pivot = (int) (Math.random() * (ub - lb + 1)) + lb;
      int[] bounds = dnfSort(values, values[pivot], lb, ub);
      partition(values, lb, bounds[0]);
      partition(values, bounds[1], ub);
    } // if
  } // partition(T[], int, int)

  /**
   * Sort an array according to a solution to the Dutch Nation Flag
   * pproblem (As discussed in class). Returns the bounds of the
   * array that are equal to the pivot.
   *
   * @param values
   *   The array.
   * @param pivot
   *   The pivot value to compare other values to.
   * @param lb
   *   The lower bound of the subarray.
   * @param ub
   *   The upper bound of the subarray.
   * @return
   *   The bounds of the array that are equal to the pivot.
   */
  private int[] dnfSort(T[] values, T pivot, int lb, int ub) {
    int less = lb;
    int equal = lb;
    int greater = ub;
    while (equal <= greater) {
      if (order.compare(values[equal], pivot) == 0) {
        equal++;
      } else if (order.compare(values[equal], pivot) > 0) {
        swap(values, greater--, equal);
      } else {
        swap(values, less++, equal++);
      } // if / else if / else
    } // while
    return new int[]{less, equal};
  } // dnfSort(T[], T, int, int)

  /**
   * Swap the values at two places in an array.
   *
   * @param values
   *   The array.
   * @param indexA
   *   The index of the first value.
   * @param indexB
   *   The index of the second value.
   */
  private void swap(T[] values, int indexA, int indexB) {
    T temp = values[indexA];
    values[indexA] = values[indexB];
    values[indexB] = temp;
  } // swap(T[], int, int)
} // class Quicksorter
