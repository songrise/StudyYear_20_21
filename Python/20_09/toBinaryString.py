def toBinaryString(num: int) -> str:
    ans = ""
    while num > 0:
        ans = str((num % 2)) + ans
        num //= 2

    return ans


print(toBinaryString(8))
