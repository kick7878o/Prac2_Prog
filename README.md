# Prac2_Prog
Practica 2 de programación de la URV (Universitat Rovira y Virgili)

--- Análisis de la cantidad de agua en las presas ---
- OBJETIVO:
  Diseñar una app que permita hacer consultas sobre estos datos.

  
- FICHERO '.csv' CON DATOS:
  1. La primera línea es la cabecera contiene la estructura general de los datos que son:
       Dia,Nombre Presa (Poblacion),Provincia,Nivel absoluto (msnm),Porcentaje Volumen ocupado (%),Volumen ocupado (hm3)
     
  2. El resto son los datos separados por comas ',' y, junto al nombre de la presa, entre paréntesis, tenemos la población en que está.

 
- ESPECIFICACIONES DEL PROGRAMA:
  1. Se nos proporciona un proyecto inicial, con la clase 'NivellAigua' (NO modificar excepto añadir los JavaDocs) y una primera versión del main.

 
  2. Crear una nueva clase llamada 'LlistaNivellsAigua' para guardar en una lista de instancias de la clase 'NivellAigua'.
             ¡¡ NO SE PUEDE USAR LAS ESTRUCTURAS PREDEFINIDAS DE JAVA !!


  3. 'LlistaNivellsAigua' ha de contener:
     3a. Básico:
       3a1. Constructor.
       3a2. Getter del número de elementos que tiene la lista.
       3a3. Consultar medida de una posición concreta.
       3a4. Copia/duplica es lo mismo.
       3a5. toString(): es mostrar toda la información que tiene la lista.

     3b. Métodos:
       3b1. Añadir datos de una nueva medida al final de la lista.
       3b2. Dado el nombre de la presa, devolver la primera medida que se encuentra. NULL si no se encuentra.
       3b3. Devolver un duplicado de la instancia con un porcentaje de volumen más alto. Si hay empate, elegir una de las dos.
       3b4. Lo mismo que '3b3' pero con el porcentaje de volumen más bajo.
       3b5. Devolver una tabla de String con el nombre de la presa de las cuales tenemos datos.
       3b6. Devolver un duplicado de la instancia con el volumen de agua más alto. Si hay empate, elegir una.
       3b7. Consultar los datos de las presas de una provincia especifica. Se devuelve en una nueva 'LlistaNivellsAigua'.
       3b8. Consultar los datos de medidas en un período de tiempo. Se recibe la franja de fechas por parametro. Se devuelve en una nueva 'LlistaNivellsAigua'.
       3b9. Eliminar el conjunto de medidas de una presa. Se recibe el nombre de la presa. NO PUEDEN QUEDAR POSICIONES VACÍAS A LA TABLA

     3c. Posibles preguntas:
       3c1. ¿'3b5' a qué se refiere?
         3c11. Opcion 1: Dado el nombre de la presa, ¿devolver un string con los datos que tenga?
         3c12. Opcion 2: Dado el nombre de la presa, ¿devolver un string SÓLO si no tiene en ningun dato 'NULL'?

       3c2. ¿'3b9' a qué se refiere?
         3c21. Opción 1: Dado el nombre de la presa, ¿eliminar todas las posiciones que contenga ese nombre?


    4. Programa principal:
       4a. Versión 1:
         4a1. Permite leer un nombre de líneas determinado.

       4b. Versión final:
         4b1. Pedir el número de líneas que se quieran cargar.
         4b2. Llama al método para cargar la tabla con los 'Strings' de las líneas.
         4b3. Procesar y separar la información de cada línea.
         4b3. Ha de mostrar un menú con estas opciones:
             4b31. Mostrar el conjunto de medidas de la lista (sin restricciones).
             4b32. Mostrar conjunto de medidas dentro de una franja de fechas. Nombre província y fechas dadas por teclado.
             4b33. Consultar datos de la primera medida con el nombre de una presa especifica dada por teclado.
             4b34. Consultar datos con un porcentaje de volumen más alto de las presas de cada província. Muestra la info y comprueba que sean del mismo año. ??¿?¿?¿?¿?¿
             4b35. Consultar datos con un porcentaje de volumen más bajo de todas las medidas de la lista.
             4b36. Consultar de cuántas presas tenemos datos de una cierta província dada por teclado. Mostrar el nombre de la presa.
             4b37. Calcular en cuál de las dos províncias se ha encontrado en dicho año con un volumen de agua más alto. Año y 2 provincias dadas por teclado.
             4b38. Consultar datos de medida en una franja de tiempo dadas por teclado.
             4b39. Eliminar un conjunto de medidas de presas de una província dado por teclado.
                 4b391. Orden para eliminar: Mostrar datos de la provincia > llamamos a la operación de eliminar > mostrar datos de la provincia (debe de estar vacío).
             4b3(10). Salir del programa.


    5. Formato de entrega:
       5a. Fichero comprimido llamado: nom1Cognom1_nom2Cognom2_prac2.zip


NOTA OBTENIDA: XX/10
