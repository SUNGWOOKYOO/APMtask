### 2018-2 SNU Advanced Programming Methodology
### HW 2 Coding problem
### Implementation of graph-related structures and algorithms
import math
from heap import MinHeap


class Node:
    """Base node for linked list implementation."""
    def __init__(self, val):
        self.val = val
        self.next = None

    def __repr__(self):
        return 'Node' + self.val.__repr__


class VertexNode(Node):
    """Indexable node subclass for representing head vertices."""
    def __init__(self, loc, h):
        super().__init__((loc, h))
        self.loc = self.val[0]
        self.h = self.val[1]

    def dist(self, other):
        x, y = self.loc
        xp, yp = other.loc
        manhattan_dist = abs(x - xp) + abs(y - yp)
        d_squared = (self.h - other.h) ** 2 + manhattan_dist ** 2
        return math.sqrt(d_squared)

    def __eq__(self, other):
        return hasattr(other, 'loc') and self.loc == other.loc

    def __hash__(self):
        return hash(self.loc)

    def __repr__(self):
        return 'Vtx' + self.loc.__repr__

    def __str__(self):
        return '({:2d},{:2d})'.format(self.loc[0], self.loc[1])

    def get_neighbors(self):
        w = self.next
        while w is not None:
            yield w.val
            w = w.next


def Dijkstra(V, s):
    """Dijkstra algorithm. Returns MinHeap D containing minimum distances and dict P containing previous node."""
    # initialize structures
    Q = set()
    D = MinHeap()
    P = {}
    
    # set initial values
    for v in V:
        Q.add(v)
        D[v] = float('inf')
    D[s] = 0
    P[s] = None
    
    while len(Q) != 0:
        v, _ = D.extract_min()
        Q.remove(v)
        for w, d in v.get_neighbors():
            new_d = D[v] + d
            if w in Q:
                if new_d < D[w]:
                    D[w] = new_d
                    P[w] = v

    return D, P


def Prim(V, s):
    """Prim algorithm. Returns list T containing edges of minimum spanning tree and T_sum, the sum of its weights."""
    # initialize structures
    Q = set()
    D = MinHeap()
    P = {}
    
    # set initial values
    for v in V:
        Q.add(v)
        D[v] = float('inf')
    D[s] = 0
    P[s] = None
    
    while len(Q) != 0:
        v, _ = D.extract_min()
        Q.remove(v)
        for w, d in v.get_neighbors():
            if w in Q:
                if d < D[w]:
                    D[w] = d
                    P[w] = v

    tree = []
    tree_sum = 0
    for v in P:
        if P[v] is not None:
            tree.append((P[v], v))
            tree_sum += D[v]

    return tree, tree_sum


def create_adjacency_list(heights):
    """Create and return adjacency list using VertexNode and Node classe.
    Return value is a dict with the coordinates as keys, and the corresponding VertexNode as values."""
    vertices = {}
    for x in range(20):
        for y in range(20):
            h = heights[x][y]
            if h < 0:
                continue
            vertices[(x, y)] = VertexNode((x, y), h)
            
    neighbors = [(i, j) for i in range(-2,3) for j in range(-2, 3) if abs(i) + abs(j) <= 2 and (i, j) != (0, 0)]
    for (x, y), v in vertices.items():
        temp = v
        for i, j in neighbors:
            w = vertices.get((x + i, y + j), None)
            if w:
                temp.next = Node((w, v.dist(w)))
                temp = temp.next

    return vertices