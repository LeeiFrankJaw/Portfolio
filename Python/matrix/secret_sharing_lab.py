# version code 3ebd92e7eece+
coursera = 1
# Please fill out this stencil and submit using the provided submission script.

import random
from GF2 import one
from vecutil import list2vec
from The_Vector_Space_problems import GF2_span
from independence import is_independent



## 1: (Task 1) Choosing a Secret Vector
def randGF2(): return random.randint(0,1)*one

a0 = list2vec([one, one,   0, one,   0, one])
b0 = list2vec([one, one,   0,   0,   0, one])

def choose_secret_vector(s,t):
   u = [ randGF2() for _ in range(6) ]
   u[5] = u[0] + u[1] + t
   u[3] = u[0] + u[1] + u[5] + s
   return list2vec(u)



## 2: (Task 2) Finding Secret Sharing Vectors
# Give each vector as a Vec instance
secret_a0 = list2vec([one, one,   0, one,   0, one])
secret_b0 = list2vec([one, one,   0,   0,   0, one])
secret_a1 = list2vec([one, one, one, one,   0, one])
secret_b1 = list2vec([one, one,   0,   0, one, one])
secret_a2 = list2vec([  0,   0,   0, one, one, one])
secret_b2 = list2vec([one,   0, one,   0,   0,   0])
secret_a3 = list2vec([one,   0,   0,   0, one,   0])
secret_b3 = list2vec([  0,   0,   0, one,   0, one])
secret_a4 = list2vec([one,   0, one, one, one,   0])
secret_b4 = list2vec([one, one, one, one, one,   0])


p0 = [secret_a0, secret_b0]
p1 = [secret_a1, secret_b1]
p2 = [secret_a2, secret_b2]
p3 = [secret_a3, secret_b3]
p4 = [secret_a4, secret_b4]

c0 = p0+p1+p2
c1 = p0+p1+p3
c2 = p0+p1+p4
c3 = p0+p2+p3
c4 = p0+p2+p4
c5 = p0+p3+p4
c6 = p1+p2+p3
c7 = p1+p2+p4
c8 = p1+p3+p4
c9 = p2+p3+p4

S = GF2_span(a0.D, c0)

span = ( GF2_span(a0.D, p0+p1+[secret_a4]) |
         GF2_span(a0.D, p0+p2+[secret_a4]) |
         GF2_span(a0.D, p0+p3+[secret_a4]) |
         GF2_span(a0.D, p1+p2+[secret_a4]) |
         GF2_span(a0.D, p1+p3+[secret_a4]) |
         GF2_span(a0.D, p2+p3+[secret_a4]) )

##print( all(map(is_independent,[c0,c1,c2,c3,c4,c5,c6,c7,c8,c9])) )
##
##print( list(S-span)[0] )
