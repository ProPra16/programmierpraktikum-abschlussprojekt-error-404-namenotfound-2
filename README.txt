Benutzung:
Starte Presetdeliverer.main(args)

Sobald die main gestartet wurde werden die Settings aus der
Datei presettings.txt gelesen, diese befindet sich auf der gleichen Verzeichnissebene wie der src Ordner.

Die beiden Output Dateien heißen:
<codeclassname>.java
- in dieser Datei ist der generierte Head eines Java Programms.
und <testclassname>.java
- in dieser Datei ist der generierte Head des Java Tests.


PresetDataBase:

Die booleans für die Aktivierung von Babysteps und Timetracking sind als Klassenvariablen in PresetDataBase zu finden.
Die Namen der erstellten Klassen sind zu finden unter
- PresetDataBase.codeclassname  und
- PresetDataBase.testclassname

Beschreibung zur Nutzung von Babysteps sind als Kommentar in der dazu gehörigen Klasse.