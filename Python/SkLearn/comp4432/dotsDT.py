# %%
from sklearn.preprocessing import LabelEncoder, OneHotEncoder
from six import StringIO
from sklearn import tree
import pandas as pd
import numpy as np
import pydotplus
import os

if __name__ == '__main__':
    os.environ["PATH"] += os.pathsep + \
        "C:/Program Files/graphviz-2.44.1-win32/Graphviz/bin/"
    dots = np.loadtxt("dots.csv", skiprows=1, delimiter=",")

    clf = tree.DecisionTreeClassifier(
        criterion="entropy")  # 创建DecisionTreeClassifier()类
    clf = clf.fit(dots[:, :2], dots[:, 2])  # 使用数据，构建决策树

    dot_data = StringIO()
    tree.export_graphviz(clf, out_file=dot_data,  # 绘制决策树
                         filled=True, rounded=True,
                         special_characters=True)
    graph = pydotplus.graph_from_dot_data(dot_data.getvalue())
    graph.write_pdf("tree.pdf")  # 保存绘制好的决策树，以PDF的形式存储。
