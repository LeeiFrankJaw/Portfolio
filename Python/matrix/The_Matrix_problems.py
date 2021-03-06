# version code 1f320053b6c7+
coursera = 1
# Please fill out this stencil and submit using the provided submission script.

from mat import Mat
from vec import Vec
from matutil import *



## 1: (Problem 1) Computing matrix-vector products
# Please represent your solution vectors as lists.
vector_matrix_product_1 = [1, 0]
vector_matrix_product_2 = [0, 4.44]
vector_matrix_product_3 = [14, 20, 26]



## 2: (Problem 2) Matrix-vector multiplication to swap entries
# Represent your solution as a list of rowlists.
# For example, the 2x2 identity matrix would be [[1,0],[0,1]].

M_swap_two_vector = [[0,1],
                     [1,0]]



## 3: (Problem 3) [z+x, y, x] Matrix-vector multiplication
three_by_three_matrix = [[1,0,1],
                         [0,1,0],
                         [1,0,0]] # Represent with a list of rowlists.



## 4: (Problem 4) [2x, 4y, 3z] matrix-vector multiplication
multiplied_matrix = [[2,0,0],
                     [0,4,0],
                     [0,0,3]] # Represent with a list of row lists.



## 5: (Problem 5) Matrix multiplication: dimension of matrices
# Please enter a boolean representing if the multiplication is valid.
# If it is not valid, please enter None for the dimensions.

part_1_valid = False # True or False
part_1_number_rows = None # Integer or None
part_1_number_cols = None # Integer or None

part_2_valid = False
part_2_number_rows = None
part_2_number_cols = None

part_3_valid = True
part_3_number_rows = 1
part_3_number_cols = 2

part_4_valid = True
part_4_number_rows = 2
part_4_number_cols = 1

part_5_valid = False
part_5_number_rows = None
part_5_number_cols = None

part_6_valid = True
part_6_number_rows = 1
part_6_number_cols = 1

part_7_valid = True
part_7_number_rows = 3
part_7_number_cols = 3



## 6: (Problem 6) Matrix-matrix multiplication practice with small matrices
# Please represent your answer as a list of row lists.
# Example: [[1,1],[2,2]]
small_mat_mult_1 = [[8,13],
                    [8,14]]
small_mat_mult_2 = [[24,11,4],
                    [1,3,0]]
small_mat_mult_3 = [[3,13]]
small_mat_mult_4 = [[14]]
small_mat_mult_5 = [[1,2,3],
                    [2,4,6],
                    [3,6,9]]
small_mat_mult_6 = [[-2,4],
                    [1,1],
                    [1,-3]]



## 7: (Problem 7) Matrix-matrix multiplication practice with a permutation matrix
# Please represent your solution as a list of row lists.

part_1_AB = [[5,2,0,1],
             [2,1,-4,6],
             [2,3,0,-4],
             [-2,3,4,0]]
part_1_BA = [[1,-4,6,2],
             [3,0,-4,2],
             [3,4,0,-2],
             [2,0,1,5]]

part_2_AB = [[5,1,0,2],
             [2,6,-4,1],
             [2,-4,0,3],
             [-2,0,4,3]]
part_2_BA = [[3,4,0,-2],
             [3,0,-4,2],
             [1,-4,6,2],
             [2,0,1,5]]

part_3_AB = [[1,0,5,2],
             [6,-4,2,1],
             [-4,0,2,3],
             [0,4,-2,3]]
part_3_BA = [[3,4,0,-2],
             [1,-4,6,2],
             [2,0,1,5],
             [3,0,-4,2]]



## 8: (Problem 8) Matrix-matrix multiplication practice with very sparse matrices
# Please represent your answer as a list of row lists.

your_answer_a_AB = [[0,0,2,0],
                    [0,0,5,0],
                    [0,0,4,0],
                    [0,0,6,0]]
