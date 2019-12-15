Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">15.12.2019</div>

# Struct & Openstruct

Struct: erstellt eine neue Klasse mit vordefinierten Attributen, Gleichheitsmethode (==) und enumerable.
OpenStruct: erstellt ein neues Objekt mit den angegebenen Attributen

Ein OpenStruct ist ein schickes Hash-Objekt, während ein Struct wie das Erstellen einer neuen Klasse aus einer Vorlage ist.

## 1.1. Openstruct

da Openstruct eine Datenstruktur ist, kann man dynamisch neue "Keys" (Methoden) erstellen und sie auf Objekte verweisen. 

OpenStruct nutzt Objekt-Individualisierung, um ein bereits erstelltes Objekt weiteren Attributen zu deferieren.

## 1.2. Struct

da Struct eine neue Subklasse erstellt, muss man muss man singleton Methoden definieren, um extra funktionalität zu geben.


##### Quellen
- [OpenStruct](https://ruby-doc.org/stdlib-2.6.5/libdoc/ostruct/rdoc/OpenStruct.html)
- [Struct](https://ruby-doc.org/core-2.6.5/Struct.html)
- [Ruby Concepts - Singleton Classes](https://www.assertnotmagic.com/2018/04/22/ruby-singleton-classes/)