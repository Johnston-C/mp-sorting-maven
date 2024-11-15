package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Cade Johnston
 * @author Samuel A. Rebelsky
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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
    merge(values, 0, values.length);
  } // sort(T[])

  /**
   * Sort each half of an array, then call a method to
   * splice them together, sorting them in the process.
   *
   * @param values
   *   The array to get subarrays of to sort.
   * @param lb
   *   The lower bound
   * @param ub
   *   The upper bound
   */
  private void merge(T[] values, int lb, int ub) {
    if (ub - lb > 1) {
      merge(values, lb, (lb + ub) / 2);
      merge(values, (lb + ub) / 2, ub);
      merge(values, lb, (lb + ub) / 2, ub);
    } // if
  } // merge(T[], int, int)

  /**
   * Splice two subarrays together, making it so that the values
   * are in order according to the order object.
   *
   * @param values
   *   The array to sort a subarray of.
   * @param lb
   *   The lower bound of the subarray.
   * @param mid
   *   The split point of the subarray.
   * @param ub
   *   The upper bound of the subarray.
   */
  @SuppressWarnings({"unchecked"})
  private void merge(T[] values, int lb, int mid, int ub) {
    T[] temp = (T[]) new Object[ub - lb];
    int lowIndex = lb;
    int highIndex = mid;
    int tempIndex = 0;
    while (lowIndex < mid && highIndex < ub) {
      if (order.compare(values[lowIndex], values[highIndex]) < 0) {
        temp[tempIndex++] = values[lowIndex++];
      } else {
        temp[tempIndex++] = values[highIndex++];
      } // if / else
    } // while
    while (lowIndex < mid) {
      temp[tempIndex++] = values[lowIndex++];
    } // while
    while (highIndex < ub) {
      temp[tempIndex++] = values[highIndex++];
    } // while
    for (int i = 0; i < temp.length; i++) {
      values[i + lb] = temp[i];
    } // for[i]
  } // merge (T[], int, int, int)
} // class MergeSorter
