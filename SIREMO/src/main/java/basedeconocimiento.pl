
esSolucion(1,1).
esSolucion(1,2).
esSolucion(1,3).

esSolucion(2,1).
esSolucion(2,2).
esSolucion(2,3).

esSolucion(3,1).
esSolucion(3,2).
esSolucion(3,3).

esSolucion(4,1).
esSolucion(4,2).
esSolucion(4,3).

esSolucion(5,1).
esSolucion(5,2).
esSolucion(5,3).

esSolucion(6,1).
esSolucion(6,2).
esSolucion(6,3).

esSolucion(7,1).
esSolucion(7,2).
esSolucion(7,3).

esSolucion(8,1).
esSolucion(8,2).
esSolucion(8,3).

esSolucion(9,1).
esSolucion(9,2).
esSolucion(9,3).

esSolucion(10,1).
esSolucion(10,2).
esSolucion(10,3).

esSolucion(11,1).
esSolucion(11,2).
esSolucion(11,3).

esSolucion(12,1).
esSolucion(12,2).
esSolucion(12,3).

esSolucion(13,1).
esSolucion(13,2).
esSolucion(13,3).



%contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=1, R=1, B1 is Re+1, B2 is X+1, B3 is Y+1, RU=U, RV=V, RW=W, RX=X, RY=Y,RZ=Z. 


contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=1, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=1, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=1, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=2, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=2, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu. 
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=2, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=3, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=3, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu. 
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=3, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3.  

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=4, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=4, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu. 
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=4, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=5, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=5, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=5, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=6, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=6, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=6, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3.  

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=7, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=7, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=7, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3.  

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=8, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=8, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=8, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=9, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=9, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=9, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=10, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=10, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=10, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=11, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=11, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu. 
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=11, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3. 

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=12, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=12, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu. 
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=12, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3.  

contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=13, R=1, B1 is Re+1, AuxRe=B1, AuxVa=Va, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=13, R=2, B2 is Va+1, AuxRe=Re, AuxVa=B2, AuxPu=Pu.
contadorVotos(P, R, Re, Va, Pu, AuxRe, AuxVa, AuxPu):- esSolucion(P,R), P=13, R=3, B3 is Pu+1, AuxRe=Re, AuxVa=Va, AuxPu=B3.  


estilo(Re,Va,Pu,C,D) :- Re >= Va , Va >= Pu, rocker(C,D) . 
estilo(Re,Va,Pu,C,D) :- Re >= Pu , Pu >= Va,  hipster(C,D) . 

estilo(Re,Va,Pu,C,D) :- Va >= Pu , Pu >= Re, trendy(C,D) . 
estilo(Re,Va,Pu,C,D) :- Va >= Re , Re >= Pu, streetWear(C,D) . 

estilo(Re,Va,Pu,C,D) :- Pu >= Re , Re >= Va, casual(C,D) . 
estilo(Re,Va,Pu,C,D) :- Pu >= Va , Va >= Re, elegante(C,D) . 


rocker(C,D):- C='Colérico' ,
D='AtRevido para empRender nuevas taReas.
Tiende a los arRebatos o estallidos emocionales.
Es extrovertido y tiene facilidad para las Relaciones sociales.
Tiende a abandonar los objetivos cuando surgen las mayoRes dificultades.
Puede ser pResa de la dispersión al hacer varias taReas y no concentrarse solo en una.
Tendencia a una baja disciplina.
Pueden ser eficientes en la improvisación.
Desea Respuestas inmediatas y no quieRe proyectos con frutos a largo plazo.
Es compasivo, generoso y servicial.
Su sentido práctico lo libera de guardar RencoRes.
Decide por sí mismo.
A veces no es consciente de sus limitaciones.
Pone su mejor empeño en sus actividades, pero como tiende a la inconstancia, es adecuado que trabaje en objetivos de corto plazo.'.


