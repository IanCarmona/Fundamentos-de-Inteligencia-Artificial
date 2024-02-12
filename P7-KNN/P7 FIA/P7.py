import numpy as np
import matplotlib.pyplot as plot
import pandas as pd
from sklearn.neighbors import KNeighborsClassifier

# Recupera datos
df = pd.read_csv("datos-estaturas.csv")

# Especifica los datos y sus etiquetas
X = df[["Estatura", "Peso"]]
y = df["Clase"]

# Para visualizar gráfica de dispersión
ax = plot.axes()
ax.scatter(
    df.loc[df["Clase"] == "Hombre", "Estatura"],
    df.loc[df["Clase"] == "Hombre", "Peso"],
    c="blue",
    label="Hombre",
)
ax.scatter(
    df.loc[df["Clase"] == "Mujer", "Estatura"],
    df.loc[df["Clase"] == "Mujer", "Peso"],
    c="pink",
    label="Mujer",
)

plot.xlabel("Height x Gender")
plot.ylabel("Weight")
ax.legend()

# Calcular k
k = int(np.sqrt(X.shape[0]))

if k % 2 == 0:  # Hacer que K sea impar para evitar empates
    k += 1

print(k)
knn = KNeighborsClassifier(n_neighbors=k)

knn.fit(X, y)

# Definir colores y marcadores para las pruebas
colores = [
    "red",
    "green",
    "blue",
    "orange",
    "yellow",
    "cyan",
    "magenta",
    "brown",
    "gray",
    "black",
]
marcadores = ["o", "s", "v", "^", "D", "*", "x", "p", "H", "+"]

# Realizar 10 pruebas
for i in range(10):
    print(f"\nPrueba {i + 1}")
    estatura = float(input("Indica la estatura en metros: "))
    peso = float(input("Indica el peso en kilos: "))

    dfp = pd.DataFrame()
    dfp["Estatura"] = [estatura]
    dfp["Peso"] = [peso]

    ax.scatter(dfp["Estatura"], dfp["Peso"], c=colores[i], marker="o")

    prediccion = knn.predict(dfp)

    ax.text(
        dfp["Estatura"][0],
        dfp["Peso"][0],
        str(i + 1),
        fontsize=9,
        color="black",
        ha="center",
    )

    print("Con los datos:")
    print(dfp)
    print("La categoría predicha es:")
    print(prediccion)


# Muestra gráfica de dispersión con distintivos de las pruebas
ax.legend()
plot.show()
