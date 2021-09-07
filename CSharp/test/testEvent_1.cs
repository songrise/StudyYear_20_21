delegate void Handler();
class Incrementer
{
    public event Handler CountedADozen;
    public void DoCount()
    {
        for (int i = 1; i < 100; i++)
        {
            if (i % 12 == 0 && CountedADozen != null)
            {
                CountedADozen();// trigger event
            }
        }
    }
}

class Dozens
{
    public int DozensCount { get; private set; }
    public Dozens(Incrementer incrementer)
    {
        DozensCount = 0;
        incrementer.CountedADozen += IncrementDozenCount;//register event
    }

    void IncrementDozenCount()
    {
        DozensCount++;
    }
}

class Program
{
    static void Main()
    {
        var incrementer = new Incrementer();
        Dozens dozensCounter = new Dozens(incrementer);

        incrementer.DoCount();
        Console.WriteLine(dozensCounter.DozensCount);
    }
}