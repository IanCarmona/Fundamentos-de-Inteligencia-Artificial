import pandas as pd
from matplotlib import pyplot as plt
import sklearn.tree as skl
from sklearn.tree import plot_tree
from sklearn.preprocessing import LabelEncoder

# Adquiere los datos desde un archivo csv usando la biblioteca PANDAS
dataframe = pd.read_csv('datos.csv', encoding='ISO-8859-1')

# Preprocesamiento (Transformar valores categóricos a numéricos)
Edad = LabelEncoder()
Sexo = LabelEncoder()
PresionSanguinea = LabelEncoder()
Colesterol = LabelEncoder()
Medicamento = LabelEncoder()

dataframe['Edad'] = Edad.fit_transform(dataframe['Edad'])
dataframe['Sexo'] = Sexo.fit_transform(dataframe['Sexo'])
dataframe['PresionSanguinea'] = PresionSanguinea.fit_transform(dataframe['PresionSanguinea'])
dataframe['Colesterol'] = Colesterol.fit_transform(dataframe['Colesterol'])
dataframe['Medicamento'] = Medicamento.fit_transform(dataframe['Medicamento'])

# Prepara los datos
features_cols = ['Edad', 'Sexo', 'PresionSanguinea', 'Colesterol']
X = dataframe[features_cols]
y = dataframe.Medicamento

# Imprimir la tabla completa de X y y
pd.set_option('display.max_columns', None)  # Muestra todas las columnas
pd.set_option('display.width', None)  # Sin límites de ancho
pd.set_option('display.max_rows', None)  # Muestra todas las filas
df_full = pd.concat([X, y], axis=1)
print(df_full.to_string(index=False))

# Entrenamiento
tree = skl.DecisionTreeClassifier(criterion='gini')
tree.fit(X, y)

# Visualización y guardar imagen
px = 1 / plt.rcParams['figure.dpi']  # Pixel en pulgadas
fig = plt.figure(figsize=(1000 * px, 1000 * px))
_ = plot_tree(tree, feature_names=features_cols, class_names=['No', 'Yes'], filled=True)
fig.savefig('arbol_decision.png', dpi=plt.rcParams['figure.dpi'])  # Guardar imagen

# Probar el Modelo
edad = int(input('Ingresa la edad (Joven - 0), (Mediana-Edad - 1), (Senior - 2): '))
sexo = int(input('Ingrese el sexo (F - 0), (M - 1): '))
presionsanguinea = int(input('Ingrese la presion Sanguinea (Alta - 0), (Baja - 1), (Normal - 2): '))
colesterol = int(input('Ingrese el Colesterol (Alto - 0), (Normal - 1): '))

dfprueba = pd.DataFrame()
dfprueba['Edad'] = [edad]
dfprueba['Sexo'] = [sexo]
dfprueba['PresionSanguinea'] = [presionsanguinea]
dfprueba['Colesterol'] = [colesterol]

prediccion = tree.predict(dfprueba)

print('\n\nResultado de la prueba')
print('**********************')
print('Con los datos')
print(dfprueba)
print('\nSe recomienda:')
if prediccion[0] == 0:
    print('Se recomienda la medicina A')
else:
    print('Se recomienda la medicina B')
print("**********************")
print('Imagen del árbol de decisión generada: arbol_decision.png')
