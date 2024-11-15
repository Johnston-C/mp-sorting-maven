package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using a custom method.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Cade Johnston
 */

public class JohnstonCadeSort<T> implements Sorter<T> {
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
  public JohnstonCadeSort(Comparator<? super T> comparator) {
    this.order = comparator;
  } // InsertionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using my own sorting algorithm.
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
    int divisor = 1;
    while (divisor <= values.length) {
      for (int i = 0; i < values.length; i += divisor * 2) {
        sift(values, i, divisor);
      } // for [i]
      divisor *= 2;
    } // while
  } // sort(T[])

  /**
   * Combine two subarrays together such that the composite
   * subarray is ordered.
   *
   * @param values
   *   The array.
   * @param lb
   *   The lower bound of the subarray.
   * @param range
   *   The range of a subarray (half of the composite).
   */
  @SuppressWarnings({"unchecked"})
  private void sift(T[] values, int lb, int range) {
    if (values.length - lb > range) {
      int low = lb;
      int mid = lb + range;
      int high;
      if (values.length - lb < 2 * range) {
        high = values.length;
      } else {
        high = lb + 2 * range;
      } // if / else
      T[] temp = (T[]) new Object[high - low];
      int tempIndex = 0;
      while ((low < lb + range) && (mid < high)) {
        if (order.compare(values[low], values[mid]) < 0) {
          temp[tempIndex++] = values[low++];
        } else {
          temp[tempIndex++] = values[mid++];
        } // if / else
      } // while
      while (low < lb + range) {
        temp[tempIndex++] = values[low++];
      } // while
      while (mid < high) {
        temp[tempIndex++] = values[mid++];
      } // while
      for (int i = 0; i < temp.length; i++) {
        values[lb + i] = temp[i];
      } // for[i]
    } // if
  } // sift(T[], int, int)
} // class JohnstonCadeSort
