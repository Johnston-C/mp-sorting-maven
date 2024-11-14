# mp-sorting-maven

An exploration of sorting in Java.

Authors

* Cade Johnston
* Samuel A. Rebelsky (starter code)

Acknowledgements

* _Forthcoming_.

This code may be found at <https://github.com/Johnston-C/mp-sorting-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-sorting-maven>.

Description of custom sorting algorithm
---------------------------------------
My custom sorting algorithm involves subdividing the array into 2s, then 4s, and so on until the whole array is considered at once. At each subdivision, the array is sorted like merge sort: Each half is assumed to already be in order, and thus the subarray may be ordered by checking which of the "heads" is greater, inserting that value, then advancing that head until a subarray is completed. I believe this method is different from merge sort as rather than recursively splitting the array repeatedly to find the bounds of subarrays, the bounds are known at the start of each iteration.

Notes on using Copilot (or other AI)
------------------------------------
No AI was consulted for this project at any point.
