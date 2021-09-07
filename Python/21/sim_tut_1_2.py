# %%sim2
import numpy as np
import seaborn as sns
from scipy import stats


def heron(points) -> float:
    a = np.sqrt(np.sum(np.abs(np.square(
        points[0][0] - points[1][0]))+np.abs(np.square(points[0][1]-points[1][1]))))
    b = np.sqrt(np.sum(np.abs(np.square(
        points[1][0] - points[2][0]))+np.abs(np.square(points[1][1] - points[2][1]))))
    c = np.sqrt(np.sum(np.abs(np.square(
        points[2][0] - points[0][0]))+np.abs(np.square(points[2][1] - points[0][1]))))
    p = (a+b+c)/2
    area = np.sqrt(p*(p-a)*(p-b)*(p-c))
    return area


N_ITER = 100
BATCHSIZE = 1000
res = []
for _ in range(N_ITER):
    sum_ = 0
    for __ in range(BATCHSIZE):
        pts = np.random.rand(3, 2)
        sum_ += heron(pts)
    res.append(sum_/BATCHSIZE)


print("Expected area of triangle %.4f" % np.mean(res))
print("Variance %f" % np.var(res))

sns.distplot(res, fit=stats.norm, kde=False)
# %%
# Sim_1
import seaborn as sns
from scipy import stats   
N_ITER = 1000
BATCHSIZE = 1000
res = []
for _ in range(N_ITER):
    s = np.random.rand(1, BATCHSIZE)
    I = [1 for _ in s[0] if _ > 0.6]
    res.append(np.sum(I)/BATCHSIZE)


print("Expected probability for U(0,1)>0.6 is %.3f" % np.mean(res))
sd = np.std(res)
print("SD %f" % sd)

# Z = 1.96
# interval = Z * sd/np.sqrt(BATCHSIZE)
# print("95CI %f" % interval)

sns.distplot(res, fit=stats.norm, kde=False)

# %%