your_answer_a_BA = [[0,0,0,0],
                    [4,4,4,0],
                    [0,0,0,0],
                    [0,0,0,0]]

your_answer_b_AB = [[0,2,-1,0],
                    [0,5,3,0],
                    [0,4,0,0],
                    [0,6,-5,0]]
your_answer_b_BA = [[0,0,0,0],
                    [1,5,-2,3],
                    [0,0,0,0],
                    [4,4,4,0]]

your_answer_c_AB = [[6,0,0,0],
                    [6,0,0,0],
                    [8,0,0,0],
                    [5,0,0,0]]
your_answer_c_BA = [[4,2,1,-1],
                    [4,2,1,-1],
                    [0,0,0,0],
                    [0,0,0,0]]

your_answer_d_AB = [[0,3,0,4],
                    [0,4,0,1],
                    [0,4,0,4],
                    [0,-6,0,-1]]
your_answer_d_BA = [[0,11,0,-2],
                    [0,0,0,0],
                    [0,0,0,0],
                    [1,5,-2,3]]

your_answer_e_AB = [[0,3,0,8],
                    [0,-9,0,2],
                    [0,0,0,8],
                    [0,15,0,-2]]
your_answer_e_BA = [[-2,12,4,-10],
                    [0,0,0,0],
                    [0,0,0,0],
                    [-3,-15,6,-9]]

your_answer_f_AB = [[-4,4,2,-3],
                    [-1,10,-4,9],
                    [-4,8,8,0],
                    [1,12,4,-15]]
your_answer_f_BA = [[-4,-2,-1,1],
                    [2,10,-4,6],
                    [8,8,8,0],
                    [-3,18,6,-15]]



## 9: (Problem 9) Column-vector and row-vector matrix multiplication
column_row_vector_multiplication1 = Vec({0, 1}, {0:13, 1:20})

column_row_vector_multiplication2 = Vec({0, 1, 2}, {0:24, 1:11, 2:4})

column_row_vector_multiplication3 = Vec({0, 1, 2, 3}, {0:4, 1:8, 2:11, 3:3})

column_row_vector_multiplication4 = Vec({0,1}, {0:30, 1:16})

column_row_vector_multiplication5 = Vec({0, 1, 2}, {0:-3, 1:1, 2:9})



## 10: (Problem 10) Linear-combinations matrix-vector multiply
# You are also allowed to use the matutil module
def lin_comb_mat_vec_mult(M, v):
    '''
    Input:
      -M: a matrix
      -v: a vector
    Output: M*v
    The following doctests are not comprehensive; they don't test the
    main question, which is whether the procedure uses the appropriate
    linear-combination definition of matrix-vector multiplication. 
    Examples:
    >>> M=Mat(({'a','b'},{0,1}), {('a',0):7, ('a',1):1, ('b',0):-5, ('b',1):2})
    >>> v=Vec({0,1},{0:4, 1:2})
    >>> lin_comb_mat_vec_mult(M,v) == Vec({'a', 'b'},{'a': 30, 'b': -16})
    True
    >>> M1=Mat(({'a','b'},{0,1}), {('a',0):8, ('a',1):2, ('b',0):-2, ('b',1):1})
    >>> v1=Vec({0,1},{0:4,1:3})
    >>> lin_comb_mat_vec_mult(M1,v1) == Vec({'a', 'b'},{'a': 38, 'b': -5})
    True
    '''
    assert(M.D[1] == v.D)
    res = Vec(M.D[0], {})
    cols = mat2coldict(M)
    for k in v.D:
        res += v[k] * cols[k]
    return res
##    for (r,c),val in M.f.items():
##        res[r] += v[c] * val
##    return res



