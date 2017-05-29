import networkx as nx

def main():
    dagLength = int(input())
    while dagLength != 0:
        dag = []
        for i in range(dagLength):
            content = input()
            node = []
            node = content.split()
            if len(node) != 0:
                node = map(int, node)
            dag += [node]
        temp = [x for x in dag if x != []]
        if len(temp) == 0:
            print(0)
        elif dag[0] == []:
            print(0)
        else:
            G = nx.DiGraph()
            for i in range(len(dag) -1):
                for j in dag[i]:
                    G.add_edge(i,j)
            for n in G:
                for nbr in G[n]:
                    G[n][nbr]['weight'] = -1
            pred, dist = nx.bellman_ford(G, 0)
            print(-dist)
        dagLength = int(input())

main()
