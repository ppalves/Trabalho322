import pandas as pd
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA

zD = pd.read_csv("zombieData/zombie-health-cases500.csv")

toNum = lambda b: 0 if b=="f" else 1

features = zD.columns[:-1]
labels = zD.columns[-1]

types = zD[labels].unique()

#labels to nums
for i in range(len(zD[labels])):
    c = zD[labels][i]
    if c==types[0]:
        zD[labels][i]=0
    elif c==types[1]:
        zD[labels][i]=1
    elif c==types[2]:
        zD[labels][i]=2
    elif c==types[3]:
        zD[labels][i]=3


zDU =zD[features].applymap(toNum) 

X = zDU[features]
Y = zD[labels]

X_reduced = PCA(n_components=2).fit_transform(X)

#plt.scatter(X.ix[:,features[1]], X.ix[:,features[2]])
plt.scatter(X_reduced[:,0], X_reduced[:,1], c=Y, cmap=plt.cm.Set2)
plt.show()