## 11: (Problem 11) Linear-combinations vector-matrix multiply
def lin_comb_vec_mat_mult(v, M):
    '''
    Input:
      -v: a vector
      -M: a matrix
    Output: v*M
    The following doctests are not comprehensive; they don't test the
    main question, which is whether the procedure uses the appropriate
    linear-combination definition of vector-matrix multiplication. 
    Examples:
      >>> M=Mat(({'a','b'},{0,1}), {('a',0):7, ('a',1):1, ('b',0):-5, ('b',1):2})
      >>> v=Vec({'a','b'},{'a':2, 'b':-1})
      >>> lin_comb_vec_mat_mult(v,M) == Vec({0, 1},{0: 19, 1: 0})
      True
      >>> M1=Mat(({'a','b'},{0,1}), {('a',0):8, ('a',1):2, ('b',0):-2, ('b',1):1})
      >>> v1=Vec({'a','b'},{'a':4,'b':3})
      >>> lin_comb_vec_mat_mult(v1,M1) == Vec({0, 1},{0: 26, 1: 11})
      True
    '''
    assert(v.D == M.D[0])
    res = Vec(M.D[1], {})
    rows = mat2rowdict(M)
    for k in v.D:
        res += v[k] * rows[k]
    return res
##    for (r,c),val in M.f.items():
##        res[c] += v[r] * val
##    return res



## 12: (Problem 12) dot-product matrix-vector multiply
# You are also allowed to use the matutil module
def dot_product_mat_vec_mult(M, v):
    '''
    Return the matrix-vector product M*v.
    The following doctests are not comprehensive; they don't test the
    main question, which is whether the procedure uses the appropriate
    dot-product definition of matrix-vector multiplication. 
    Examples:
    >>> M=Mat(({'a','b'},{0,1}), {('a',0):7, ('a',1):1, ('b',0):-5, ('b',1):2})
    >>> v=Vec({0,1},{0:4, 1:2})
    >>> dot_product_mat_vec_mult(M,v) == Vec({'a', 'b'},{'a': 30, 'b': -16})
    True
    >>> M1=Mat(({'a','b'},{0,1}), {('a',0):8, ('a',1):2, ('b',0):-2, ('b',1):1})
    >>> v1=Vec({0,1},{0:4,1:3})
    >>> dot_product_mat_vec_mult(M1,v1) == Vec({'a', 'b'},{'a': 38, 'b': -5})
    True
    '''
    assert(M.D[1] == v.D)
    return Vec(M.D[0], { r:v*row for r,row in mat2rowdict(M).items() })



## 13: (Problem 13) Dot-product vector-matrix multiply
# You are also allowed to use the matutil module
def dot_product_vec_mat_mult(v, M):
    '''
    The following doctests are not comprehensive; they don't test the
    main question, which is whether the procedure uses the appropriate
    dot-product definition of vector-matrix multiplication. 
    Examples:
      >>> M=Mat(({'a','b'},{0,1}), {('a',0):7, ('a',1):1, ('b',0):-5, ('b',1):2})
      >>> v=Vec({'a','b'},{'a':2, 'b':-1})
      >>> dot_product_vec_mat_mult(v,M) == Vec({0, 1},{0: 19, 1: 0})
      True
      >>> M1=Mat(({'a','b'},{0,1}), {('a',0):8, ('a',1):2, ('b',0):-2, ('b',1):1})
      >>> v1=Vec({'a','b'},{'a':4,'b':3})
      >>> dot_product_vec_mat_mult(v1,M1) == Vec({0, 1},{0: 26, 1: 11})
      True
      '''
    assert(v.D == M.D[0])
    return Vec(M.D[1], { c:v*col for c,col in mat2coldict(M).items() })



## 14: (Problem 14) Matrix-vector matrix-matrix multiply
# You are also allowed to use the matutil module
def Mv_mat_mat_mult(A, B):
    assert A.D[1] == B.D[0]
    cols = mat2coldict(B)
    res = {}
    for k in B.D[1]:
        res[k] = A * cols[k]
    return coldict2mat(res)



