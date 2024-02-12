import numpy as np
import matplotlib.pyplot as plot
import pandas as pd
from sklearn.neighbors import KNeighborsClassifier

# Recupera dator
df = pd.read_csv("datos-salarios.csv")

# Especifica los datos y sus etiquetas
X = df[["Salario", "Experiencia"]]
y = df["Categoria"]

# Para visualizar gráfica de dispersión
ax = plot.axes()
ax.scatter(
   df.loc[df["Categoria"] == "junior", "Salario"],
   df.loc[df["Categoria"] == "junior", "Experiencia"],
   c="red",
   label="junior",
)
ax.scatter(
   df.loc[df["Categoria"] == "semi senior", "Salario"],
   df.loc[df["Categoria"] == "semi senior", "Experiencia"],
   c="orange",
   label="semi senior",
)
ax.scatter(
   df.loc[df["Categoria"] == "senior", "Salario"],
   df.loc[df["Categoria"] == "senior", "Experiencia"],
   c="green",
   label="senior",
)

plot.xlabel("Salary USD x Month")
plot.ylabel("Experiencie")
ax.legend()

# Calcular k
k = int(np.sqrt(X.shape[0]))

if k % 2 == 0:  # Hacer que K sea impar para evitar empates
   k = k + 1

knn = KNeighborsClassifier(n_neighbors=k)

knn.fit(X, y)

# Probar
print(f"\nProbar algoritmo con {k=}")
salario = float(input("Indique el salario mensual en miles de UD$: "))
experiencia = float(input("Indique la experiencia en años: "))

dfp = pd.DataFrame()
dfp["Salario"] = [salario]
dfp["Experiencia"] = [experiencia]

ax.scatter(dfp["Salario"], dfp["Experiencia"], c="black")

prediccion = knn.predict(dfp)

# Muestra gráfica de dispersión
plot.show()

print("\nCon los datos")
print(dfp)
print("La categoría predicha es:")
print(prediccion)