hipster(C,D):- C='Apasionado' , 
D='Una gran disposición al sacrificio.
Posee una visión de largo plazo y está dispuesto a pagar el pRecio para obtener el exito que quieRe.
Compasivo, compRensivo, servicial.
Posee el don de mando y puede desempeñarse con éxito en puestos que exijan un buen liderazgo.
En su afán por lograr sus propósitos puede arrastrar a otras personas a conflictos o no considerar las emociones de los demás.
Tienden a ser Reservados en su vida amorosa.
Gran capacidad para el trabajo, organizado y un gran sentido de Responsabilidad.
Capacidad de razonamiento abstracto y lógico.
PRefeRencia por trabajar solo.
Facilidad al autodominio y la templanza para no dejarse llevar por los placeRes de los sentidos.
Pueden ser demasiado orgullosos.
Poca docilidad.'.

trendy(C,D):- C='Nervioso' , 
D='Personas muy sensibles que tienen el deseo de vivir con mucha intensidad.
Pueden cambiar de humor con facilidad.
QuieRe buenos Resultados en poco tiempo y que no impliquen grandes esfuerzos.
Tendiente a la peReza, falta de atención y poca constancia en las actividades que Realiza.
Le Resulta fácil establecer buenas Relaciones sociales.
Se les complica un poco el uso del razonamiento lógico, el pensamiento abstracto y el uso de la memoria.
Gran facilidad para la literatura, la poesía y las artes, excelente capacidad de imaginación.
Marcada tendencia a la diversión y disfrutar el momento.
Tiende al amor superficial.
Le huye a la disciplina, el trabajo duro y agendas apRetadas.
Puede distraerse y aburrirse con mucha facilidad.
Normalmente cambia de ocupaciones.
Su poder de voluntad es bajo.'.

streetWear(C,D):- C='Sentimental' , 
D='Personas introvertidas y con pocas habilidades sociales.
Son personas muy sensibles y cuando se les hace algún tipo de Reclamo, pueden sentirse tRemendamente afectadas y les costará superar ese episodio.
Les Resulta difícil adaptarse a los cambios, porque poseen un gran espíritu conservador.
No tienen facilidades para empRender, los análisis lógicos y el pensamiento abstracto, les Resulta difícil.
Ponen interés en las taReas que Realizan y se esfuerzan en hacerlas bien.
Cuando ocurRen las dificultades se desmotiva con facilidad.
No confía en sus capacidades y actúa con lentitud.
Tendencia a estar anclado en experiencias del pasado.
Busca la soledad y los ambientes tranquilos.
Personas nobles y con gran sentido de empatía.
Inclinado a los procesos de introspección y la vida interior.
Tienden al pesimismo y los RencoRes.'.

casual(C,D):- C='Sanguíneo' , 
D='Personas muy extrovertidas.
Alto sentido práctico.
Tendencia al egoísmo y la codicia.
Puede usar las ironías, el cinismo, la crítica dura.
Poca sensibilidad y tendencia al libertinaje.
Facilidad para la compRensión y razonamientos objetivos.
Deseo de obtener dinero, en base a un trabajo inteligente que brinde rápidos Resultados.
Poca disposición para la vida espiritual.
Posee una gran confianza en sí mismo.
Pueden caer en la enfermedad de mentir para conseguir sus objetivos.
Persona optimista.
Piensa las cosas fríamente.'.

elegante(C,D):- C='Flemático' , 
D='Gran frialdad y marcada tendencia a mantener la calma.
Constancia para Realizar sus proyectos personales.
Personas poco expResivas.
Sentido de libertad y autonomía.
Se adaptan con facilidad a difeRentes ambientes.
Demuestran una gran templanza.
Evita las participaciones en grupo y  son personas bastante Reservadas.
Poseen un gran orgullo.
Son gente ordenada y metódica.
Tienen una inteligencia bastante profunda.
Les cuesta darse a los demás.
Encerrado en sus propios ideales.'.
