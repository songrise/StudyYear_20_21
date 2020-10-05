def fib(n: int):
    a = 1
    b = 1
    ans = 0
    for i in range(n-2):
        ans = a+b
        a = b
        b = ans
    return ans


def f1(n):
    return pow(8/5, n-1)


def f2(n):
    return pow(13/8, n-1)


for i in range(3, 40):
    print("i = {}| {} {:.2f} {:.2f}".format(i, fib(i), f1(i), f2(i)))
