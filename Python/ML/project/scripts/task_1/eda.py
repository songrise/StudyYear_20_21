# %% load modules please do read readme
from sklearn.preprocessing import Normalizer
from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import RobustScaler
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

# clipping
train.loc[train["episodes"] > 1000, "episodes"] = 1000


# %%illustration of feature transformation
train.loc[train["members"] > 10000, "members"] = 10000
plt.rc("figure", figsize=(8, 10))
fig, axes = plt.subplots(2, 1)

sns.distplot(train[train["members"] < 10000]["members"], ax=axes[0])
axes[0].set_title("Distribution of members (raw)")
transformer = ColumnTransformer(
    transformers=[('cat', RobustScaler(), ["members"])])
members_transformed = transformer.fit_transform(train)

train.members = members_transformed
sns.distplot(train["members"], ax=axes[1])
axes[1].set_title("Distribution of members (after transformation)")

plt.show()
# %%
transformer = ColumnTransformer(
    transformers=[('cat', Normalizer(), ["members"])])
members_transformed = transformer.fit_transform(train)
train.members = members_transformed
sns.distplot(train["members"])

# %%
