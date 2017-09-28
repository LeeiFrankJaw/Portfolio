# version code 7f08c507c12c+
coursera = 1
# Please fill out this stencil and submit using the provided submission script.

from mat import Mat
from vec import Vec
from vecutil import list2vec
from matutil import listlist2mat
from math import sqrt
from orthogonalization import orthogonalize, aug_orthogonalize
from triangular import triangular_solve



## 1: (Problem 1) Generators for orthogonal complement
U_vecs_1 = [list2vec([0,0,3,2])]
W_vecs_1 = [list2vec(v) for v in [[1,2,-3,-1],[1,2,0,1],[3,1,0,-1],[-1,-2,3,1]]]
# Give a list of Vecs
ortho_compl_generators_1 = [Vec({0, 1, 2, 3},{0: 1.0,
                                              1: 2.0,
                                              2: -0.4615384615384617,
                                              3: 0.6923076923076923}),
                            Vec({0, 1, 2, 3},{0: 2.2432432432432434,
                                              1: -0.5135135135135136,
                                              2: 0.8108108108108109,
                                              3: -1.2162162162162162})]

U_vecs_2 = [list2vec([3,0,1])]
W_vecs_2 = [list2vec(v) for v in [[1,0,0],[1,0,1]]]

# Give a list of Vecs
ortho_compl_generators_2 = [list2vec([0.1,0,-0.3])]

U_vecs_3 = [list2vec(v) for v in [[-4,3,1,-2],[-2,2,3,-1]]]
W_vecs_3 = [list2vec(v) for v in [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]]]

# Give a list of Vecs
ortho_compl_generators_3 = [list2vec(v) for v in [[0.533,0.1,2.37,0.267],
                                                  [0.419,0.391,-0.0782,-0.291],
                                                  [0,0.333,-0.0667,0.467]]]



## 2: (Problem 2) Basis for null space
# Your solution should be a list of Vecs
null_space_basis = [Vec({0, 1, 2, 3},{0: 0.4624505928853755,
                                      1: -0.07114624505928856,
                                      2: -0.4031620553359684,
                                      3: -0.2845849802371541}),
                    Vec({0, 1, 2, 3},{0: 0.0,
                                      1: 0.038461538461538505,
                                      2: -0.11538461538461539,
                                      3: 0.15384615384615388})]



## 3: (Problem 3) Orthonormalize(L)
def orthonormalize(L):
    '''
    Input: a list L of linearly independent Vecs
    Output: A list Lstar of len(L) orthonormal Vecs such that, for all i in range(len(L)),
            Span L[:i+1] == Span Lstar[:i+1]

    >>> from vec import Vec
    >>> D = {'a','b','c','d'}
    >>> L = [Vec(D, {'a':4,'b':3,'c':1,'d':2}), Vec(D, {'a':8,'b':9,'c':-5,'d':-5}), Vec(D, {'a':10,'b':1,'c':-1,'d':5})]
    >>> for v in orthonormalize(L): print(v)
    ... 
    <BLANKLINE>
        a     b     c     d
    -----------------------
     0.73 0.548 0.183 0.365
    <BLANKLINE>
         a     b      c      d
    --------------------------
     0.187 0.403 -0.566 -0.695
    <BLANKLINE>
         a      b      c     d
    --------------------------
     0.528 -0.653 -0.512 0.181
    '''
    ustars = orthogonalize(L)
    return [v / sqrt(v*v) for v in ustars]



## 4: (Problem 4) aug_orthonormalize(L)
def aug_orthonormalize(L):
    '''
    Input:
        - L: a list of Vecs
    Output:
        - A pair Qlist, Rlist such that:
            * coldict2mat(L) == coldict2mat(Qlist) * coldict2mat(Rlist)
            * Qlist = orthonormalize(L)
            
    >>> from vec import Vec
    >>> D={'a','b','c','d'}
    >>> L = [Vec(D, {'a':4,'b':3,'c':1,'d':2}), Vec(D, {'a':8,'b':9,'c':-5,'d':-5}), Vec(D, {'a':10,'b':1,'c':-1,'d':5})]
    >>> Qlist, Rlist = aug_orthonormalize(L)
    >>> from matutil import coldict2mat
    >>> print(coldict2mat(Qlist))
    <BLANKLINE>
               0      1      2
         ---------------------
     a  |   0.73  0.187  0.528
     b  |  0.548  0.403 -0.653
     c  |  0.183 -0.566 -0.512
     d  |  0.365 -0.695  0.181
    <BLANKLINE>
    >>> print(coldict2mat(Rlist))
    <BLANKLINE>
              0    1      2
         ------------------
     0  |  5.48 8.03   9.49
     1  |     0 11.4 -0.636
     2  |     0    0   6.04
    <BLANKLINE>
    >>> print(coldict2mat(Qlist)*coldict2mat(Rlist))
    <BLANKLINE>
           0  1  2
         ---------
     a  |  4  8 10
     b  |  3  9  1
     c  |  1 -5 -1
     d  |  2 -5  5
    <BLANKLINE>
    '''
    vstars, rstars = aug_orthogonalize(L)
    Qlist, Rlist = ([], rstars)
    for k,v in enumerate(vstars):
        vnorm = sqrt(v*v)
        Qlist.append(v / vnorm)
        for i in range(len(Rlist)):
            Rlist[i][k] *= vnorm
    return Qlist, Rlist



