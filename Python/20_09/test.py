
arr = [0, 15, 99, 42, 51, 27, 38, 63, 32]
p = arr[8]
i = 0
for j in range(1, 8):
    if arr[j] <= p:
        i += 1
        arr[i], arr[j] = arr[j], arr[i]
    print(i, j, arr)
