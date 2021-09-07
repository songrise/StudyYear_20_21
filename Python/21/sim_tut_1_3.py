import numpy as np
import seaborn as sns


def simrun(m):


    seats = np.arange(1, m+1)  # seats numbered 1,...,m
    s = rnd.choice(seats, 1)  # first person
    i = np.where(seats == s)
    seats = np.delete(seats, i)  # person takes seat i
    for p in range(2, m):  # persons 2,...,m-1
        if p in seats:  # his seat is available
i = np.where(seats == p)
seats = np.delete(seats, i)  # person takes seat
else:  # seta is taken
s = rnd.choice(seats, 1)
i = np.where(seats == s)
seats = np.delete(seats, i)
if seats[0] == m:
return 1  # last person has his own seat
else:
return 0
