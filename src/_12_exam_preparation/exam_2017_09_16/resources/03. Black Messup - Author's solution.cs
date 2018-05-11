using System;
using System.Collections.Generic;
using System.Diagnostics;

public class BlackMessup
{
    static Dictionary<string, Atom> atoms = new Dictionary<string, Atom>();
    static Dictionary<string, List<string>> graph = new Dictionary<string, List<string>>();

    static void Main(string[] args)
    {
        int atomCount = int.Parse(Console.ReadLine());
        int connectionCount = int.Parse(Console.ReadLine());

        for (int i = 0; i < atomCount; i++)
        {
            string[] atomArgs = Console.ReadLine().Split();
            Atom atom = new Atom(atomArgs[0], int.Parse(atomArgs[1]), int.Parse(atomArgs[2]));
            atoms[atom.Name] = atom;
            graph.Add(atom.Name, new List<string>());
        }

        for (int i = 0; i < connectionCount; i++)
        {
            string[] connectionArgs = Console.ReadLine().Split();
            string from = connectionArgs[0];
            string to = connectionArgs[1];
            graph[from].Add(to);
            graph[to].Add(from);
        }

        Stopwatch sw = new Stopwatch();
        sw.Start();

        List<SortedSet<Atom>> molecules = FindConnectedComponents();
        int id = GetBestMoleculeId(molecules);
        Console.WriteLine(GetMoleculeForce(molecules, id));

        sw.Stop();
        //Console.WriteLine(sw.ElapsedMilliseconds);
    }

    private static int GetBestMoleculeId(List<SortedSet<Atom>> ids)
    {
        int bestId = 0;
        int bestScore = 0;

        for (int id = 0; id < ids.Count; id++)
        {
            int score = GetMoleculeForce(ids, id);
            if (score >= bestScore)
            {
                bestScore = score;
                bestId = id;
            }
        }

        return bestId;
    }

    private static int GetMoleculeForce(List<SortedSet<Atom>> molecules, int id)
    {
        int score = 0;
        int maxExpiration = 1;
        List<Atom> taken = new List<Atom>();
        foreach (var atom in molecules[id])
        {
            if (atom.Decay > maxExpiration)
            {
                taken.Add(atom);
                maxExpiration = atom.Decay;
                score += atom.Mass;
            }
            else if (maxExpiration > taken.Count)
            {
                taken.Add(atom);
                score += atom.Mass;
            }
        }

        return score;
    }

    private static List<SortedSet<Atom>> FindConnectedComponents()
    {
        int id = 0;
        List<SortedSet<Atom>> ids = new List<SortedSet<Atom>>();
        HashSet<string> marked = new HashSet<string>();

        foreach (var atom in graph.Keys)
        {
            if (!marked.Contains(atom))
            {
                ids.Add(new SortedSet<Atom>());
                DFS(ids, marked, atom, id++);
            }
        }

        return ids;
    }

    private static void DFS(List<SortedSet<Atom>> ids, HashSet<string> marked, string atom, int id)
    {
        marked.Add(atom);
        ids[id].Add(atoms[atom]);
        foreach (var child in graph[atom])
        {
            if (!marked.Contains(child))
            {
                DFS(ids, marked, child, id);
            }
        }
    }
}

public class Atom : IComparable<Atom>
{
    public Atom(string id, int mass, int decay)
    {
        this.Name = id;
        this.Mass = mass;
        this.Decay = decay;
    }

    public string Name { get; set; }
    public int Mass { get; set; }
    public int Decay { get; set; }

    public int CompareTo(Atom other)
    {
        return -this.Mass.CompareTo(other.Mass);
    }
}