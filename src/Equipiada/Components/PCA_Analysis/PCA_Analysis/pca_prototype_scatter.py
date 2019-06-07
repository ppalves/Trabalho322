import sys
import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from sklearn.decomposition import PCA

#create Pandas DataFrame
dataset = sys.argv[1]
zD = pd.read_csv("zombieData/"+dataset)

#features to nums
toNum = lambda b: 0 if b=="f" else 1

features = zD.columns[:-1]
labels = zD.columns[-1]

#checks sintomn datatype (int or str)
types = zD[labels].unique()
if (zD.dtypes.iloc[0]=="int64"):
    X = zD[features]
else:
    zDU = zD[features].applymap(toNum) 
    X = zDU[features]

Y = zD[labels]

#labels to nums
for i in range(len(Y)):
    for j in range(len(types)):
        if  Y[i]== types[j]:
             Y.at[i]=j

#PCA
X_reduced = PCA(n_components=2).fit_transform(X)

#creates dictionaries for legend purposes
colors = plt.cm.Set2.colors
diseases = Y.unique()
colorDict = {diseases[i]:colors[i] for i in range(len(diseases))}
diseasesDict = {diseases[i]:types[i] for i in range(len(diseases))}

#preparing the plot
fig, ax = plt.subplots()
plt.xlabel('Primeiro Componente Principal')
plt.ylabel('Segundo Componente Principal')
plt.title('Analise dos Componentes Principais')

#scatter-plotting for each disease
for i in diseases:
    ix = Y[Y==i].index
    ax.scatter(X_reduced[ix,0], X_reduced[ix,1], c=[colorDict[i]], label = diseasesDict[i])
ax.legend()

#plt.show()
name = (dataset.split("."))[0]
fig.set_size_inches(10.5, 6.5)
fig.savefig(name+'.jpeg')

