import subprocess
import sys
import codecs
import time
# 常用编码
GBK = 'gbk'
UTF8 = 'utf-8'

current_encoding = UTF8

while True:
    for i in range(500):
        popen = subprocess.Popen('./ans_gen.exe',
                                 stdout=subprocess.PIPE,
                                 stderr=subprocess.PIPE,
                                 bufsize=1)
        out, err = popen.communicate()
        out = out.decode().strip()
        err = err.decode().strip()
        out = out[::-1]
        f = True
        for j in range(15):
            if out[j] != '0':
                f = False
                break
        if f:
            print("ANS Found: {}".format(err))

    print("500 tries...")
