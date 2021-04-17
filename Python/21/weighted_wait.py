weight = [0.8**_ for _ in range(10)]
wait0 = [[13], [1], [0], [18], [2]]  # SRT
wait1 = [[15], [1, 9], [3], [0], [19]]  # pri(linux)
wait2 = [[7], [15], [28], [17], [0]]  # pri(windows)
wait3 = [[4, 8], [2, 10], [4], [6, 8, 3], [8, 8, 3]]  # RR3
wait4 = [[2, 7, 5, 4], [1, 3, 6], [4], [5, 5, 4, 3, 1], [6, 5, 4, 3]]  # RR2
waits = [wait0, wait1, wait2, wait3, wait4]

for wait in waits:
    for i, w in enumerate(wait):
        ww = 0
        for j, n in enumerate(w):
            ww += weight[j]*n
        print("The weighted waiting time for process {} is {}".format(i+1, ww))
    print("+++++++++++++++++++++++++++++++++++")
