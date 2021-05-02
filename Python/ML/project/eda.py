# %% load modules please do read readme
from sklearn.ensemble import GradientBoostingRegressor
from sklearn.svm import SVR
from sklearn.neighbors import KNeighborsRegressor
import keras
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
from sklearn.neural_network import MLPRegressor
from sklearn.ensemble import StackingRegressor
from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression
from sklearn.metrics import make_scorer
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import cross_val_score
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder


# %% load dataset
train = pd.read_csv("data/Teleplay.csv", delimiter=",")
train["members"].fillna(0, inplace=True)
train["type"].fillna("medium", inplace=True)
# %%
train.head()
# %%
train.describe()

# %% preprocess genre


def tokenize(input_str: str = None) -> list:
    try:
        assert isinstance(input_str, str)
    except AssertionError as e:
        return list()

    return [word.strip() for word in input_str.split(",")]


def get_all_genre() -> list:
    genre = train["genre"]
    all_genre = set()
    for line in genre:
        for item in tokenize(line):
            all_genre.add(item)
    # Python set does not guarantee order, so convert to list
    return list(all_genre)


all_genre = get_all_genre()
all_type = np.unique(train["type"]).tolist()

# %% encode genre


def encode_genre(arr: list, all_genre: list) -> list:
    """
        encode genre to a vector, like "multi-hot encoder"
    """
    encoded = np.zeros_like(all_genre, dtype=np.int64)
    for genre in arr:
        encoded[all_genre.index(genre)] = 1
    return encoded


def encode_type(arr: list, all_type: list) -> list:
    encoded = [all_type.index(t) for t in arr]
    return encoded


# %%
encoded_genre = pd.DataFrame(
    np.array([encode_genre(tokenize(genre), all_genre) for genre in train["genre"]], dtype=np.int64), columns=all_genre, dtype=np.int64)
encoded_type = pd.DataFrame(
    np.array(encode_type(train["type"], all_type), dtype=np.int64), columns=["type"])

train = train.drop(["genre", "type", "name"], axis=1)
train.loc[train["episodes"] == "Unknown", "episodes"] = 0.0

train = train.astype("float")

train = pd.concat([train, encoded_type], axis=1)
train = pd.concat([train, encoded_genre], axis=1)
train.dropna(axis=0, how="any", inplace=True)

# boxplot before capping
plt.rc("figure", figsize=(10, 6))
fig, axes = plt.subplots(2, 1)
sns.boxplot(data=train, x="episodes", ax=axes[0])
sns.boxplot(data=train, x="members", ax=axes[1])
axes[0].set_title(
    "Box Plot illustrating long-tailed distribution of some features")
plt.show()

#!! baselines
# %% baseline models
model_result = []  # RMSE of models

train = train.astype("float")
train_Y = train["rating"]
train_X = train.drop(["rating", "teleplay_id"], axis=1)
# %% baseline random forest
rf = RandomForestRegressor(random_state=4434)
train_X, val_X, train_Y, val_Y = train_test_split(
    train_X, train_Y, test_size=0.2)
