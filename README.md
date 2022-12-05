# Algoritmo de visualización

Se ha utilizado la dependencia de lombok, por lo que es necesario para poder compilar el proyecto tenerlo instalado de forma local https://projectlombok.org/

## Estructuras de datos utilizadas en el algoritmo
  Se han utilizado Set en lugar de List tanto para productos como para tallas porque se ha asumido que los ids propios de estos serían sus primarykey, por tanto al tratarse de elementos no repetidos, los que van ha contener, se ha considerado más correcto utilizar set.
  
## Complejidad temporal del algoritmo
  Debido a que existen dos bucles anidados, si hacemos una aproximación podemos asumir que la complejidad temporal expresada en la notación "O" es O(n^2).
  
  Ahora mismo, no veo la forma de resolver el problema sin al menos dos bucles anidados, así que no veo forma de reducir la complejidad de forma que pueda ser significativa.
