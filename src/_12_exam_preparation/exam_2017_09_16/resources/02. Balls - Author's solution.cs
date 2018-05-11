using System;

public class Balls
{
    private static int[] pockets;

    public static void Main(string[] args)
    {
        int pocketCount = int.Parse(Console.ReadLine());
        int ballCount = int.Parse(Console.ReadLine());
        int pocketSize = int.Parse(Console.ReadLine());

        pockets = new int[pocketCount];

        Gen(0, pocketSize, ballCount);
    }

    static void Gen(int index, int pocketSize, int ballsLeft)
    {
        if (index >= pockets.Length)
        {
            Console.WriteLine(string.Join(", ", pockets));
        }
        else
        {
            if (index == pockets.Length - 1)
            {
                if (ballsLeft <= pocketSize)
                {
                    pockets[index] = ballsLeft;
                    Console.WriteLine(string.Join(", ", pockets));
                }
            }
            else
            {
                int balls = Math.Min(pocketSize, ballsLeft - (pockets.Length - index - 1));
                for (int i = balls; i > 0; i--)
                {
                    pockets[index] = i;
                    Gen(index + 1, pocketSize, ballsLeft - i);
                }
            }
        }
    }
}
