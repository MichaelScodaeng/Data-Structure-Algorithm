class Graph(object):
    def __init__(self, graph_dict=None):
        if graph_dict == None:
            self.graph_dict = {}
        else:
            self.graph_dict = graph_dict

    def allVertices(self):
        return list(self.graph_dict.keys())

    def getEdges(self, vertex):
        return self.graph_dict[vertex]

    def allEdges(self):
        return self.__generateEdges()

    def addVertex(self, vertex):
        if (vertex not in self.graph_dict):
            self.graph_dict[vertex] = []

    def addEdge(self, edge):
        edge = set(edge)
        vertex1, vertex2 = tuple(edge)
        for x, y in [(vertex1, vertex2), (vertex2, vertex1)]:
            if x in self.graph_dict:
                self.graph_dict[x].add(y)
            else:
                self.graph_dict[x] = [y]

    def __generateEdges(self):
        edges = []
        for vertex in self.graph_dict:
            for neighbour in self.graph_dict[vertex]:
                if {neighbour, vertex} not in edges:
                    edges.append({vertex, neighbour})
        return edges

    def __iter__(self):
        self._iter_obj = iter(self.graph_dict)
        return self._iter_obj

    def __next__(self):
        """ allows us to iterate over the vertices """
        return next(self._iter_obj)

    def __str__(self):
        res = "vertices: "
        for k in self.graph_dict:
            res += str(k) + " "
        res += "\nedges: "
        for edge in self.__generateEdges():
            res += str(edge) + " "
        return res

    def find_path(self, start_vertex, end_vertex, path=None):
        """ find a path from start_vertex to end_vertex
            in graph """
        if path == None:
            path = []
        graph = self.graph_dict
        path = path + [start_vertex]
        if start_vertex == end_vertex:
            return path
        if start_vertex not in graph:
            return None
        for vertex in graph[start_vertex]:
            if vertex not in path:
                extended_path = self.find_path(vertex,
                                               end_vertex,
                                               path)
                if extended_path:
                    return extended_path
        return None

    def find_all_paths(self, start_vertex, end_vertex, path=[]):
        """ find all paths from start_vertex to
            end_vertex in graph """
        graph = self.graph_dict
        path = path + [start_vertex]
        if start_vertex == end_vertex:
            return [path]
        if start_vertex not in graph:
            return []
        paths = []
        for vertex in graph[start_vertex]:
            if vertex not in path:
                extended_paths = self.find_all_paths(vertex,
                                                     end_vertex,
                                                     path)
                for p in extended_paths:
                    paths.append(p)
        return paths
def bfs_traversal(graph,start):
    explored = []
    queue = [start]
    while(queue):
        node = queue.pop(0)
        if node not in explored:
            explored.append(node)
            neighbors = graph[node]
            for i in neighbors:
                queue.append(i)
    return explored
def dfs_traversal(graph,start):
    explored = []
    stack = [start]
    while(stack):
        node = stack[-1]
        if node not in explored:
            explored.extend(node)
        removed = True
        for next in graph[node]:
            if next not in explored:
                stack.extend(next)
                removed = False
                break
        if removed:
            stack.pop()
    return explored




if __name__ == "__main__":
    g = { "a" : {"d"},
          "b" : {"c"},
          "c" : {"b", "c", "d", "e"},
          "d" : {"a", "c"},
          "e" : {"c"},
          "f" : {}
          }


    graph = Graph(g)

    print("Vertices of graph:")
    print(graph.allVertices())

    print("Edges of graph:")
    print(graph.allEdges())



    print('The path from vertex "a" to vertex "b":')
    path = graph.find_path("a", "b")
    print(path)

    print('The path from vertex "a" to vertex "f":')
    path = graph.find_path("a", "f")
    print(path)

    graph02 = {'A': ['B', 'C', 'E'],
             'B': ['A','D', 'E'],
             'C': ['A', 'F', 'G'],
             'D': ['B'],
             'E': ['A', 'B','D'],
             'F': ['C'],
             'G': ['C']}
    print("bfs: ", bfs_traversal(graph02,"A"))
    print("dfs: ", dfs_traversal(graph02,"A"))


