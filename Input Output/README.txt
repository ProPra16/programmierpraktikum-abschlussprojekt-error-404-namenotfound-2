JavaToEditor: Constructor bekommt den namen der Datei die er lesen soll. 
		Ich empfehle hier zu beginn des Programms f�r jedes Datei einen Reader
		zu erzeugen.

		.read(): Liest die Datei ein und gibt sie als String zur�ck. Bereits formatiert.

EditorToJava: Constructor �hnlich wie JavaToEditor.

		.write(String): Bekommt einen String, also Textfeld.getText() und 
			schreibt es in die Datei.

Wenn ihr die Klassen benutzen wollt m�sst ihr eventuell jeweils bei write und read
den Pfad anpassen. Bei mir war er nur "src/" aber das ist ja �berall anders. 
Also nicht den ganzen Pfad, nur von der Aufrufdatei aus gesehen, ich denke ihr wisst
was ich meine :_)