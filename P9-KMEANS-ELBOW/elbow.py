import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import sklearn.cluster as skl

# Adquirir los datos desde un archivo csv usando la biblioteca Pandas
dataframe = pd.read_csv('datos_paises.csv', encoding="ISO-8859-1", thousands=',')
x = dataframe['Esperanza de Vida al Nacer']
y = dataframe['PIB Per Cápita (USD)']
z = dataframe['Emisiones de CO2 per Cápita (toneladas)']

# Tomamos únicamente las características x, y, z para el set de entrenamiento
X = pd.DataFrame()
X['x'] = x
X['y'] = y.replace(',', '', regex=True).astype(float)
X['z'] = z

# Calcular inercias
inertias = []
K = range(1, 10)
for k in K:
    kmeansModel = skl.KMeans(n_clusters=k, n_init=10)
    # Se entrena con k clusters y los datos de X
    kmeansModel.fit(X)
    inertias.append(kmeansModel.inertia_)

# Graficar el codo en 2D
plt.plot(K, inertias, 'bx-')
plt.xlabel('k')
plt.ylabel('Inercia')
plt.title('Método del codo para mostrar el valor óptimo de K')
plt.show()
