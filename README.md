# acceso_datos_tarea6

Tarea para AD06.

## Enunciado

Utilizando la base de datos XML, crear la colección ejercicios y en ella sube los documentos `universidad.xml`, `libros.xml` y `librosalmacen.xml`. Los recursos necesarios para la elaboración de los ejercicios se encuentran en el siguiente enlace: [Aquí iría el enlace a los recursos, si lo tienes]

## EJERCICIO 1: XPATH (universidad.xml)

Resuelve las consultas que se plantean y envía el documento de texto con las propias consultas y sus resultados:

* Nombre de la Universidad.
* País de la Universidad.
* Nombres de las Carreras.
* Años de plan de estudio de las carreras.
* Nombres de todos los alumnos.
* Identificadores de todas las carreras.
* Datos de la carrera cuyo id es c1.
* Centro en que se estudia la carrera cuyo id es c0.
* Nombre de las carreras que tengan subdirector.
* Nombre de los alumnos que no estén haciendo proyecto.
* Códigos de las carreras en las que hay algún alumno matriculado.
* Apellidos y Nombre de los alumnos con beca.
* Nombre de las asignaturas de la titulación c04.
* Nombre de las asignaturas de segundo trimestre.
* Nombre de las asignaturas que no tienen 4 créditos teóricos.
* Código de la carrera que estudia el último alumno.
* Código de las asignaturas que estudian mujeres.
* Nombre de los alumnos que matriculados en la asignatura a02.
* Códigos de las carreras que estudian los alumnos matriculados en alguna asignatura.
* Apellidos de todos los alumnos mayores de 20.
* Nombre de la carrera que estudia Víctor Manuel.
* Nombre de las asignaturas que estudia Luisa.
* Primer apellido de los alumnos matriculados en Ingeniería del Software.
* Nombre de las carreras que estudian los alumnos matriculados en la asignatura Tecnología de los Alimentos.
* Nombre de los alumnos matriculados en carreras que no tienen subdirector.
* Nombre de los alumnos matriculados en asignaturas con 0 créditos prácticos o que estudien la carrera de I.T. Informática.
* Nombre de los alumnos que estudian carreras cuyos planes son anteriores a 2002.

## EJERCICIO 2: XQUERY (libros.xml y librosalmacen.xml)

Resuelve las consultas que se plantean y envía el documento de texto con las propias consultas y sus resultados:

* Listar el título de todos los libros.
* Listar año y título de todos los libros, ordenados por el año.
* Listar los libros cuyo precio sea 65.95.
* Listar los libros publicados antes del año 2000.
* Listar año y título de los libros publicados por Addison-Wesley después del año 1992.
* Listar año y título de los libros que tienen más de un autor.
* Listar año y título de los libros que tienen no tienen autor.
* Mostrar los apellidos de los autores que aparecen en el documento, sin repeticiones, ordenados alfabéticamente.
* Por cada libro, listar agrupado en un elemento su título y autores.
* Por cada libro, obtener su título y el número de autores, agrupados en un elemento.
* Una lista ordenada alfabéticamente de categorías de libros comprados.
* Obtener la suma del importe de todos los libros que están pendientes.
* Una lista ordenada de autores que tengan libros pendientes.
* La última línea contendrá una línea que tenga el total de autores.

## EJERCICIO 3: (utiliza eXist en un programa JAVA)

Crea un proyecto JAVA que después de conectarse a la base de datos eXist visualice la información de todos los libros que hay en el fichero `libros.xml`.

Se enviará todo el proyecto comprimido.
