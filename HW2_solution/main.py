### 2018-2 SNU Advanced Programming Methodology
### HW 2 Coding problem
### Main executing file
from graph import create_adjacency_list, Dijkstra, Prim


def read_heights(filename):
    """Read heights data from file and return as 2-d array."""
    heights = []
    with open(filename,'r') as f:
        for l in f.readlines():
            if l == "\n":
                continue
            heights.append([float(h) for h in l.rstrip().split('\t')])
    return heights


def write_paths_to_file(V, D, P, s, path):
    """Print the paths from source vertex to all other vertices and its distances."""
    def get_path(P, s, v):
        p = P[v]
        path = [v]
        while p is not None:
            path.append(p)
            p = P[p]
        return list(reversed(path))
    
    with open(path, 'w') as f:
        for v in V:
            text = "{}: {:5.2f} ".format(v, D[v])
            text += " ".join([str(p) for p in get_path(P,s,v)])
            text += "\n"
            f.write(text)

            
def write_tree_to_file(V, T, T_sum, s, path):
    """Print weight and edges of a tree."""
    with open(path, 'w') as f:
        f.write("{:.2f}\n".format(T_sum))
        for a, b in T:
            f.write("{} {}\n".format(a,b))


if __name__ == "__main__":
    # get heights
    filename0 = 'height.txt'
    print("Reading heights from %s..." % filename0)
    heights = read_heights(filename0)

    # create adjacency list
    print("Creating adjacency list...")
    vertices = create_adjacency_list(heights)
    V = vertices.values()

    # run Dijkstra
    print("Running Dijkstra...")
    s1 = vertices[(0,0)]
    D, P = Dijkstra(V, s1)

    # print paths
    filename1 = 'Output_b'
    print("Writing results to %s..." % filename1)
    write_paths_to_file(V, D, P, s1, filename1)

    # run Prim
    print("Running Prim...")
    s2 = vertices[(19,19)]
    T, T_sum = Prim(V, s2)

    # print tree
    filename2 = 'Output_c'
    print("Writing results to %s..." % filename2)
    write_tree_to_file(V, T, T_sum, s2, filename2)
    
    print("Done!")