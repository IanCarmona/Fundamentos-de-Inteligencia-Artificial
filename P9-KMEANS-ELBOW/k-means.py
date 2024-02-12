import pandas as pd
import matplotlib.pyplot as plt
import sklearn.cluster as skl
from mpl_toolkits.mplot3d import Axes3D
import numpy as np

# Adquiere los datos desde un archivo csv usando la biblioteca Pandas
dataframe = pd.read_csv('datos_paises.csv', encoding='ISO-8859-1')

# Reemplaza las comas por puntos en la columna 'PIB Per Cápita (USD)'
dataframe['PIB Per Cápita (USD)'] = dataframe['PIB Per Cápita (USD)'].str.replace(',', '.').astype(float)

x = dataframe['Esperanza de Vida al Nacer']
y = dataframe['PIB Per Cápita (USD)']
z = dataframe['Emisiones de CO2 per Cápita (toneladas)']

# Tomamos únicamente las características x, y, z para el set de entrenamiento
X = pd.DataFrame()
X['x'] = x
X['y'] = y
X['z'] = z

# Solicita al usuario el número de Clusters - Parámetro K
k = int(input("Ingrese el numero de clusters (k) que quiera ver: "))

# Inicialización para el algoritmo de clustering con el parámetro K indicado
kmeansModel = skl.KMeans(n_clusters=k)

# Carga los datos
kmeansModel.fit(X)

# Restaura los valores originales de la columna 'PIB Per Cápita (USD)' en el dataframe
dataframe['PIB Per Cápita (USD)'] = dataframe['PIB Per Cápita (USD)'].astype(str).str.replace('.', ',')

# Gráfico 3D
fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

# Puntos de datos
ax.scatter(x, y, z, c='black', marker='o')  # Todos los marcadores serán negros

# Colores para los grupos
colors = ['blue', 'red', 'green', 'purple', 'brown']

# Asignar colores a los puntos de datos según los grupos
for i in range(k):
    group_indices = np.where(kmeansModel.labels_ == i)[0]
    ax.scatter(x[group_indices], y[group_indices], z[group_indices], c=colors[i], marker='o')

# Centroides
centroids = kmeansModel.cluster_centers_
ax.scatter(centroids[:, 0], centroids[:, 1], centroids[:, 2], marker='x', s=200, linewidths=3, color='black')

# Etiquetas de los ejes
ax.set_xlabel('Esperanza de Vida al Nacer')
ax.set_ylabel('PIB Per Cápita (USD)')
ax.set_zlabel('Emisiones de CO2 per Cápita (toneladas)')

plt.show()
