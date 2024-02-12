import pandas as pd
import matplotlib.pyplot as plot
import sklearn.cluster as skl

#Adquiere los datos desde un archivo csv usando biblioteca PANDAS
dataframe = pd.read_csv('datos-procesadores.csv', encoding="ISO-8859-1")
x = dataframe['frecuencia']
y = dataframe['precio']

#Tomamos únicamente las características x,y para el set de entrenamiento
X = pd.DataFrame()
X['x'] = x
X['y'] = y


#Calcular inercias

inertias = []
K = range(1,10)
for k in K:
    kmeansModel = skl.KMeans(n_clusters=k)
    #Se entrena con k clusters y los datos de X
    kmeansModel.fit(X)
    inertias.append(kmeansModel.inertia_)

#Grafica de codo
plot.figure(figsize=(16,8))
plot.plot(K, inertias, 'bx-')
plot.xlabel('k')
plot.ylabel('Inercia')
plot.title('Método del codo para mostrar el valor óptimo de K')
plot.show()

