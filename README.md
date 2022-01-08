# Project1
Proiectul la POO
etapa 1 

Am facut o baza de date in care citesc tot. Implementarea ei este singleton lazy,
in felul acesta, o pot apela si modifica de oriunde din proiect.

Am folosit visitor pattern pentru a sepeara implementarea funtiilor care calculeaza 
average scorul de clasele corespunzatoare. Astfel clasa Children e mostenita de 
Kid, Teen si Baby. Iar SantasCalculator e o clasa ce implementeaza interfata 
de vizitare.

In inputloader am citit datele si le-am stocat in baza de date astfel:
- copii initiali
- cadourile initiale
- bugetul initial
- numarul de ani pe care lucram
- o lista cu schimbarile de la an la an

Apoi am creat clasa Operator pentru a implementa toate metodele necesare pentru desfasurarea
unei asa zise "runde";
Pentru fiecare an am luat datele sale si le-am adaugat la baza de date

Mai am doua clase speciale de print pe care le-am folosit pentru a obtine formatul cerut in
enuntul problemei, ambele continand doar cate o lista, una de Children si una de AnnualChildren.
Elementele annualChildren au abilitatea de a stoca o lista de copii (Children) la un moment dat 
in timp. Astfel, chiar daca baza de date se modifica, lista ce trebuie trimisa catre output ramane
aceeasi. Functioneaza ca un fel de clipboard dinamic.

Am avut nevoie de aceasta implementare deoarece scrierea fisierelor json este immutable, deci nu 
putem adauga pe parcurs date.

Feedback: Mi-a placut tema asta. M-am cam enervat pentru ca nu am gasit o metoda de a 
utiliza un object mapper si m-am chinuit destul de mult fara succes. In final am decis
sa fac citirea dupa exemplul primit in tema 1. Dupa partea de citire, rezolvarea a fost
destul de fun. Enuntul a fost foarte bine redactat si foarte explicit! :).