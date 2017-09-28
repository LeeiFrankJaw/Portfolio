from ps10 import *

try:
    na = Node('a')
    nb = Node('b')
    nc = Node('c')
    nd = Node('d')
    ne = Node('e')
    g = WeightedDigraph()
    g.addNode(na)
    g.addNode(nb)
    g.addNode(nc)
    g.addNode(nd)
    g.addNode(ne)
    randomEdge = WeightedEdge(na, nb, 10, 8)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(nb, nc, 15, 12)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(nc, na, 20, 7)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(na, nd, 15, 13)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(nb, nd, 5, 3)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(nd, ne, 11, 7)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(ne, nb, 17, 9)
    g.addEdge(randomEdge)
    randomEdge = WeightedEdge(nb, ne, 37, 34)
    g.addEdge(randomEdge)
    print type(g.childrenOf(na)[0])
    print g.childrenOf(nb)
    print g.childrenOf(nc)
    print g.childrenOf(nd)
    print g.childrenOf(ne)
    print g
    print getTotalDist(g, [na, nb, nd, ne])
    print getTotalDist(g, [na, nb, ne])
    print getTotalDist(g, [na, ne])
except ValueError as detail:
    print detail

try:
    print g.childrenOf(Node('a'))
    print g.childrenOf(Node('b'))
    print g.childrenOf(Node('c'))
    print g.childrenOf(Node('d'))
    print g.childrenOf(Node('e'))
    print getDistOutdoors(g, [na, nb, nd, ne])
    print getDistOutdoors(g, [na, nb, ne])
    print getDistOutdoors(g, [na, ne])
except ValueError as detail:
    print detail
##finally:
##    raw_input('Press any key to exit.')