rf.fit(train_X, train_Y)
mean_squared_error(val_Y, rf.predict(val_X))
result = np.sqrt(cross_val_score(rf, train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE: %.4f" % result.mean())
model_result.append(result.mean())
# %% feature importance
plt.rc("figure", figsize=(6, 5))
importance = rf.feature_importances_
sns.barplot(train_X.columns[:3], importance[:3], palette="Blues_d")
# %%
# #!data visualization
# %%
plt.rc("figure", figsize=(15, 10))
fig, axes = plt.subplots(2, 2)
sns.distplot(train["rating"], ax=axes[0][0])
axes[0][0].set_title("Distribution of Rating")
sns.distplot(train[train["episodes"] < 100]["episodes"], ax=axes[0][1])
axes[0][1].set_title("Distribution of episodes (trimmed)")
sns.distplot(train[train["members"] < 1e4]["members"], ax=axes[1][0])
axes[1][0].set_title("Distribution of members (trimmed)")
sns.histplot(train["type"], ax=axes[1][1],)
axes[1][1].set_title("Distribution of types")
axes[1][1].set_xticklabels([""]+all_type)
plt.show()
# %% pair-wise plot
# pair_fig = sns.pairplot(train[["episodes", "rating", "members", "type"]])
# pair_fig.savefig("pair_plot.png")
# plt.rc("figure", figsize=(6, 4))
# %% corr
plt.rc("figure", figsize=(6, 4))
corr = sns.heatmap(train[["episodes", "rating", "members", "type"]
                         ].corr(), vmin=0, vmax=1, center=0, cmap="YlGnBu", annot=True)
corr.get_figure().savefig("corr.png")

# capping
train.loc[train["episodes"] > 1000, "episodes"] = 1000
train.loc[train["members"] > 10000, "members"] = 10000
# %% plain LR
lr = LinearRegression()
result = np.sqrt(cross_val_score(lr, train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE of LR: %.4f" % result.mean())
model_result.append(result.mean())
# %% Polynomial LR
expansion = PolynomialFeatures()
poly_train_X = expansion.fit_transform(train_X)
PLR = LinearRegression()
result = np.sqrt(cross_val_score(PLR, poly_train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE of Polynomial LR: %.4f" % result.mean())
model_result.append(result.mean())


# %%mlp baseline notice that the performance is random seed sensitive
mlp = keras.models.Sequential()
mlp.add(keras.layers.Input([46, ]))
mlp.add(keras.layers.BatchNormalization())
mlp.add(keras.layers.Dense(300, activation="relu"))
mlp.add(keras.layers.BatchNormalization())
mlp.add(keras.layers.Dense(200, activation="relu"))
mlp.add(keras.layers.BatchNormalization())
mlp.add(keras.layers.Dense(1))
mlp.compile(loss=['mse'], metrics=['mse'])
mlp.summary()
es = keras.callbacks.EarlyStopping(patience=8, restore_best_weights=True)
history = mlp.fit(train_X, train_Y, epochs=40,
                  validation_data=(val_X, val_Y), callbacks=[es],)
plt.rc("figure", figsize=(6, 4))
plt.xlabel("epoches")
plt.ylabel("loss")
plt.plot(history.history['mse'])
plt.plot(history.history['val_mse'])
result = np.sqrt(mean_squared_error(train_Y, mlp.predict(train_X)))
model_result.append(result.mean())
# result = cross_val_score(mlp, train_X, train_Y,
#                          scoring=make_scorer(mean_squared_error))
# %%knn regressor
# a magic number 27, I will show the reason in final report.
knr = KNeighborsRegressor(27)
knr.fit(train_X, train_Y)
result = np.sqrt(cross_val_score(knr, train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE of knn: %.4f" % result.mean())
model_result.append(result.mean())

# %% Gradient-based Decision Tree boosting
gbr = GradientBoostingRegressor(random_state=4434)
gbr.fit(train_X, train_Y)
result = np.sqrt(cross_val_score(gbr, train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE of gbr: %.4f" % result.mean())
model_result.append(result.mean())
# %% support vector machine regression, with gaussian kernel
svr = SVR()
svr.fit(train_X, train_Y)
result = np.sqrt(cross_val_score(svr, train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE of svr: %.4f" % result.mean())
model_result.append(result.mean())
# %%visualize model performance
# %%
plt.rc("figure", figsize=(8, 6))
sns.barplot(["RF", "LR", "Polynomial LR", "MLP", "KNN", "GBDT", "SVM"],
            model_result, palette="Blues_d")

plt.ylabel("5-fold CV RMSE")
# %% model stacking
print("training Stacking model may take long time, please wait...")
estimators = [('lr', lr), ('rf', rf), ('knn', knr),
              ('gbdt', gbr), ('svm', svr)]
stacking = StackingRegressor(
    estimators=estimators, final_estimator=LinearRegression())
stacking.fit(train_X, train_Y)
result = np.sqrt(cross_val_score(stacking, train_X, train_Y,
                                 scoring=make_scorer(mean_squared_error), cv=5))
print("avg RMSE of stacking: %.4f" % result.mean())
model_result.append(result.mean())
# %%
new_tele = pd.read_csv("New_teleplay.csv", delimiter=",")
new_tele["members"].fillna(0, inplace=True)
new_tele["type"].fillna("medium", inplace=True)
encoded_genre = pd.DataFrame(
    np.array([encode_genre(tokenize(genre), all_genre) for genre in new_tele["genre"]], dtype=np.int64), columns=all_genre, dtype=np.int64)
encoded_type = pd.DataFrame(
    np.array(encode_type(new_tele["type"], all_type), dtype=np.int64), columns=["type"])

new_tele = new_tele.drop(
    ["genre", "type", "name", "rating", "teleplay_id"], axis=1)
new_tele.loc[new_tele["episodes"] == "Unknown", "episodes"] = 0.0
new_tele = new_tele.astype("float")
new_tele = pd.concat([new_tele, encoded_type], axis=1)
new_tele = pd.concat([new_tele, encoded_genre], axis=1)
new_tele.loc[new_tele["episodes"] > 1000, "episodes"] = 1000
new_tele.loc[new_tele["members"] > 10000, "members"] = 10000
# %%
prediction = rf.predict(new_tele)
new_tele = pd.read_csv("New_teleplay.csv", delimiter=",")
new_tele["rating"] = prediction
new_tele.to_csv("19079662D_ task1.csv", index=False)
