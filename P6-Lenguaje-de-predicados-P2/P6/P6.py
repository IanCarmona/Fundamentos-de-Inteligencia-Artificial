from tkinter import *
import os
from pyswip import Prolog
from PIL import Image,ImageTk
from json import *

def center_window(window):
    window.update_idletasks()
    width = window.winfo_width()
    height = window.winfo_height()
    x = (window.winfo_screenwidth() // 2) - (width // 2)
    y = (window.winfo_screenheight() // 2) - (height // 2)
    window.geometry('{}x{}+{}+{}'.format(width, height, x, y))


def consulta():
    global eleccion,root
    if eleccion.get()==1:
        
        consult_specie()
    if eleccion.get()==2:
        list_species()

def consult_specie():
    global especieInput, textResult, labelImg, image2
    
    prolog = Prolog()
    prolog.consult('./animalia.pl')
    
    specie = especieInput.get()

    with open("input.txt", "w") as file:
        file.write(f"about({specie}).")

    os.system("swipl animalia.pl < input.txt > output.txt")

    with open("output.txt", "r") as file:
        content = file.read()
    
    textResult.delete("1.0", "end")
    textResult.insert(END, content)

    for line in content.splitlines():
        if "imagen" in line:
            src = line.split("(")[1].split(")")[0]

            image2 = Image.open(f"img/{src}")
            image2 = image2.resize((100, 100), Image.Resampling.LANCZOS)
            img_tk = ImageTk.PhotoImage(image2)

            labelImg2 = Label(frame, image=img_tk)
            labelImg2.place(x=40, y=20)
            labelImg2.image = img_tk




import json

def list_species():
    global textResult
    
    animals = [
        "puma_concolor",
        "panthera_pardus",
        "leopardus_geoffroyi",
        "elephas_maximus",
        "equus_caballus",
        "ceratotherium_simum",
        "ursus_arctos",
        "ursus_maritimus",
        "ailuropoda_melanoleuca",
        "noctilio_albiventris"
    ]
    
    textResult.delete("1.0", "end")
    formatted_animals = json.dumps(animals, indent=4)
    textResult.insert(END, formatted_animals)
    
    

root = Tk()
root.resizable(False,False)
frame = Frame(root)
frame.pack(fill="both",expand=True)
frame.config(width=450,height=350, bg='blue' )
center_window(root);

textResult = Text(width=30,height=20,bg='lightgreen')
textResult.place(x=200,y=10)

image=Image.open("akinator.jpg")
image=image.resize((100,100),Image.Resampling.LANCZOS)
img=ImageTk.PhotoImage(image)
lbl_img=Label(frame,image=img)
lbl_img.place(x=40,y=20)

especieInput = Entry(width=21,bg='lightgreen')
especieInput.place(x=40,y=180)

specieLabel = Label(text="Que especie desea buscar:", bg='blue', fg='white', font=("Trebuchet MS", 10))
specieLabel.place(x=20,y=150)

eleccion = IntVar()
btnSpecie = Radiobutton(text="Conocer Especie",width=15,bg='lightgreen',font=("Maiandra GD",10),value=1,variable=eleccion)
btnSpecie.place(x=40,y=210)

btnListAll = Radiobutton(text="Mostrar Especies",width=15,bg='lightgreen',font=("Maiandra GD",10),value=2,variable=eleccion)
btnListAll.place(x=40,y=240)

consultar = Button(text="Consultar",width=17,bg='lightgreen',command=consulta)
consultar.place(x=40,y=270)

root.mainloop()