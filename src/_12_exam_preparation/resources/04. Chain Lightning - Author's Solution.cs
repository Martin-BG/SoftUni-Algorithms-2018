using System;
using System.Collections.Generic;
using System.Linq;

public class Program
{
    static private bool[] marked;
    static private int[] damage;
    static private PriorityQueue<Edge> queue;
    static private Dictionary<int, List<int>> msf;

    static void Main(string[] args)
    {
        int houseCount = int.Parse(Console.ReadLine());
        int distanceCount = int.Parse(Console.ReadLine());
        int lightningCount = int.Parse(Console.ReadLine());

        List<Edge>[] graph = new List<Edge>[houseCount];
        for (int i = 0; i < houseCount; i++)
        {
            graph[i] = new List<Edge>();
        }

        for (int i = 0; i < distanceCount; i++)
        {
            int[] edgeArgs = Console.ReadLine().Split().Select(int.Parse).ToArray();
            int from = edgeArgs[0];
            int to = edgeArgs[1];
            int weight = edgeArgs[2];
            Edge edge = new Edge(from, to, weight);
            graph[from].Add(edge);
            graph[to].Add(edge);
        }

        marked = new bool[graph.Length];
        damage = new int[graph.Length];
        msf = new Dictionary<int, List<int>>();

        for (int i = 0; i < graph.Length; i++)
        {
            LazyPrimMSF(graph, i);
        }

        for (int i = 0; i < lightningCount; i++)
        {
            int[] lightningArgs = Console.ReadLine().Split().Select(int.Parse).ToArray();
            int source = lightningArgs[0];
            int power = lightningArgs[1];
            DFS(source, source, power);
        }

        Console.WriteLine(damage.Max());
    }

    private static void DFS(int vertex, int parent, int power)
    {
        damage[vertex] += power;
        foreach (var child in msf[vertex])
        {
            if (child != parent)
            {
                DFS(child, vertex, power / 2);
            }
        }
    }

    public static void LazyPrimMSF(List<Edge>[] graph, int source)
    {
        queue = new PriorityQueue<Edge>();

        Visit(graph, source);

        while (queue.Count > 0)
        {
            Edge edge = queue.DequeueMin();
            int v = edge.Either();
            int w = edge.Other(v);

            if (marked[v] && marked[w]) continue;

            AddEdgeToMSF(edge);

            if (!marked[v]) Visit(graph, v);
            if (!marked[w]) Visit(graph, w);
        }
    }

    private static void AddEdgeToMSF(Edge edge)
    {
        if (!msf.ContainsKey(edge.V))
        {
            msf.Add(edge.V, new List<int>());
        }

        if (!msf.ContainsKey(edge.W))
        {
            msf.Add(edge.W, new List<int>());
        }

        msf[edge.V].Add(edge.W);
        msf[edge.W].Add(edge.V); 
    }

    private static void Visit(List<Edge>[] graph, int v)
    {
        marked[v] = true;
        foreach (var edge in graph[v])
        {
            if (!marked[edge.Other(v)])
            {
                queue.Enqueue(edge);
            }
        }
    }
}

public class Edge : IComparable<Edge>
{
    public Edge(int v, int w, int weight)
    {
        this.V = v;
        this.W = w;
        this.Weight = weight;
    }

    public int V { get; private set; }
    public int W { get; private set; }
    public int Weight { get; private set; }

    public int Either()
    {
        return this.V;
    }

    public int Other(int v)
    {
        if (this.V == v) return this.W;
        if (this.W == v) return this.V;

        throw new ArgumentException("Invalid edge");
    }

    public int CompareTo(Edge other)
    {
        return this.Weight.CompareTo(other.Weight);
    }

    public override string ToString()
    {
        return string.Format($"{V}-{W} {Weight}");
    }
}

public class PriorityQueue<T> where T : IComparable<T>
{
    private Dictionary<T, int> searchCollection;
    private List<T> heap;

    public PriorityQueue()
    {
        this.heap = new List<T>();
        this.searchCollection = new Dictionary<T, int>();
    }

    public int Count
    {
        get
        {
            return this.heap.Count;
        }
    }

    public T DequeueMin()
    {
        var min = this.heap[0];
        var last = this.heap[this.heap.Count - 1];
        this.searchCollection[last] = 0;
        this.heap[0] = last;
        this.heap.RemoveAt(this.heap.Count - 1);
        if (this.heap.Count > 0)
        {
            this.Sink(0);
        }

        this.searchCollection.Remove(min);

        return min;
    }

    public T PeekMin()
    {
        return this.heap[0];
    }

    public void Enqueue(T element)
    {
        this.searchCollection.Add(element, this.heap.Count);
        this.heap.Add(element);
        this.Swim(this.heap.Count - 1);
    }

    private void Sink(int i)
    {
        var left = (2 * i) + 1;
        var right = (2 * i) + 2;
        var smallest = i;

        if (left < this.heap.Count && this.heap[left].CompareTo(this.heap[smallest]) < 0)
        {
            smallest = left;
        }

        if (right < this.heap.Count && this.heap[right].CompareTo(this.heap[smallest]) < 0)
        {
            smallest = right;
        }

        if (smallest != i)
        {
            T old = this.heap[i];
            this.searchCollection[old] = smallest;
            this.searchCollection[this.heap[smallest]] = i;
            this.heap[i] = this.heap[smallest];
            this.heap[smallest] = old;
            this.Sink(smallest);
        }
    }

    private void Swim(int i)
    {
        var parent = (i - 1) / 2;
        while (i > 0 && this.heap[i].CompareTo(this.heap[parent]) < 0)
        {
            T old = this.heap[i];
            this.searchCollection[old] = parent;
            this.searchCollection[this.heap[parent]] = i;
            this.heap[i] = this.heap[parent];
            this.heap[parent] = old;

            i = parent;
            parent = (i - 1) / 2;
        }
    }

    public void DecreaseKey(T element)
    {
        int index = this.searchCollection[element];
        this.Swim(index);
    }
}

