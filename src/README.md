nom 
SY Alassane 21956987 grp2
DOROSENCO Nadejda 21955552 grp5 

Pour lancer en terminal ou en interface graphique un choix est demandé dans le terminal au lancement

pour lancer le projet sur windows, faire avec le cmd dans le fichier src :
javac -classpath "commons-math3-3.6.1.jar;." Controller.java 
puis 
java -classpath "commons-math3-3.6.1.jar;." Controller


Nous pensons que les points forts sont le Builder, les fonctions zoom et se déplacer 
Nous avons ajouté des threads, malheureusement nous n'avons pas observé de gain de performance significatif
 donc nous avons laissé par défaut le nombre de threads comme celui étant considéré optimal.
 
Il y a quelques éléments que nous aurions pu rajouter si nous avions mieux géré notre temps, et nous avons laissé quelques todos pour le montrer : 
des tests sur linux;
un thread séparé pour lancer le calcul en interface graphique, avec un bouton stop;
les ensembles de Mandelbrot;
une ou des classes Vue plus lisibles;
un enum de fractales remarquables;
un choix en interface graphique du style de couleurs.
une interface graphique plus jolie avec JavaFX;

Pour les polynômes nous avions une implémentation
 que nous avons décidé de ne pas garder car elle impliquait une boucle et donc abimait beaucoup les performances meme pour des calculs simples.


Une image apparait dans le dossier src si vous utiliser la vue terminal ou le bouton sauvegarder dans la vue graphique;
Il y a egalement un graphe des classes

source pour les complexes : 
Complex (Apache Commons Math 3.6 API)
https://commons.apache.org › math3

