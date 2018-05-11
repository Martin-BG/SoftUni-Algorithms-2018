using System;
using System.Collections.Generic;

public class AbaspaBasapa
{
    static string firstStr = "abba";
    static string secondStr = "ba";

    static int[,] lcs;

    static int max = 0;
    static int maxX = -1;
    static int maxY = -1;

    static void Main(string[] args)
    {
        firstStr = Console.ReadLine();
        secondStr = Console.ReadLine();

        lcs = new int[firstStr.Length, secondStr.Length];

        for (int x = 0; x < firstStr.Length; x++)
        {
            for (int y = 0; y < secondStr.Length; y++)
            {
                if (firstStr[x] == secondStr[y])
                {
                    lcs[x, y] = GetPrev(x, y) + 1;
                }
                else
                {
                    lcs[x, y] = 0;
                }

                if (lcs[x, y] > max)
                {
                    max = lcs[x, y];
                    maxX = x;
                    maxY = y;
                }
            }
        }

        Stack<char> str = new Stack<char>();
        while (maxX >= 0 && maxY >= 0 && lcs[maxX, maxY] != 0)
        {
            str.Push(firstStr[maxX]);
            maxX--;
            maxY--;
        }

        Console.WriteLine(string.Join("", str));
    }

    static int GetPrev(int x, int y)
    {
        if (x - 1 < 0 || y - 1 < 0)
        {
            return 0;
        }

        return lcs[x - 1, y - 1];
    }
}
