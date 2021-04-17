# -*- coding : utf-8 -*-
# @FileName  : shcedule.py
# @Author    : Ruixiang JIANG (Songrise)
# @Time      : 2021-04-08
# @Github    ：https://github.com/songrise
# @Descriptions: None

import numpy as np


def priority_schedule(burst_time, arrival_time, priority):
    """
    preemtive
    """
    total_time_slice = sum(burst_time)
    arrived = []
    schedule = []
    for i in range(total_time_slice):
        for j, v in enumerate(arrival_time):
            if v == i:
                arrived.append(j)
        # find highest priority
        min_ = arrived[0]
        for job_i in arrived:
            # assume lower value higher pri.
            if priority[job_i] < priority[min_]:
                # find min priority
                min_ = job_i
        schedule.append(min_+1)  # append the process id
        burst_time[min_] -= 1
        if burst_time[min_] == 0:
            # a job finished
            arrived.remove(min_)
    return schedule


def srf(burst_time, arrival_time):
    burst_time_cpy = burst_time.copy()
    return priority_schedule(burst_time, arrival_time, burst_time_cpy)


def stat(arrival_time, schedule: list):
    n_jobs = len(arrival_time)
    waiting = []
    turnaround = []
    for i in range(n_jobs):
        wait = 0
        last_i = len(schedule) - schedule[::-1].index(i+1)-1
        turnaround.append(last_i-arrival_time[i] + 1)
        for j in range(last_i+1):
            if schedule[j] != i+1:
                wait += 1
        waiting.append(wait-arrival_time[i])
    print("the schedule: ")
    print(schedule)
    print("waiting time: ")
    print(waiting)
    print("avg waiting time %f" % (sum(waiting)/len(waiting)))
    print("turnaround time: ")
    print(turnaround)
    print("avg turnaround time: %f" % (sum(turnaround)/len(turnaround)))


if __name__ == "__main__":
    # burst_time = [10, 1, 2, 1, 5]
    # arrival_time = [0, 0, 0, 0, 0]
    # priority = [3, 1, 4, 5, 2]  # assume smaller number means higher

    # stat(arrival_time, priority_schedule(burst_time, arrival_time, priority))

    # burst_time = [8, 4, 9, 5]
    # arrival_time = [0, 1, 2, 3]
    # stat(arrival_time, srf(burst_time, arrival_time))
    stat([0, 2, 3, 4, 5], [1, 1, 2, 2, 3, 3, 5, 5, 4, 3, 4,
                           1, 5, 1, 4, 2, 1, 5, 1, 4, 5, 1, 1, 2, 3, 4, 5])