## 5: (Problem 5) QR factorization of small matrices
#Compute the QR factorization

#Please represent your solution as a list of rows, such as [[1,0,0],[0,1,0],[0,0,1]]

part_1_Q = [[0.8571428571428571, 0.2555506259999758],
            [0.2857142857142857, -0.9583148474999099],
            [0.42857142857142855, 0.1277753129999879]]
part_1_R = [[7.0, 6.428571428571429], [0.0, 1.9166296949998196]]

part_2_Q = [[0.6666666666666666, 0.7071067811865475],
            [0.6666666666666666, -0.7071067811865475],
            [0.3333333333333333, 0.0]]
part_2_R = [[3.0, 3.0], [0.0, 1.4142135623730951]]



## 6: (Problem 6) QR Solve
from matutil import mat2coldict, coldict2mat, mat2rowdict
from python_lab import dict2list, list2dict

def QR_factor(A):
    col_labels = sorted(A.D[1], key=repr)
    Acols = dict2list(mat2coldict(A),col_labels)
    Qlist, Rlist = aug_orthonormalize(Acols)
    #Now make Mats
    Q = coldict2mat(Qlist)
    R = coldict2mat(list2dict(Rlist, col_labels))
    return Q,R


def QR_solve(A, b):
    '''
    Input:
        - A: a Mat with linearly independent columns
        - b: a Vec whose domain equals the set of row-labels of A
    Output:
        - vector x that minimizes norm(b - A*x)
    Note: This procedure uses the procedure QR_factor, which in turn uses dict2list and list2dict.
           You wrote these procedures long back in python_lab.  Make sure the completed python_lab.py
           is in your matrix directory.
    Example:
        >>> domain = ({'a','b','c'},{'A','B'})
        >>> A = Mat(domain,{('a','A'):-1, ('a','B'):2,('b','A'):5, ('b','B'):3,('c','A'):1,('c','B'):-2})
        >>> Q, R = QR_factor(A)
        >>> b = Vec(domain[0], {'a': 1, 'b': -1})
        >>> x = QR_solve(A, b)
        >>> result = A.transpose()*(b-A*x)
        >>> result.is_almost_zero()
        True
    '''
    Q, R = QR_factor(A)
    Qtb = Q.transpose() * b
    return triangular_solve(mat2rowdict(R),
                            sorted(A.D[1], key=repr),
                            Qtb)



## 7: (Problem 7) Least Squares Problem
# Please give each solution as a Vec

least_squares_A1 = listlist2mat([[8, 1], [6, 2], [0, 6]])
least_squares_Q1 = listlist2mat([[.8,-0.099],[.6, 0.132],[0,0.986]])
least_squares_R1 = listlist2mat([[10,2],[0,6.08]])
least_squares_b1 = list2vec([10, 8, 6])

x_hat_1 = Vec({0, 1},{0: 1.0832432432432433, 1: 0.9837837837837838})


least_squares_A2 = listlist2mat([[3, 1], [4, 1], [5, 1]])
least_squares_Q2 = listlist2mat([[.424, .808],[.566, .115],[.707, -.577]])
least_squares_R2 = listlist2mat([[7.07, 1.7],[0,.346]])
least_squares_b2 = list2vec([10,13,15])

x_hat_2 = Vec({0, 1},{0: 2.499999999999997, 1: 2.666666666666679})



## 8: (Problem 8) Small examples of least squares
#Find the vector minimizing (Ax-b)^2

#Please represent your solution as a list

your_answer_1 = [1.0832432432432433, 0.9837837837837838]
your_answer_2 = [3.000000000000015, 0.9999999999999476]



## 9: (Problem 9) Linear regression example
#Find a and b for the y=ax+b line of best fit

a = 0.6349650349650302
b = 64.9283216783218

