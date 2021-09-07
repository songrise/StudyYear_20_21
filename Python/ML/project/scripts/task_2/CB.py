# %%
from sklearn.neural_network import MLPRegressor
from sklearn.svm import SVR
from sklearn.model_selection import KFold
from sklearn.ensemble import BaggingRegressor
from sklearn.linear_model import Lasso
from sklearn.linear_model import SGDRegressor
from sklearn.metrics import mean_squared_error
from my_linear_regression import MyLinearRegression
from sklearn.ensemble import RandomForestRegressor
from sklearn.model_selection import train_test_split
import pandas as pd
import numpy as np
import tensorflow as tf
from tensorflow.keras import backend as K
# %%
rating = pd.read_csv("../../data/Rating.csv", delimiter=",")
teleplay = pd.read_csv("../../data/processed_teleplay.csv", delimiter=",")
teleplay.sort_values(["teleplay_id"], inplace=True)
# %%
UID = 53698
SEED = 4434
user_rating = rating.loc[rating["user_id"] == UID]
del rating  # save memory
# %% perfrom natural join on two tabels
# we save the rating of a particular user and the rating by all users
# as two separate feature.
merged = pd.merge(user_rating, teleplay, on="teleplay_id")

# %%
train = merged[merged["rating_x"] != -1]  # rated movies
y_train = train["rating_x"]
X_train = train.drop(["rating_x", "user_id"], axis=1)
# %%model evaluation
kf = KFold(5)
# lr = MyLinearRegression()
# # lr = Lasso() #sklearn implementation as reference benchmark

lr_rmse = []
for train_index, test_index in kf.split(X_train):
    lr_X_train, lr_X_test = X_train.iloc[train_index], X_train.iloc[test_index]
    lr_y_train, lr_y_test = y_train.iloc[train_index], y_train.iloc[test_index]
    lr = MyLinearRegression()
    # lr = MyLinearRegression(poly_degree=2)

    lr.fit(lr_X_train, lr_y_train)
    lr_rmse.append(np.sqrt(mean_squared_error(
        lr.predict(lr_X_test), lr_y_test)))

print("LR, 5fold RMSE ", np.mean(lr_rmse))

# %%mlp
mlp = tf.keras.models.Sequential()
mlp.add(tf.keras.layers.Input([48, ]))
mlp.add(tf.keras.layers.BatchNormalization())
mlp.add(tf.keras.layers.Dense(400, activation="sigmoid"))
mlp.add(tf.keras.layers.BatchNormalization())
mlp.add(tf.keras.layers.Dropout(0.4))
mlp.add(tf.keras.layers.Dense(400, activation="sigmoid"))
mlp.add(tf.keras.layers.Dense(1))

adam = tf.keras.optimizers.Adam(learning_rate=1e-4)
scheduler = tf.keras.callbacks.ReduceLROnPlateau()
es = tf.keras.callbacks.EarlyStopping(patience=4, restore_best_weights=True)
mlp.compile(loss=["mse"], metrics=["mse"], optimizer=adam)
mlp.summary()


def init_layer(layer):  # re-init nn
    session = K.get_session()
    weights_initializer = tf.variables_initializer(layer.weights)
    session.run(weights_initializer)


mlp_rmse = []
for train_index, test_index in kf.split(X_train):
    mlp_X_train, mlp_X_test = X_train.iloc[train_index], X_train.iloc[test_index]
    mlp_y_train, mlp_y_test = y_train.iloc[train_index], y_train.iloc[test_index]
    for layer in mlp.layers:
        init_layer(layer)
    mlp.fit(mlp_X_train, mlp_y_train, validation_data=[
            mlp_X_test, mlp_y_test], epochs=100, callbacks=[es, scheduler])
    mlp_rmse.append(np.sqrt(mean_squared_error(
        mlp.predict(mlp_X_test), mlp_y_test)))

print("MLP, 5fold RMSE ", np.mean(mlp_rmse))


# %%

lr.fit(X_train, y_train)

# %%recommendation
pred_rating = lr.predict(teleplay)
pred_rating = pd.DataFrame(pred_rating)

print(mean_squared_error(lr.predict(teleplay), teleplay.rating))
# %%
id_ = teleplay.teleplay_id.astype("int")
pred = pd.DataFrame(np.column_stack((id_, pred_rating)))
pred[0] = pred[0].astype("int")
pred.rename(columns={0: "Teleplay_id",
                     1: "Predicted rating"}, inplace=True)
# %%
pred.to_csv("19079662D_task2.csv", index=False)
# %%
