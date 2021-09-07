# %%
from sklearn.metrics import mean_squared_error
from sklearn.model_selection import train_test_split
from sklearn.datasets import make_regression
from sklearn.preprocessing import PolynomialFeatures
import numpy as np
import matplotlib.pyplot as plt


class MyLinearRegression:
    def __init__(self, fit_intercept=True, poly_degree=1, regularizer=None, lambda_val=1, momentum=0.01):
        self.w = None
        self.loss = []
        self.poly_degree = poly_degree
        self.fit_intercept = fit_intercept
        self.epsilon = 1e-9  # avoid underflow
        self.regularizer = regularizer
        self.lambda_val = lambda_val
        self.momentum = momentum
        # self.grad_clipper = np.tanh
        self.grad_clipper = lambda x: x

    def fit(self, X, y, eta=1e-4, n_iter=1000, initial_w=None,):
        X_cpy = X.copy()

        if(self.fit_intercept):
            bias = np.ones(X.shape[0]).T
            X_cpy = np.column_stack((X_cpy, bias))

        if(self.poly_degree > 1):
            poly = PolynomialFeatures(
                degree=self.poly_degree, include_bias=False)
            X_cpy = poly.fit_transform(X_cpy)

        X_cpy = np.mat(X_cpy)
        y = np.mat(y).T

        m, n = X_cpy.shape  # m is the number of data point, n is dimension
        if initial_w is None:
            # if initial weight unspecified, use all 0s.
            self.w = np.mat(np.zeros(n)).T
        else:
            self.w = initial_w
        # to deal with vanishing of gradients
        self.velocity = np.zeros_like(self.w)

        for i in range(n_iter):
            prediction = X_cpy*self.w

            if self.regularizer == None:
                # J_theta = np.sum(np.square(prediction-y))/(2*m)
                # self.loss.append(J_theta)
                # error = prediction-y
                # grad = (eta*X.T*error)/m
                # self.velocity = momentum*self.velocity - grad
                # self.w = self.w + self.velocity
                # try to use closed-form solution
                XTX = X_cpy.T*X_cpy
                try:
                    XTX_I = XTX.I
                except np.linalg.LinAlgError:
                    # pseudo inversion, also speed up computation
                    XTX_I = np.linalg.pinv(XTX)
                self.w = XTX_I * X_cpy.T*y
                break

            elif self.regularizer == 'l1':
                # J_theta = np.sum(np.square(prediction-y)) / \
                #     (2*m) + self.lambda_val*np.sum(np.abs(self.w))/(2*m)
                # self.loss.append(J_theta)
                error = prediction-y
                sig = np.sign(self.w)

                grad = self.grad_clipper(
                    (eta*X_cpy.T*error)/m + (self.lambda_val*eta/m)*sig)  # apply clipping to avoid overflow
                self.velocity = self.momentum*self.velocity - grad
                self.w = self.w + self.velocity

                self.w = self.w + self.velocity

            elif self.regularizer == 'l2':
                # J_theta = np.sum(np.square(prediction-y))/(2*m) + \
                #     self.lambda_val*np.sum(np.square(self.w))/(2*m)  # l2 norm
                # self.loss.append(J_theta)
                error = prediction-y

                grad = (eta*X_cpy.T*error)/m + (self.lambda_val*eta/m)*self.w
                self.velocity = self.momentum*self.velocity - grad
                self.w = self.w + self.velocity

            else:
                raise Exception

    def predict(self, X):
        X_cpy = X.copy()
        X_cpy = np.mat(X_cpy)

        if(self.fit_intercept):
            bias = np.ones(X_cpy.shape[0]).T
            X_cpy = np.column_stack((X_cpy, bias))

        if(self.poly_degree > 1):
            poly = PolynomialFeatures(
                degree=self.poly_degree, include_bias=False)
            X_cpy = poly.fit_transform(X_cpy)

        return X_cpy*self.w


# %%
if __name__ == "__main__":
    # the perfromance is dependent on random seeds
    # somehow the random_state param does not have effect...
    X, y = make_regression(n_samples=10, n_features=1,
                           n_targets=1, random_state=1,  noise=1, bias=0)
    X_train, X_val, y_train, y_val = train_test_split(
        X, y, test_size=0.2)
    lr = MyLinearRegression(regularizer='l2', lambda_val=1e-3)
    lr.fit(X_train, y_train)
    print(mean_squared_error(lr.predict(X_val), y_val))

    lr = MyLinearRegression(regularizer='l1', lambda_val=1e-3)
    lr.fit(X_train, y_train)
    print(mean_squared_error(lr.predict(X_val), y_val))

    lr = MyLinearRegression()
    lr.fit(X_train, y_train)
    print(mean_squared_error(lr.predict(X_val), y_val))

# %%
