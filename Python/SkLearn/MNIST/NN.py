# %%
from sklearn import neural_network, model_selection, metrics
import numpy as np
import seaborn as sns


print("Loading data...")
mnist = np.loadtxt("train.csv", skiprows=1, delimiter=",")
kf = model_selection.KFold(n_splits=10)
data = mnist[:, 1:]
label = mnist[:, 0]

# %% train model
score = []
clf = neural_network.MLPClassifier(
    hidden_layer_sizes=(100, 200),  random_state=1, verbose=1, learning_rate='adaptive')
for i in range(6):
    X_train, X_test, Y_train, Y_test = model_selection.train_test_split(
        data, label, random_state=i)
    clf.partial_fit(X_train, Y_train, classes=np.unique(label))
    score.append(clf.score(X_test, Y_test))
    # score.append(model_selection.cross_val_score(
    #     clf, data, y=label, cv=10, scoring="accuracy", verbose=1, n_jobs=4))
    print(score[i])
# %% plot accuracy graph
sns.relplot(data=score, kind="line",)

# %%  load predict data and apply model
print("Predicting...")
predict_data = np.loadtxt("test.csv", skiprows=1, delimiter=",")
result = clf.predict(predict_data)

result = np.c_[np.arange(1, 28001).reshape(28000,), result]
np.savetxt("predictionNN.csv", result, fmt="%d", delimiter=",")
print("Done")
# %%
