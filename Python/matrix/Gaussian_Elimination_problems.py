# version code f4bde2e5d0a5+
coursera = 1
# Please fill out this stencil and submit using the provided submission script.

from matutil import *
from GF2 import one
from vecutil import zero_vec



## 1: (Problem 1) Recognizing Echelon Form
# Write each matrix as a list of row lists

echelon_form_1 = [[ 1,2,0,2,0 ],
                  [ 0,1,0,3,4 ],
                  [ 0,0,2,3,4 ],
                  [ 0,0,0,2,0 ],
                  [ 0,0,0,0,4 ]]

echelon_form_2 = [[ 0,4,3,4,4 ],
                  [ 0,0,4,2,0 ],
                  [ 0,0,0,0,1 ],
                  [ 0,0,0,0,0 ]]

echelon_form_3 = [[ 1,0,0,1 ],
                  [ 0,0,0,1 ],
                  [ 0,0,0,0 ]]

echelon_form_4 = [[ 1,0,0,0 ],
                  [ 0,1,0,0 ],
                  [ 0,0,0,0 ],
                  [ 0,0,0,0 ]]



## 2: (Problem 2) Is it echelon?
def is_echelon(A):
    '''
    Input:
        - A: a list of row lists
    Output:
        - True if A is in echelon form
        - False otherwise
    Examples:
        >>> is_echelon([[9,-1,2],[0,4,5],[0,0,2]])
        True
        >>> is_echelon([[0,4,5],[0,3,0],[0,0,2]])
        False
        >>> is_echelon([[9,10]])
        True
        >>> is_echelon([[5]])
        True
        >>> is_echelon([[1],[1]])
        False
        >>> is_echelon([[0]])
        True
        >>> is_echelon([[0],[1]])
        False
    '''
    prev_nonzero = -1
    for row in A:
        nonzero = next( filter(lambda x: x[1]!=0,
                               enumerate(row)),
                        [len(row), None] )[0]
        if nonzero <= prev_nonzero and nonzero != len(row):
            return False
        prev_nonzero = nonzero
    return True



## 3: (Problem 3) Solving with Echelon Form: No Zero Rows
# Give each answer as a list

echelon_form_vec_a = [1,0,3,0]
echelon_form_vec_b = [-3,0,-2,3]
echelon_form_vec_c = [-5,0,2,0,2]



## 4: (Problem 4) Solving with Echelon Form
# If a solution exists, give it as a list vector.
# If no solution exists, provide "None" (without the quotes).

solving_with_echelon_form_a = None
solving_with_echelon_form_b = [21,0,2,0,0]



## 5: (Problem 5) Echelon Solver
def echelon_solve(row_list, label_list, b):
    '''
    Input:
        - row_list: a list of Vecs
        - label_list: a list of labels establishing an order on the domain of
                      Vecs in row_list
        - b: a vector (represented as a list)
    Output:
        - Vec x such that row_list * x is b
    >>> D = {'A','B','C','D','E'}
    >>> U_rows = [Vec(D, {'A':one, 'E':one}), Vec(D, {'B':one, 'E':one}), Vec(D,{'C':one})]
    >>> b_list = [one,0,one]
    >>> cols = ['A', 'B', 'C', 'D', 'E']
    >>> echelon_solve(U_rows, cols, b_list) == Vec({'B', 'C', 'A', 'D', 'E'},{'B': 0, 'C': one, 'A': one})
    True
    '''
    D = row_list[0].D
    x = zero_vec(D)
    for j in reversed(range(len(row_list))):
        row = row_list[j]
        for c in label_list:
            if row[c] != 0:
                x[c] = (b[j] - x*row) / row[c]
                break
    return x



## 6: (Problem 6) Solving General Matrices via Echelon
row_list = [ Vec({'A','B','C','D'}, {'A':one, 'B':one, 'C':0, 'D':one}),
             Vec({'A','B','C','D'}, {'A':0, 'B':one, 'C':0, 'D':0}),
             Vec({'A','B','C','D'}, {'A':0, 'B':0, 'C':one, 'D':0}),
             Vec({'A','B','C','D'}, {'A':0, 'B':0, 'C':0, 'D':one}) ]    # Provide as a list of Vec instances
label_list = [ 'A', 'B', 'C', 'D' ] # Provide as a list
b = [ one,one,0,0 ]          # Provide as a list of GF(2) values



## 7: (Problem 7) Nullspace A
null_space_rows_a = {3,4} # Put the row numbers of M from the PDF



## 8: (Problem 8) Nullspace B
null_space_rows_b = {4} # Put the row numbers of M from the PDF

