from myutil import *

squares = { k:k*k for k in range(4) }
cubes = { k:k**3 for k in range(2,6) }

assert ( merge(squares, cubes) ==
         {0: 0, 1: 1, 2: 8, 3: 27, 4: 64, 5: 125} )

assert ( merge(cubes, squares) ==
         {0: 0, 1: 1, 2: 4, 3: 9, 4: 64, 5: 125} )

assert ( mergel(squares, cubes) ==
         {0: 0, 1: 1, 2: 4, 3: 9, 4: 64, 5: 125} )

assert ( mergel(cubes, squares) ==
         {0: 0, 1: 1, 2: 8, 3: 27, 4: 64, 5: 125} )

assert ( merger(squares, cubes) ==
         {0: 0, 1: 1, 2: 8, 3: 27, 4: 64, 5: 125} )

assert ( merger(cubes, squares) ==
         {0: 0, 1: 1, 2: 4, 3: 9, 4: 64, 5: 125} )

assert ( merge_with(lambda x, y: x+y, squares, cubes) ==
         {0: 0, 1: 1, 2: 12, 3: 36, 4: 64, 5: 125} )

assert ( merge_with(lambda x, y: x*y, squares, cubes) ==
         {0: 0, 1: 1, 2: 32, 3: 243, 4: 64, 5: 125} )

assert ( merge() == {} )

assert ( merge(squares) == squares )

assert ( merge(cubes) == cubes )

assert ( mergel() == {} )

assert ( mergel(squares) == squares )

assert ( mergel(cubes) == cubes )

assert ( merger() == {} )

assert ( merger(squares) == squares )

assert ( merger(cubes) == cubes )

assert ( merge_with(lambda x, y: x+y, squares, cubes, squares) ==
         {0: 0, 1: 2, 2: 16, 3: 45, 4: 64, 5: 125} )

assert ( merge_with(lambda x, y: x*y, squares, cubes, squares) ==
         {0: 0, 1: 1, 2: 128, 3: 2187, 4: 64, 5: 125} )
