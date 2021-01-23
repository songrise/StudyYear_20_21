import numpy as np


# 生成示例数组
a = np.random.randint(10, size=(3, 3))
b = np.random.randint(10, size=(3, 3))

np.vstack((a,b))
np.pad(a,)