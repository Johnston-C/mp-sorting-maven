package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Cade Johnston
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers

  /**
   * Ensure an array of only 1 object and shallow copies of it
   * sort correctly;
   */
  @Test
  public void allSameTest() {
    String value = "Hello";
    String[] original = new String[100];
    for (int i = 0; i < 100; i++) {
      original[i] = value;
    } // for [i]
    String[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, stringSorter);
  } // allSameTest()
  
  /**
   * Ensure an empty array 'sorts' correctly.
   */
  @Test
  public void emptyTest() {
    Integer[] original = new Integer[0];
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // emptyTest()

  /**
   * Ensure a sorting algorithm works with three different values,
   * repeated several times each (DNF).
   */
  @Test
  public void dnfTest() {
    String[] values = new String[]{"A", "B", "C"};
    String[] original = new String[30];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 10; j++) {
        original[i * 10 + j] = values[i] + i;
      } // for [j]
    } // for [i]
    String[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, stringSorter);
  } // dnfTest()

  /**
   * Ensure an array with duplicate values collated sorts properly.
   * Values are those of the Dutch National Flag problem.
   */
  @Test
  public void coalatedDNFTest() {
    String[] values = new String[]{"A", "B", "C"};
    String[] original = new String[30];
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 3; j++) {
        original[i * 3 + j] = i + values[j];
      } // for [j]
    } // for [i]
    String[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, stringSorter);
  } // coalatedDNFTest()

  /**
   * Ensures the sorting algorithm can work with increasing
   * array sizes, up to 2^16 (Should be sufficient for most use).
   */
  @Test
  public void increasingLargeTest() {
    for(int i = 0; i <= 16; i++) {
      Integer[] original = new Integer[(int) Math.pow(2,i)];
      for (int j = 0; j < original.length; j++) {
        original[j] =  j;
      }
      Integer[] expected = original.clone();
      ArrayUtils.permute(original);
      assertSorts(expected, original, intSorter);
    }
  } // increasingLargeTest()

} // class TestSorter