## 15: (Problem 15) Vector-matrix matrix-matrix multiply
def vM_mat_mat_mult(A, B):
    assert A.D[1] == B.D[0]
    rows = mat2rowdict(A)
    res = {}
    for k in A.D[0]:
        res[k] = rows[k] * B
    return efficient_rowdict2mat(res)

## Ungraded Problem: UN voting
def UN_voting():
    labels = []
    cols = []
    with open('UN_voting_data.txt') as file:
        for line in file:
            label, col = line.split(maxsplit=1)
            labels.append(label)
            cols.append( list( map(int, col.split()) ) )
    K = set(range(len(cols[0])))
    cols = [ Vec(K, dict(enumerate(col))) for col in cols ]
    D = set(labels)
    countries = len(labels)
    M = Mat( (D,D), { (labels[i], labels[j]): cols[i]*cols[j]
                      for i in range(countries)
                      for j in range(i+1,countries) } )
    similar = sorted(M.f.items(), key=lambda x: x[1])
    print(' and '.join(similar[0][0]) + 'are most opposed')
    print('The ten most opposed pairs of countries are:')
    for x in similar[:10]:
        print(' and '.join(x[0]))
    print(' and '.join(similar[-1][0]) + 'are in the greatest agreement')
##    return similar


## 16: (Problem 16) Buttons
from solver import solve
from GF2 import one

def D(n): return {(i,j) for i in range(n) for j in range(n)}

def button_vectors(n):
  return {(i,j):Vec(D(n),dict([((x,j),one) for x in range(max(i-1,0), min(i+2,n))]
                           +[((i,y),one) for y in range(max(j-1,0), min(j+2,n))]))
                           for (i,j) in D(n)}

# Remind yourself of the types of the arguments to solve().

## PART 1

b1=Vec(D(9),{(7, 8):one,(7, 7):one,(6, 2):one,(3, 7):one,(2, 5):one,(8, 5):one,(1, 2):one,(7, 2):one,(6, 3):one,(0, 4):one,(2, 2):one,(5, 0):one,(6, 4):one,(0, 0):one,(5, 4):one,(1, 4):one,(8, 7):one,(0, 8):one,(6, 5):one,(2, 7):one,(8, 3):one,(7, 0):one,(4, 6):one,(6, 8):one,(0, 6):one,(1, 8):one,(7, 4):one,(2, 4):one})


#Solution given by solver.
x1 = Vec({(7, 8), (7, 3), (4, 7), (1, 3), (8, 4), (4, 8), (3, 0), (2, 8), (5, 6), (6, 6), (8, 0), (7, 7), (0, 7), (2, 1), (6, 2), (1, 6), (3, 7), (0, 3), (2, 5), (5, 1), (8, 5), (5, 8), (4, 0), (1, 2), (7, 2), (6, 7), (3, 3), (5, 5), (8, 1), (7, 6), (4, 4), (6, 3), (1, 5), (3, 6), (0, 4), (2, 2), (5, 0), (8, 6), (4, 1), (1, 1), (6, 4), (3, 2), (0, 0), (2, 6), (5, 4), (8, 2), (7, 1), (4, 5), (6, 0), (1, 4), (7, 5), (0, 5), (2, 3), (8, 7), (4, 2), (1, 0), (0, 8), (6, 5), (3, 5), (0, 1), (2, 7), (5, 3), (8, 3), (7, 0), (4, 6), (6, 8), (6, 1), (3, 1), (5, 7), (3, 8), (0, 6), (1, 8), (2, 0), (7, 4), (8, 8), (4, 3), (1, 7), (3, 4), (0, 2), (2, 4), (5, 2)},{(7, 3): one, (4, 7): one, (4, 4): one, (6, 6): 0, (5, 6): one, (2, 8): 0, (8, 0): one, (7, 8): 0, (7, 7): one, (2, 1): one, (0, 1): 0, (6, 2): one, (1, 6): 0, (3, 7): 0, (5, 1): 0, (2, 5): 0, (0, 3): 0, (5, 8): one, (4, 0): 0, (1, 2): 0, (7, 4): 0, (3, 1): one, (6, 7): 0, (5, 5): 0, (0, 2): 0, (1, 5): 0, (7, 6): 0, (4, 8): 0, (0, 0): one, (6, 3): one, (3, 0): one, (7, 2): 0, (3, 6): one, (2, 2): one, (1, 3): one, (3, 3): 0, (5, 3): one, (4, 1): 0, (1, 1): one, (6, 4): one, (5, 4): 0, (2, 6): 0, (3, 2): one, (5, 0): one, (7, 1): one, (4, 5): 0, (0, 4): one, (6, 0): 0, (1, 4): one, (7, 5): one, (2, 3): 0, (0, 7): 0, (6, 8): 0, (4, 2): one, (1, 0): 0, (0, 8): 0, (6, 5): one, (3, 5): one, (2, 7): one, (7, 0): one, (4, 6): 0, (3, 4): 0, (6, 1): 0, (5, 7): one, (0, 6): 0, (3, 8): 0, (2, 0): 0, (1, 8): one, (4, 3): one, (1, 7): 0, (0, 5): one, (5, 2): one, (2, 4): 0})

