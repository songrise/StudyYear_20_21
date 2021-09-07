# %%
import numpy as np
NUMPATIENTS = 20
LAMBDA = 1/5
CONTROLVAR = 5


def sim_one_run():
    """
    return [[State, Duration],...]
    """
    # init events
    events = [[_, np.random.exponential(scale=1/LAMBDA)]
              for _ in range(NUMPATIENTS)]  # scale param is actually mean
    state_history = []
    crt_events = []

    # the first patient
    crt_events.append(events.pop(0))

    clock = 0  # increment by 5 each time
    finished = []
    while events or crt_events:
        clock += CONTROLVAR
        changed = 0
        for i, e in enumerate(crt_events):
            if e[1] < CONTROLVAR:  # e[1] is the ramaining time of that event
                # an appointment finished in previous time slice
                pre_state = len(crt_events)
                next_state = len(crt_events)-1
                # ? bug here
                state_history.append([pre_state, e[1]])
                finished.append(e[1])
                crt_events.pop(i)  # remove this event
                changed = 1
            else:
                # else one time unit passed
                e[1] -= CONTROLVAR
            if not changed:
                crt_state = len(crt_events)
                state_history.append([crt_state, CONTROLVAR])
            else:
                last_finished = max(finished)
                end_state = len(crt_events)
                state_history.append([end_state, CONTROLVAR - last_finished])

            if events:
                crt_events.append(events.pop(0))

    return state_history  # raw history


def stat(his: list) -> list:
    """
    return [idle time, wating time]
    """
    idle = 0
    waiting = 0
    for s in his:
        if s[0] == 0:
            idle += s[1]
        elif s[0] > 1:
            waiting += s[1]
    return idle, waiting


# %% one run
print("idle time %.4f, waiting time %.4f" % stat(sim_one_run()))

# %%1000 times
NTIMES = 1000
res = np.array([stat(sim_one_run()) for _ in range(NTIMES)])
idle = res[:, 0]
waiting = res[:, 1]
print("idle mean %.4f" % np.mean(idle))
print("waiting mean %.4f" % np.mean(waiting))

# %%
