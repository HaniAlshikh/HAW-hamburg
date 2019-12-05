Professor:: Dr. Bernd Kahlbrandt  
Author:: Nick Marvin Rattay  
Author:: Hani Alshikh  
<div style="text-align: right">01.12.2019</div>

# Beispiele und Stückliste

da es bei dieser Aufgabe viel zu erklären bzw. begründen gab, wurde an der jeweiligen Stelle entsprechenden Kommentare geschrieben.

## bewusste Entscheidungen

wo die Angaben fehlten, wurde dafür entschieden, was in der Realität zu entnehmen ist

### duplizierung

eine Stückliste darf ein oder mehrere kopien des gleichen (==) Part-Objekts, aber keins davon teilt die gleiche Identität bzw. den gleichen Speicherplatz. Z.B. bei der erstellung eines Part-Objekts, das an mehrere Stellen der Stückliste hinzugefügt werden sollte, wäre es unmöglich die jeweilige Kopie einem Ganzen zu verweisen, da alle kopien das gleiche Objekt referenzieren

## method_missing

um dem Nutzer das Leben einfacher zu machen, wurden für die jeweiligen Teile einer Stückliste Reader-Methoden dynamisch erstellt. Dies vermeidet schreibweisen wie:  

```ruby
partlist.parts[0].part[1].part[2]
```

und bietet die folgende Schreibweise:

```ruby
partlist.first_part_label.second_part_label.thired_part_label
```

##### Quellen
- [alias](https://ruby-doc.org/core-2.1.2/doc/syntax/miscellaneous_rdoc.html#label-alias)
- [alias_method](https://ruby-doc.org/core-2.6.5/Module.html#method-i-alias_method)
- [alias vs alias_method](https://blog.bigbinary.com/2012/01/08/alias-vs-alias-method.html)
- [What happens when you use string interpolation in ruby?](https://stackoverflow.com/questions/25488902/what-happens-when-you-use-string-interpolation-in-ruby)
- [Enumerable](https://ruby-doc.org/core-2.6.5/Enumerable.html)
- [Always Define respond_to_missing? When Overriding method_missing](https://thoughtbot.com/blog/always-define-respond-to-missing-when-overriding)
- [List of bicycle parts](https://en.wikipedia.org/wiki/List_of_bicycle_parts)