#residual
r1 = Vec({(7, 8), (7, 3), (4, 7), (1, 3), (8, 4), (4, 8), (3, 0), (2, 8), (5, 6), (6, 6), (8, 0), (7, 7), (0, 7), (2, 1), (6, 2), (1, 6), (3, 7), (0, 3), (2, 5), (5, 1), (8, 5), (5, 8), (4, 0), (1, 2), (7, 2), (6, 7), (3, 3), (5, 5), (8, 1), (7, 6), (4, 4), (6, 3), (1, 5), (3, 6), (0, 4), (2, 2), (5, 0), (8, 6), (4, 1), (1, 1), (6, 4), (3, 2), (0, 0), (2, 6), (5, 4), (8, 2), (7, 1), (4, 5), (6, 0), (1, 4), (7, 5), (0, 5), (2, 3), (8, 7), (4, 2), (1, 0), (0, 8), (6, 5), (3, 5), (0, 1), (2, 7), (5, 3), (8, 3), (7, 0), (4, 6), (6, 8), (6, 1), (3, 1), (5, 7), (3, 8), (0, 6), (1, 8), (2, 0), (7, 4), (8, 8), (4, 3), (1, 7), (3, 4), (0, 2), (2, 4), (5, 2)},{(7, 3): 0, (4, 7): 0, (1, 3): 0, (6, 6): 0, (5, 6): 0, (2, 8): 0, (8, 0): 0, (7, 7): 0, (2, 1): 0, (0, 1): 0, (6, 2): 0, (1, 6): 0, (5, 1): 0, (3, 7): 0, (0, 3): 0, (8, 5): 0, (2, 5): 0, (5, 8): 0, (4, 0): 0, (1, 2): 0, (3, 8): 0, (3, 1): 0, (6, 7): 0, (5, 5): 0, (0, 6): 0, (8, 1): 0, (7, 6): 0, (4, 8): 0, (4, 4): 0, (3, 0): 0, (6, 3): 0, (1, 5): 0, (7, 2): 0, (5, 0): 0, (2, 2): 0, (8, 6): 0, (3, 5): 0, (4, 1): 0, (1, 1): 0, (6, 4): 0, (3, 2): 0, (0, 0): 0, (3, 6): 0, (7, 1): 0, (4, 5): 0, (0, 4): 0, (6, 0): 0, (1, 4): 0, (7, 5): 0, (2, 3): 0, (0, 7): 0, (8, 7): 0, (6, 8): 0, (5, 4): 0, (4, 2): 0, (1, 0): 0, (0, 8): 0, (5, 7): 0, (8, 2): 0, (6, 5): 0, (3, 3): 0, (2, 7): 0, (5, 3): 0, (7, 0): 0, (4, 6): 0, (3, 4): 0, (6, 1): 0, (8, 3): 0, (5, 2): 0, (2, 6): 0, (0, 2): 0, (7, 4): 0, (2, 0): 0, (1, 8): 0, (8, 8): 0, (4, 3): 0, (1, 7): 0, (0, 5): 0, (7, 8): 0, (2, 4): 0, (8, 4): 0})

