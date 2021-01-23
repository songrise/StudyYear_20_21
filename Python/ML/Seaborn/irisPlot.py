# %%
import seaborn as sns
import pandas as pd
iris = pd.read_csv("Iris.csv")
iris.info()

# %%
g = sns.pairplot(data=iris, hue="PetalWidthCm", )

# %%
