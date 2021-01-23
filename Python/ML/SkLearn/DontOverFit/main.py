# %% load data
from sklearn.ensemble import AdaBoostClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
import numpy as np
import seaborn as sns

train = np.loadtxt("train.csv", skiprows=1, delimiter=",")
test = np.loadtxt("test.csv", skiprows=1, delimiter=",")

# %% data preprocess
train_label = train[:, 1]
train_data = train[:, 2:]
X_train, X_val, Y_train, Y_val = train_test_split(
    train_data, train_label)

# %% model setup
# clf = RandomForestClassifier(n_estimators=200, min_impurity_split=4, verbose=1)
clf = AdaBoostClassifier(
    base_estimator=DecisionTreeClassifier(min_samples_split=4))
clf.fit(X_train, Y_train)
clf.score(X_val, Y_val)

# %% predict
result = clf.predict(test[:, 1:])
result = np.c_[np.arange(250, 20000).reshape(19750,), result]
np.savetxt("predictionNN.csv", result, fmt="%.d",
           delimiter=",", header="id,target")

# %%