#Is x1 really a solution? Assign True if yes, False if no.
is_good1 = True

## PART 2

b2=Vec(D(9), {(3,4):one, (6,7):one})

#Solution given by solver
x2 = Vec({(7, 8), (7, 3), (4, 7), (1, 3), (8, 4), (4, 8), (3, 0), (2, 8), (5, 6), (6, 6), (8, 0), (7, 7), (0, 7), (2, 1), (6, 2), (1, 6), (3, 7), (0, 3), (2, 5), (5, 1), (8, 5), (5, 8), (4, 0), (1, 2), (7, 2), (6, 7), (3, 3), (5, 5), (8, 1), (7, 6), (4, 4), (6, 3), (1, 5), (3, 6), (0, 4), (2, 2), (5, 0), (8, 6), (4, 1), (1, 1), (6, 4), (3, 2), (0, 0), (2, 6), (5, 4), (8, 2), (7, 1), (4, 5), (6, 0), (1, 4), (7, 5), (0, 5), (2, 3), (8, 7), (4, 2), (1, 0), (0, 8), (6, 5), (3, 5), (0, 1), (2, 7), (5, 3), (8, 3), (7, 0), (4, 6), (6, 8), (6, 1), (3, 1), (5, 7), (3, 8), (0, 6), (1, 8), (2, 0), (7, 4), (8, 8), (4, 3), (1, 7), (3, 4), (0, 2), (2, 4), (5, 2)},{(7, 3): 0, (4, 7): one, (4, 4): 0, (6, 6): 0, (5, 6): 0, (2, 8): 0, (8, 0): 0, (7, 8): 0, (7, 7): 0, (2, 1): 0, (0, 1): 0, (6, 2): 0, (1, 6): 0, (3, 7): one, (5, 1): 0, (2, 5): one, (0, 3): 0, (5, 8): 0, (4, 0): 0, (1, 2): one, (7, 4): 0, (3, 1): 0, (6, 7): 0, (5, 5): one, (0, 2): one, (1, 5): 0, (7, 6): 0, (4, 8): one, (0, 0): one, (6, 3): 0, (3, 0): 0, (7, 2): 0, (3, 6): one, (2, 2): 0, (1, 3): 0, (3, 3): 0, (5, 3): one, (4, 1): 0, (1, 1): 0, (6, 4): one, (5, 4): one, (2, 6): one, (3, 2): 0, (5, 0): 0, (7, 1): 0, (4, 5): 0, (0, 4): one, (6, 0): 0, (1, 4): 0, (7, 5): 0, (2, 3): one, (0, 7): one, (6, 8): 0, (4, 2): 0, (1, 0): one, (0, 8): 0, (6, 5): 0, (3, 5): one, (2, 7): one, (7, 0): 0, (4, 6): 0, (3, 4): one, (6, 1): 0, (5, 7): one, (0, 6): 0, (3, 8): 0, (2, 0): 0, (1, 8): one, (4, 3): 0, (1, 7): one, (0, 5): one, (5, 2): 0, (2, 4): one})

