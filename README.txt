
Claudia Rogoz
321CA

Tema consta in implementarea unui RadixTree. 

In realizarea temei, m-am folosit atat de clasele Index, FileParser (date in schelet), cat si de clasele RadixTree, RadixNode, dar si de MyArrayList.

Clasa RadixNode prezinta campurile specifice fiecarui nod din cadrul lui RadixTree.
Campurile sunt pe rand: String word (cheia - cuvantul/portiunea din cuvant retinut); RadixNode[] kids (copiii fiecarui nod); boolean fullWord (daca word este "finalul" unui cuvant); List<Integer> list (lista indecsilor);

Clasa RadixTree implementeaza arborele efectiv.
In cadrul acestei clase am folosit metoda recursiva insert pentru parsarea cuvintelor in RadixTree, metoda findPrefix pentru gasirea unui prefix dat ca argument in RadixTree, metoda getNodes pentru listarea tuturor indecsilor gasiti de la nivelul unui nod data ca parametru pana la ultimul nivel.

Clasa MyArrayList extinde clasa ArrayList in scopul supraincarcarii metodei toString.

Preluaraea cuvintelor se face prin intermediul clasei Index in care se afla functia main, cuvinte transmise apoi impreuna cu indecsii corespunzatori, in cadrul arborelui.   