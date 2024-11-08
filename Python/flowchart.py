# from graphviz import Digraph

# # Create a new Digraph object
# dot = Digraph()

# # Add nodes to the graph
# dot.node('Start', 'Start')
# dot.node('p < r', 'p < r')
# dot.node('PARTITION', 'PARTITION')
# dot.node('q ← PARTITION', 'q ← PARTITION')
# dot.node('QUICKSORT(A, p, q - 1)', 'QUICKSORT(A, p, q - 1)')
# dot.node('QUICKSORT(A, q + 1, r)', 'QUICKSORT(A, q + 1, r)')
# dot.node('End', 'End')

# # Add edges between nodes
# dot.edge('Start', 'p < r')
# dot.edge('p < r', 'PARTITION')
# dot.edge('PARTITION', 'q ← PARTITION')
# dot.edge('q ← PARTITION', 'QUICKSORT(A, p, q - 1)')
# dot.edge('q ← PARTITION', 'QUICKSORT(A, q + 1, r)')
# dot.edge('QUICKSORT(A, p, q - 1)', 'End')
# dot.edge('QUICKSORT(A, q + 1, r)', 'End')

# # Save the graph to a file
# dot.render('quicksort_flowchart', format='png', cleanup=True)

# print("Flowchart created successfully.")


# import networkx as nx
# import matplotlib.pyplot as plt

# # Function to draw the flowchart
# def draw_flowchart():
#     G = nx.DiGraph()

#     # Add nodes
#     nodes = ['Start', 'p < r', 'PARTITION', 'q ← PARTITION', 'QUICKSORT(A, p, q - 1)', 'QUICKSORT(A, q + 1, r)', 'End']
#     G.add_nodes_from(nodes)

#     # Add edges
#     edges = [('Start', 'p < r'), ('p < r', 'PARTITION'), ('PARTITION', 'q ← PARTITION'),
#              ('q ← PARTITION', 'QUICKSORT(A, p, q - 1)'), ('q ← PARTITION', 'QUICKSORT(A, q + 1, r)'),
#              ('QUICKSORT(A, p, q - 1)', 'End'), ('QUICKSORT(A, q + 1, r)', 'End')]
#     G.add_edges_from(edges)

#     # Draw graph
#     pos = nx.spring_layout(G)
#     nx.draw_networkx_nodes(G, pos, node_size=2000, node_color='lightblue')
#     nx.draw_networkx_labels(G, pos, font_size=10, font_family='sans-serif')
#     nx.draw_networkx_edges(G, pos, edge_color='black', arrows=True)
#     plt.title('Flowchart for Quicksort Algorithm')
#     plt.axis('off')
#     plt.show()

#     return G

# # Calculate Cyclomatic Complexity
# def calculate_cyclomatic_complexity(G):
#     V = len(G.nodes())
#     E = len(G.edges())
#     C = E - V + 2
#     return C

# # Main function
# if __name__ == "__main__":
#     flowchart_graph = draw_flowchart()
#     cyclomatic_complexity = calculate_cyclomatic_complexity(flowchart_graph)
#     print("Cyclomatic Complexity:", cyclomatic_complexity)




from graphviz import Digraph

def generate_flowchart():
    dot = Digraph()

    # Add nodes
    dot.node('Start', 'Start', shape='parallelogram')
    dot.node('p < r', 'p < r', shape='diamond')
    dot.node('Partition', 'Partition', shape='rectangle')
    dot.node('q ← PARTITION', 'q ← PARTITION', shape='rectangle')
    dot.node('QuickSort1', 'QuickSort(A, p, q - 1)', shape='rectangle')
    dot.node('QuickSort2', 'QuickSort(A, q + 1, r)', shape='rectangle')
    dot.node('End', 'End', shape='parallelogram')

    # Add edges
    dot.edge('Start', 'p < r', label='Yes')
    dot.edge('p < r', 'Partition', label='Yes')
    dot.edge('Partition', 'q ← PARTITION')
    dot.edge('q ← PARTITION', 'QuickSort1')
    dot.edge('q ← PARTITION', 'QuickSort2')
    dot.edge('QuickSort1', 'End')
    dot.edge('QuickSort2', 'End')

    return dot

def generate_graph():
    G = Digraph()

    # Add nodes
    nodes = ['Start', 'p < r', 'Partition', 'q ← PARTITION', 'QuickSort1', 'QuickSort2', 'End']
    for node in nodes:
        G.node(node)

    # Add edges
    edges = [('Start', 'p < r'), ('p < r', 'Partition'), ('Partition', 'q ← PARTITION'), 
             ('q ← PARTITION', 'QuickSort1'), ('q ← PARTITION', 'QuickSort2'),
             ('QuickSort1', 'End'), ('QuickSort2', 'End')]
    for edge in edges:
        G.edge(*edge)

    return G

if __name__ == "__main__":
    # Generate and save flowchart
    flowchart = generate_flowchart()
    flowchart.render('quicksort_flowchart', format='png', cleanup=True)

    # Generate and save graph
    graph = generate_graph()
    graph.render('quicksort_graph', format='png', cleanup=True)

    print("Flowchart and graph generated successfully.")



# from graphviz import Digraph

# def generate_graph():
#     G = Digraph()

#     # Add nodes
#     nodes = ['Start', 'p < r', 'Partition', 'q ← PARTITION', 'QuickSort1', 'QuickSort2', 'End']
#     for i, node in enumerate(nodes, start=1):
#         G.node('n{}'.format(i), '{}'.format(node))

#     # Add edges
#     edges = [('n1', 'n2'), ('n2', 'n3'), ('n3', 'n4'), ('n4', 'n5'), ('n4', 'n6'), ('n5', 'n7'), ('n6', 'n7')]
#     for i, edge in enumerate(edges, start=1):
#         G.edge('n{}'.format(edge[0]), 'n{}'.format(edge[1]), label='e{}'.format(i))

#     return G

# if __name__ == "__main__":
#     # Generate and save graph
#     graph = generate_graph()
#     graph.render('quicksort_graph', format='png', cleanup=True)

#     print("Graph generated successfully.")