#residual
r2 = Vec({(7, 8), (7, 3), (4, 7), (1, 3), (8, 4), (4, 8), (3, 0), (2, 8), (5, 6), (6, 6), (8, 0), (7, 7), (0, 7), (2, 1), (6, 2), (1, 6), (3, 7), (0, 3), (2, 5), (5, 1), (8, 5), (5, 8), (4, 0), (1, 2), (7, 2), (6, 7), (3, 3), (5, 5), (8, 1), (7, 6), (4, 4), (6, 3), (1, 5), (3, 6), (0, 4), (2, 2), (5, 0), (8, 6), (4, 1), (1, 1), (6, 4), (3, 2), (0, 0), (2, 6), (5, 4), (8, 2), (7, 1), (4, 5), (6, 0), (1, 4), (7, 5), (0, 5), (2, 3), (8, 7), (4, 2), (1, 0), (0, 8), (6, 5), (3, 5), (0, 1), (2, 7), (5, 3), (8, 3), (7, 0), (4, 6), (6, 8), (6, 1), (3, 1), (5, 7), (3, 8), (0, 6), (1, 8), (2, 0), (7, 4), (8, 8), (4, 3), (1, 7), (3, 4), (0, 2), (2, 4), (5, 2)},{(7, 3): 0, (4, 7): 0, (1, 3): 0, (6, 6): 0, (5, 6): 0, (2, 8): 0, (8, 0): 0, (7, 7): 0, (2, 1): 0, (0, 1): 0, (6, 2): 0, (1, 6): 0, (5, 1): 0, (3, 7): 0, (0, 3): 0, (8, 5): 0, (2, 5): 0, (5, 8): 0, (4, 0): 0, (1, 2): 0, (3, 8): 0, (3, 1): 0, (6, 7): 0, (5, 5): 0, (0, 6): 0, (8, 1): 0, (7, 6): 0, (4, 8): 0, (4, 4): 0, (3, 0): 0, (6, 3): 0, (1, 5): 0, (7, 2): 0, (5, 0): 0, (2, 2): 0, (8, 6): 0, (3, 5): 0, (4, 1): 0, (1, 1): 0, (6, 4): 0, (3, 2): 0, (0, 0): 0, (3, 6): 0, (7, 1): 0, (4, 5): 0, (0, 4): 0, (6, 0): 0, (1, 4): 0, (7, 5): 0, (2, 3): 0, (0, 7): 0, (8, 7): 0, (6, 8): 0, (5, 4): 0, (4, 2): 0, (1, 0): 0, (0, 8): 0, (5, 7): 0, (8, 2): 0, (6, 5): 0, (3, 3): 0, (2, 7): 0, (5, 3): 0, (7, 0): 0, (4, 6): 0, (3, 4): 0, (6, 1): 0, (8, 3): 0, (5, 2): one, (2, 6): 0, (0, 2): 0, (7, 4): one, (2, 0): one, (1, 8): 0, (8, 8): 0, (4, 3): one, (1, 7): 0, (0, 5): 0, (7, 8): 0, (2, 4): 0, (8, 4): 0})

#Is it really a solution? Assign True if yes, False if no.
is_good2 = False




## 17: (Problem 17) Solving 2x2 linear systems and finding matrix inverse
solving_systems_x1 = -1/5
solving_systems_x2 = 2/5
solving_systems_y1 = 4/5
solving_systems_y2 = -3/5
solving_systems_m = Mat(({0, 1}, {0, 1}), {(0,0):-1/5, (0,1):4/5, (1,0):2/5, (1,1):-3/5})
solving_systems_a = Mat(({0, 1}, {0, 1}), {(0,0):3, (0,1):4, (1,0):2, (1,1):1})
solving_systems_a_times_m = Mat(({0, 1}, {0, 1}), {(0,0):1, (0,1):0, (1,0):0, (1,1):1})
solving_systems_m_times_a = Mat(({0, 1}, {0, 1}), {(0,0):1, (0,1):0, (1,0):0, (1,1):1})



## 18: (Problem 18) Matrix inverse criterion
# Please write your solutions as booleans (True or False)

are_inverses1 = True
are_inverses2 = True
are_inverses3 = False
are_inverses4 = False

