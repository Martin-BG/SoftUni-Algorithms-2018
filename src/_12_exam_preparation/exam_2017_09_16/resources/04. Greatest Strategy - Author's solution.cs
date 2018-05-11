using System;
using System.Collections.Generic;
using System.Linq;

public class GreatestStrat
{
    private static Dictionary<int, HashSet<int>> graph = new Dictionary<int, HashSet<int>>();
    private static Dictionary<int, HashSet<int>> disconnected = new Dictionary<int, HashSet<int>>();

    static void Main()
    {
        int[] args = Console.ReadLine().Split().Select(x => int.Parse(x)).ToArray();
        int N = args[0];
        int M = args[1];
        int root = args[2];

        for (int i = 1; i <= N; i++)
        {
            graph[i] = new HashSet<int>();
            disconnected[i] = new HashSet<int>();
        }

        for (int i = 0; i < M; i++)
        {
            args = Console.ReadLine().Split().Select(x => int.Parse(x)).ToArray();
            int from = args[0];
            int to = args[1];

            graph[from].Add(to);
            graph[to].Add(from);

            disconnected[from].Add(to);
            disconnected[to].Add(from);
        }

        DFS(root, root, new HashSet<int>());

        Console.WriteLine(GetMax());
    }

    private static int GetMax()
    {
        int max = 0;
        var visited = new HashSet<int>();
        foreach (var x in disconnected.Keys)
        {
            if (!visited.Contains(x))
            {
                int value = GetValue(x, visited);

                if (value > max)
                {
                    max = value;
                }
            }
        }

        return max;
    }

    private static int GetValue(int x, HashSet<int> visited)
    {
        visited.Add(x);
        int value = 0;
        foreach (var child in disconnected[x])
        {
            if (!visited.Contains(child))
            {
                value += GetValue(child, visited);
            }
        }

        return value + x;
    }

    private static int DFS(int x, int parent, HashSet<int> visited)
    {
        visited.Add(x);
        int childCount = 0;
        foreach (var child in graph[x])
        {
            if (!visited.Contains(child) && child != parent)
            {
                int subtreeSize = DFS(child, x, visited);

                if (subtreeSize % 2 == 0)
                {
                    disconnected[x].Remove(child);
                    disconnected[child].Remove(x);
                }

                childCount += subtreeSize;
            }
        }

        return childCount + 1;
    }
}