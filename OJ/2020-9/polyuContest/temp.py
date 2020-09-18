base = "aeiou"
for i in range(26):
    temp = ""
    for j in range(len(base)):
        ch = ord(base[j])
        ch = chr(97 + ((ch-97+i) % 26))
        temp += ch
    print(temp)
