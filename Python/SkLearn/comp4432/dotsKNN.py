# %%
from sklearn.preprocessing import LabelEncoder, OneHotEncoder
from six import StringIO
from sklearn import neighbors
from sklearn import tree
import pandas as pd
import numpy as np
import pydotplus
import os


clf = neighbors.KNeighborsClassifier(n_neighbors=3)
clf.