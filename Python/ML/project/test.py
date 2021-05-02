
# %%
# missing data
def process(data):

    members_mean = data["members"].mean()
    data["members"].fillna(members_mean, inplace=True)
    data["type"].fillna("medium", inplace=True)  # our assumption
    episodes_mean = data[data["episodes"] != "Unknown"].mean()
    data.loc[data["episodes"] == "Unknown", "episodes"] = episodes_mean

    # vectorization
    encoded_genre = pd.DataFrame(
        np.array([encode_genre(tokenize(genre), all_genre) for genre in data["genre"]], dtype=np.int64), columns=all_genre, dtype=np.int64)
    encoded_type = pd.DataFrame(
        np.array(encode_type(data["type"], all_type), dtype=np.int64), columns=["type"])

    # drop useless feature,
    data = data.drop(["genre", "type", "name"], axis=1)

    data = data.astype("float")
    # concatenate vectorized type and genre
    data = pd.concat([data, encoded_type], axis=1)
    data = pd.concat([data, encoded_genre], axis=1)
    data.dropna(axis=0, how="any", inplace=True)

    # %% Capping (to deal with outliers)
    data.loc[data["episodes"] > 1000, "episodes"] = 1000
    data.loc[data["members"] > 10000, "members"] = 10000


# %% after preprocessing
train.head()
# np.random.shuffle(train)
