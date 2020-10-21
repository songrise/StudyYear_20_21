# %%
from sklearn.preprocessing import LabelEncoder, OneHotEncoder
from six import StringIO
from sklearn import tree
import pandas as pd
import numpy as np
import pydotplus
import os
# %%
if __name__ == '__main__':
    os.environ["PATH"] += os.pathsep + \
        "C:/Program Files/graphviz-2.44.1-win32/Graphviz/bin/"
    dots = np.loadtxt("octopus.csv", skiprows=1, delimiter=" ")

    clf = tree.DecisionTreeClassifier(
        criterion="entropy")  # 创建DecisionTreeClassifier()类
    clf = clf.fit(dots[:, 1:4], dots[:, 4])  # 使用数据，构建决策树

    dot_data = StringIO()
    tree.export_graphviz(clf, out_file=dot_data,  # 绘制决策树
                         filled=True, rounded=True,
                         special_characters=True)
    graph = pydotplus.graph_from_dot_data(dot_data.getvalue())
    graph.write_pdf("Octopus_tree.pdf")  # 保存绘制好的决策树，以PDF的形式存储。


# %%
# mon-thur 0 fri 1
# hk 0 Kln 1 NT 2
# same
# trans MTR 0 Bus1
