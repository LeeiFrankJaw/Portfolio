# 6.00x Problem Set 10
# Graph optimization
#
# A set of data structures to represent graphs
#

class Node(object):
    def __init__(self, name):
        self.name = str(name)
    def getName(self):
        return self.name
    def __str__(self):
        return self.name
    def __repr__(self):
        return self.name
    def __eq__(self, other):
        return self.name == other.name
    def __ne__(self, other):
        return not self.__eq__(other)
    def __hash__(self):
        # Override the default hash method
        # Think: Why would we want to do this?
        return self.name.__hash__()

class Edge(object):
    def __init__(self, src, dest):
        self.src = src
        self.dest = dest
    def getSource(self):
        return self.src
    def getDestination(self):
        return self.dest
    def __str__(self):
        return '{0}->{1}'.format(self.src, self.dest)

class Digraph(object):
    """
    A directed graph
    """
    def __init__(self):
        # A Python Set is basically a list that doesn't allow duplicates.
        # Entries into a set must be hashable (where have we seen this before?)
        # Because it is backed by a hashtable, lookups are O(1) as opposed to the O(n) of a list (nifty!)
        # See http://docs.python.org/2/library/stdtypes.html#set-types-set-frozenset
        self.nodes = set([])
        self.edges = {}
    def addNode(self, node):
        if node in self.nodes:
            # Even though self.nodes is a Set, we want to do this to make sure we
            # don't add a duplicate entry for the same node in the self.edges list.
            raise ValueError('Duplicate node')
        else:
            self.nodes.add(node)
            self.edges[node] = []
    def addEdge(self, edge):
        src = edge.getSource()
        dest = edge.getDestination()
        if not(src in self.nodes and dest in self.nodes):
            raise ValueError('Node not in graph')
        self.edges[src].append(dest)
    def childrenOf(self, node):
        return self.edges[node]
    def hasNode(self, node):
        return node in self.nodes
    def __str__(self):
        res = ''
        for k in self.edges:
            for d in self.edges[str(k)]:
                res = '{0}{1}->{2}\n'.format(res, k, d)
        return res[:-1]


class WeightedEdge(Edge):
    
    def __init__(self, src, dest, weightBlue, weightGreen):
        Edge.__init__(self, src, dest)
        self.weightBlue = weightBlue
        self.weightGreen = weightGreen

    def getTotalDistance(self):
        return self.weightBlue

    def getOutdoorDistance(self):
        return self.weightGreen

    def __str__(self):
        return '{!s}->{!s} ({!s}, {!s})'.format(self.src, self.dest,
                                                self.weightBlue,
                                                self.weightGreen)


class WeightedDigraph(Digraph):

    def addEdge(self, edge):
        src = edge.getSource()
        dest = edge.getDestination()
        if not (src in self.nodes and dest in self.nodes):
            raise ValueError('Node not in graph')
        weightBlue = edge.getTotalDistance()
        weightGreen = edge.getOutdoorDistance()
        self.edges[src].append([dest, weightBlue, weightGreen])

    def childrenOf(self, node):
        if self.edges[node]:
            return list(zip(*self.edges[node])[0])
        return []

    def getChildren(self, node):
        return self.edges[node]

    def __str__(self):
        res = ''
        for k in self.edges:
            for d in self.edges[k]:
                res = '{}{!s}->{!s} ({!s}, {!s})\n'.format(res, k, d[0],
                                                           float(d[1]),
                                                           float(d[2]))
        return res[:-1]

def printPath(path):
    res = ''
    for node in path:
        res += str(node) + ' -> '
    res = res[:-4]
    res += '\n'
    return res
    
