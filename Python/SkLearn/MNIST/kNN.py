# %%
from sklearn import neighbors
import numpy as np
import seaborn as sns
print("Loading data...")
mnist = np.loadtxt("train.csv", skiprows=1, delimiter=",")
testBoundary = 35000
train_data = mnist[:test, 1:]
train_label = mnist[:test, 0]

print("Training...")
clf = neighbors.KNeighborsClassifier(
    n_neighbors=5, n_jobs=-1, metric='euclidean')
clf.fit(train_data, train_label)
# test model
print(clf.score(mnist[test:, 1:], mnist[test:, 0]))

# %%  load predict data and apply model
print("Predicting...")
predict_data = np.loadtxt("test.csv", skiprows=1, delimiter=",")
result = clf.predict(predict_data)
Numid = np.arange(1, 28001).reshape(28000,)

result = np.c_[Numid, result]
np.savetxt("prediction.csv", result, fmt="%d", delimiter=",")
print("done")
# %%
