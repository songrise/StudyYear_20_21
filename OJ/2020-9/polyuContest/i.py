width, ad = input().split()
width = int(width)
min_ = min(ad)

minIndex = [i for i, ch in enumerate(ad) if ch == min_]
ad *= 2

possible = ["".join(ad[i:i+width]) for i in minIndex]

print(min(possible))
