import itertools

min_ = 1000000
# req = [84, 90, 97, 81, 79, 20, 77, 64, 56, 68, 22, 38]
# for _ in req:
#     if sum(req) - _ == 756:
#         print(_)


# for per in itertools.permutations(req, 12):
#     # print(per)
#     holes = [211, 291, 254]
#     for re in per:
#         for i, v in enumerate(holes):
#             # find first avaliable
#             if re <= v:
#                 holes[i] -= re
#                 break
#     if min_ > min(min_, sum(holes)):
#         print(sum(holes))
#         print(per)
#         print(holes)
#         min_ = min(min_, sum(holes))

# print("*****")

# min_ = 1000000
# req = [84, 90, 97, 81, 79, 20, 77, 64, 56, 68, 22, 38]
# for per in itertools.permutations(req, 12):
#     # print(per)
#     holes = [210, 292, 254]
#     for re in per:
#         for i, v in enumerate(holes):
#             # find first avaliable
#             if re <= v:
#                 holes[i] -= re
#                 break
#     if min_ > min(min_, sum(holes)):
#         print(sum(holes))
#         print(per)
#         print(holes)
#         min_ = min(min_, sum(holes))

print("*****")

min_ = 1000000
req = [84, 90, 97, 81, 79, 20, 77, 64, 56, 68, 22, 38]
for per in itertools.permutations(req, 12):
    # print(per)
    holes = [210, 291, 255]
    for re in per:
        for i, v in enumerate(holes):
            # find first avaliable
            if re <= v:
                holes[i] -= re
                break
    if min_ > min(min_, sum(holes)):
        print(sum(holes))
        print(per)
        print(holes)
        min_ = min(min_, sum(holes))

print("*****")

print(min_)


# 48
# (84, 90, 97, 81, 79, 20, 77, 64, 56, 68, 22, 38)
# [17, 12, 19]
# 36
# (84, 90, 97, 81, 79, 20, 77, 64, 68, 56, 22, 38)
# [17, 12, 7]
# 18
# (84, 90, 81, 20, 77, 64, 97, 79, 68, 56, 22, 38)
# [17, 1, 0]
# 2
# (84, 20, 68, 90, 81, 64, 97, 79, 77, 56, 38, 22)
# [1, 0, 1]
# *****
# 48
# (84, 90, 97, 81, 79, 20, 77, 64, 56, 68, 22, 38)
# [16, 13, 19]
# 36
# (84, 90, 97, 81, 79, 20, 77, 64, 68, 56, 22, 38)
# [16, 13, 7]
# 18
# (84, 90, 81, 79, 20, 64, 97, 77, 68, 56, 22, 38)
# [16, 0, 2]
# 2
# (84, 20, 68, 90, 81, 64, 97, 79, 77, 56, 38, 22)
# [0, 1, 1]
# *****
# 48
# (84, 90, 97, 81, 79, 20, 77, 64, 56, 68, 22, 38)
# [16, 12, 20]
# 36
# (84, 90, 97, 81, 79, 20, 77, 64, 68, 56, 22, 38)
# [16, 12, 8]
# 18
# (84, 90, 81, 20, 77, 64, 97, 79, 68, 56, 22, 38)
# [16, 1, 1]
# 2
# (84, 20, 68, 90, 81, 64, 97, 79, 77, 56, 38, 22)
# [0, 0, 2]
